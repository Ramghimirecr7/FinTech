package com.peerlender.lendingengine.application.model;

import com.peerlender.lendingengine.domain.model.Money;
import com.peerlender.lendingengine.domain.model.User;

import java.util.Objects;

public class LoanApplicationDTO {
    private long id;
    private User borrower;
    private Money money;
    private int repaymentTermInDays;
    private double interestRate;


    public long getId() {
        return id;
    }

    public User getBorrower() {
        return borrower;
    }

    public Money getMoney() {
        return money;
    }

    public int getRepaymentTermInDays() {
        return repaymentTermInDays;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public void setRepaymentTermInDays(int repaymentTermInDays) {
        this.repaymentTermInDays = repaymentTermInDays;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplicationDTO that = (LoanApplicationDTO) o;
        return id == that.id && repaymentTermInDays == that.repaymentTermInDays &&
                Double.compare(that.interestRate, interestRate) == 0 && Objects.equals(borrower, that.borrower) &&
                Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, borrower, money, repaymentTermInDays, interestRate);
    }

    @Override
    public String toString() {
        return "LoanApplicationDTO{" +
                "id=" + id +
                ", borrower=" + borrower +
                ", money=" + money +
                ", repaymentTermInDays=" + repaymentTermInDays +
                ", interestRate=" + interestRate +
                '}';
    }
}
