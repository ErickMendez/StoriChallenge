package com.storichallenge.model;


import java.math.BigDecimal;

public class Transaction {
    BigDecimal value;
    Character type;

    public Transaction(BigDecimal value, Character type) {
        this.setValue(value);
        this.setType(type);
    }

    public BigDecimal getValue() {
        return value;
    }

    public Transaction setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public Character getType() {
        return type;
    }

    public Transaction setType(Character type) {

        if(TransactionType.CREDIT.equals(type)){
            this.type = type;
            return this;
        }
        if(TransactionType.DEBIT.equals(type)){
            this.type = type;
            return this;
        }
        else{
            throw new RuntimeException("Invalid transaction type");
        }
    }
}
