package com.application.budgeting.infrastructure.http.requests;

import com.application.budgeting.application.input.TransactionInput;
import com.application.budgeting.domain.Category;

public record TransactionRequest(String description, Category category, long amount) {

    public TransactionInput toInput() {
        return new TransactionInput(this.description(), this.amount(), this.category());
    }
    
}
