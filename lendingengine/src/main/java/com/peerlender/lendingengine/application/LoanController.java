package com.peerlender.lendingengine.application;

import com.peerlender.lendingengine.application.model.LoanRequest;
import com.peerlender.lendingengine.application.service.TokenValidationService;
import com.peerlender.lendingengine.domain.model.Loan;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.repository.LoanApplicationRepository;
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

    private final LoanApplicationRepository loanRequestRepository;
    private final UserRepository userRepository;
    private final LoanApplicationAdapter loanApplicationAdapter;
    private final LoanService loanService;

    private final TokenValidationService tokenValidationService;

    @Autowired
    public LoanController(LoanApplicationRepository loanRequestRepository, UserRepository userRepository, LoanApplicationAdapter loanApplicationAdapter, LoanService loanService, TokenValidationService tokenValidationService) {
        this.loanRequestRepository = loanRequestRepository;
        this.userRepository = userRepository;
        this.loanApplicationAdapter = loanApplicationAdapter;
        this.loanService = loanService;
        this.tokenValidationService = tokenValidationService;
    }

    @PostMapping(value = "/loan/request")
    public void requestLoan(@RequestBody final LoanRequest loanApplication, HttpServletRequest request){
        User borrower = tokenValidationService.ValidateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        LoanApplication application=loanApplicationAdapter.transform(loanApplication, borrower);
        loanRequestRepository.save(application);
    }

    @GetMapping(value = "/users")
    public List<User> findUsers(HttpServletRequest request){
        tokenValidationService.ValidateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return userRepository.findAll();
    }

    @GetMapping(value = "/loan/getRequests")
    public List<LoanApplication> findLoanApplications(HttpServletRequest request){
        tokenValidationService.ValidateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));

        return loanRequestRepository.findAll();
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
}
