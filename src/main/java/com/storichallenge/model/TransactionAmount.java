package com.storichallenge.model;

import java.time.Month;

public class TransactionAmount {
    private Month month;
    private Integer amount;

    public TransactionAmount(Month month, Integer amount) {
        this.setMonth(month);
        this.setAmount(amount);
    }

    public Month getMonth() {
        return month;
    }

    public TransactionAmount setMonth(Month month) {
        this.month = month;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public TransactionAmount setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }
}
