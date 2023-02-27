package com.peerlender.lendingengine.application;

import com.peerlender.lendingengine.application.model.LoanRequest;
import com.peerlender.lendingengine.domain.model.Loan;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.repository.LoanApplicationRepository;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import com.peerlender.lendingengine.domain.model.User;
import com.peerlender.lendingengine.domain.service.LoanApplicationAdapter;
import com.peerlender.lendingengine.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    private final LoanApplicationRepository loanRequestRepository;
    private final UserRepository userRepository;
    private final LoanApplicationAdapter loanApplicationAdapter;
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanApplicationRepository loanRequestRepository, UserRepository userRepository, LoanApplicationAdapter loanApplicationAdapter, LoanService loanService) {
        this.loanRequestRepository = loanRequestRepository;
        this.userRepository = userRepository;
        this.loanApplicationAdapter = loanApplicationAdapter;
        this.loanService = loanService;
    }

    @PostMapping(value = "/loan/request")
    public void requestLoan(@RequestBody final LoanRequest loanApplication){
        LoanApplication application=loanApplicationAdapter.transform(loanApplication);
        loanRequestRepository.save(application);
    }

    @GetMapping(value = "/users")
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/loan/getRequests")
    public List<LoanApplication> findLoanApplications(){
        return loanRequestRepository.findAll();
    }

    @PostMapping(value = "/loan/acceptLoanRequest/{lenderId}/{loanApplicationId}")
    public void acceptLoan(@PathVariable final String lenderId,
                           @PathVariable final String loanApplicationId){

    }

    @GetMapping(value = "/loans")
    public List<Loan> getLoans(){
        return loanService.getLoans();
    }
}
