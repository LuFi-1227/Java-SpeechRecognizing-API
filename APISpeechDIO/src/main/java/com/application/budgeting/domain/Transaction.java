package com.application.budgeting.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class Transaction {
    private TransactionId id;
    private UUID userId;
    private String description;
    private long amount;
    private Category category;

    public Transaction(UUID userId, String description, long amount, Category category2){
        this.id = new TransactionId();
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.category = category2;
    }
}
