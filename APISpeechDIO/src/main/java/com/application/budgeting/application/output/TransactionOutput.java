package com.application.budgeting.application.output;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.application.budgeting.domain.Transaction;

public record TransactionOutput(String id, String description, String category, double amount) {
    public static TransactionOutput from(Transaction transaction){
        return new TransactionOutput(transaction.getId().id().toString(), 
        transaction.getDescription().toString(), 
        transaction.getCategory().toString(), 
        BigDecimal.valueOf(transaction.getAmount())
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue());
    }   
}
