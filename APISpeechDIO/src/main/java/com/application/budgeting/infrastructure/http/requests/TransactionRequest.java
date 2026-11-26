package com.application.budgeting.infrastructure.http.requests;

import com.application.budgeting.application.input.TransactionInput;
import com.application.budgeting.domain.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequest(
    @NotBlank (message = "Description is required")
    String description, 
    @NotNull (message = "Category is required")
    Category category, 
    @Positive 
    long amount) {

    public TransactionInput toInput() {
        return new TransactionInput(this.description(), this.amount(), this.category());
    }
    
}
