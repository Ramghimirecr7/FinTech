package com.peerlender.lendingengine.domain.exception;

public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException(){
        super("Loan Not Found");
    }
}
