package com.application.budgeting.application;

import java.util.Locale.Category;

import com.application.budgeting.domain.Transaction;
import com.application.budgeting.domain.TransactionRepository;

public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public void execute(String description, long amount, Category category){
        var transaction = new Transaction(description, amount, category);

    }
}
