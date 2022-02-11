package com.storichallenge.model;

import java.time.LocalDate;

public class TransactionRecord {
    Integer id;
    LocalDate date;
    Transaction transaction;

    public TransactionRecord(Integer id, LocalDate date, Transaction transaction) {
        this.setId(id) ;
        this.setDate(date);
        this.setTransaction(transaction);
    }

    public Integer getId() {
        return id;
    }

    public TransactionRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionRecord setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public TransactionRecord setTransaction(Transaction transaction) {
        this.transaction = transaction;
        return this;
    }
}
