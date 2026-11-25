package com.application.budgeting.domain;

public class Transaction {
    private TransactionId id;
    private String description;
    private long amount;
    private Category category;

    public Transaction(String description, long amount, Category category2){
        this.id = new TransactionId();
        this.description = description;
        this.amount = amount;
        this.category = category2;
    }
}
