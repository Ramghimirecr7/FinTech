package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public final class LoanApplication {

    @Id
    private long id;
    private  int amount;
    @ManyToOne
    private User borrower;
    private int repaymentTermInDays;
    private double interestRate;
    private Status status;

    public LoanApplication() {
    }

    public LoanApplication(int amount, User borrower, int repaymentTermInDays, double interestRate) {
        this.amount = amount;
        this.borrower = borrower;
        this.repaymentTermInDays = repaymentTermInDays;
        this.interestRate = interestRate;
        this.status = Status.ONGOING;
    }

    public Loan acceptLoanApplication(final User lender){
        lender.withdraw(getAmount());
        borrower.topUp(getAmount());
        status = Status.COMPLETED;
        return new Loan(lender, this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, borrower, repaymentTermInDays, interestRate);
    }
    public Money getAmount() {
        return new Money(amount, Currency.USD);
    }

    public User getBorrower() {
        return borrower;
    }

    public int getRepaymentTermInDays() {
        return repaymentTermInDays;
    }

    public long getId() {
        return id;
    }

    public double getInterestRate() {
        return interestRate;
    }


    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", borrower=" + borrower +
                ", repaymentTerm=" + repaymentTermInDays +
                ", interestRate=" + interestRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplication that = (LoanApplication) o;
        return amount == that.amount && Double.compare(that.interestRate, interestRate) == 0 && Objects.equals(borrower, that.borrower) && Objects.equals(repaymentTermInDays, that.repaymentTermInDays);
    }
}
