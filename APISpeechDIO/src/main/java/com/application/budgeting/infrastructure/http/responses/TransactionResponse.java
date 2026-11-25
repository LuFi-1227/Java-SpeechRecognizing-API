package com.application.budgeting.infrastructure.http.responses;

import com.application.budgeting.application.output.TransactionOutput;

public record TransactionResponse(String id, String category, String description, double amount) {

    public static TransactionResponse from(TransactionOutput output) {
       return new TransactionResponse(output.id(), 
       output.category(), 
       output.description(), 
       output.amount());
    }
    
}
