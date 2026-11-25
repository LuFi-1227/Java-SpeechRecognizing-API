package com.application.budgeting.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.application.budgeting.domain.Category;
import com.application.budgeting.domain.TransactionRepository;

import com.application.budgeting.application.output.TransactionOutput;

@Service 
public class ListTransactionByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public ListTransactionByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionOutput> execute(Category category){
        return transactionRepository.findAllByCategory(category).stream().map(TransactionOutput::from).toList();
    }
}
