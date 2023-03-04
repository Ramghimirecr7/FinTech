package com.peerlender.lendingengine.application.model;


import com.peerlender.lendingengine.domain.model.Money;

import java.util.Objects;

public final class LoanRepaymentRequest {
    private final Money amount;
    private final long loanId;

    public LoanRepaymentRequest(Money amount, long loanId) {
        this.amount = amount;
        this.loanId = loanId;
    }

    public Money getAmount() {
        return amount;
    }

    public long getLoanId() {
        return loanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRepaymentRequest that = (LoanRepaymentRequest) o;
        return amount == that.amount && loanId == that.loanId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, loanId);
    }

    @Override
    public String toString() {
        return "LoanRepaymentRequest{" +
                "amount=" + amount +
                ", loanId=" + loanId +
                '}';
    }
}
