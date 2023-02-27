package com.peerlender.lendingengine.domain.exception;

public class LoanApplicationNotFoundException extends RuntimeException{
    public LoanApplicationNotFoundException(long applicationId) {
        super("Loan with application id " + applicationId+ " was not found");
    }
}
