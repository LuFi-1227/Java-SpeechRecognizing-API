package com.application.budgeting.application.input;

import com.application.budgeting.domain.Category;

public record TransactionInput(String description, long amount, Category category) {
    
}
