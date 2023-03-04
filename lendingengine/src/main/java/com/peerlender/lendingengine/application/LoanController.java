package com.peerlender.lendingengine.application;

import com.peerlender.lendingengine.application.model.LoanRepaymentRequest;
import com.peerlender.lendingengine.application.model.LoanRequest;
import com.peerlender.lendingengine.application.service.TokenValidationService;
import com.peerlender.lendingengine.domain.model.*;
import com.peerlender.lendingengine.domain.repository.LoanApplicationRepository;
import com.peerlender.lendingengine.domain.repository.LoanRepository;
import com.peerlender.lendingengine.domain.repository.UserRepository;
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
    private final LoanApplicationAdapter loanApplicationAdapter;
    private final LoanService loanService;

    private final TokenValidationService tokenValidationService;

    @Autowired
    public LoanController(LoanApplicationRepository loanApplicationRepository, LoanApplicationAdapter loanApplicationAdapter, LoanService loanService, TokenValidationService tokenValidationService) {
        this.loanApplicationRepository = loanApplicationRepository;
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
        return loanApplicationRepository.findAllByStatusEquals(Status.ONGOING);
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

    @GetMapping("/loans/{status}/borrowed")
    public List<Loan> findBorrowedLoans(@RequestHeader String authorization, @PathVariable Status status){
        User borrower = tokenValidationService.ValidateTokenAndGetUser(authorization);
        return loanService.findAllBorrowedLoans(borrower,status);
    }

    @GetMapping("/loans/{status}/lent")
    public List<Loan> findLentLoans(@RequestHeader String authorization, @PathVariable Status status){
        User lender = tokenValidationService.ValidateTokenAndGetUser(authorization);
        return loanService.findAllLentLoans(lender, status);
    }
    @PostMapping("/loan/repay")
    public void repayLoan(@RequestBody LoanRepaymentRequest request,@RequestHeader String authorization){
        User borrower = tokenValidationService.ValidateTokenAndGetUser(authorization);
        loanService.repayLoan(request.getAmount(), request.getLoanId(), borrower);

    }

}
