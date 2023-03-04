package com.peerlender.lendingengine.application;

import com.peerlender.lendingengine.application.model.LoanRequest;
import com.peerlender.lendingengine.application.service.TokenValidationService;
import com.peerlender.lendingengine.domain.model.Loan;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.repository.LoanApplicationRepository;
import com.peerlender.lendingengine.domain.repository.LoanRepository;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import com.peerlender.lendingengine.domain.model.User;
import com.peerlender.lendingengine.domain.service.LoanApplicationAdapter;
import com.peerlender.lendingengine.domain.service.LoanService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;
    private final LoanApplicationAdapter loanApplicationAdapter;
    private final LoanService loanService;

    private final TokenValidationService tokenValidationService;

    @Autowired
    public LoanController(LoanApplicationRepository loanApplicationRepository, UserRepository userRepository, LoanApplicationAdapter loanApplicationAdapter, LoanService loanService, TokenValidationService tokenValidationService) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.userRepository = userRepository;
        this.loanApplicationAdapter = loanApplicationAdapter;
        this.loanService = loanService;
        this.tokenValidationService = tokenValidationService;
    }

    @PostMapping(value = "/loan/request")
    public void requestLoan(@RequestBody final LoanRequest loanApplication, HttpServletRequest request){
        User borrower = tokenValidationService.ValidateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        LoanApplication application=loanApplicationAdapter.transform(loanApplication, borrower);
        loanApplicationRepository.save(application);
    }


    @GetMapping(value = "/loan/getAllApplications")
    public List<LoanApplication> findLoanApplications(HttpServletRequest request){
        tokenValidationService.ValidateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));

        return loanApplicationRepository.findAll();
    }

    @PostMapping(value = "/loan/acceptLoanRequest/{loanApplicationId}")
    public void acceptLoan(@PathVariable final String loanApplicationId, HttpServletRequest request){
        User lender = tokenValidationService.ValidateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanService.acceptLoan(Long.parseLong(loanApplicationId), lender.getUsername());

    }

    @GetMapping(value = "/loans")
    public List<Loan> getLoans(HttpServletRequest request){
        tokenValidationService.ValidateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));

        return loanService.getLoans();
    }

    @GetMapping("/loans/borrowed")
    public List<Loan> findBorrowedLoans(@RequestHeader String authorization){
        User borrower = tokenValidationService.ValidateTokenAndGetUser(authorization);
        return loanService.findAllBorrowedLoans(borrower);
    }

    @GetMapping("/loans/lent")
    public List<Loan> findLentLoans(@RequestHeader String authorization){
        User lender = tokenValidationService.ValidateTokenAndGetUser(authorization);
        return loanService.findAllLentLoans(lender);
    }

}
