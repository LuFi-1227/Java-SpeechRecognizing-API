package com.application.budgeting.application;

import org.springframework.stereotype.Service;

import com.application.budgeting.application.input.TransactionInput;
import com.application.budgeting.application.output.TransactionOutput;
import com.application.budgeting.domain.Transaction;
import com.application.budgeting.domain.TransactionRepository;

@Service 
public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public TransactionOutput execute(TransactionInput input){
        var transaction = transactionRepository.save(new Transaction(input.description(), input.amount(), input.category()));
        return TransactionOutput.from(transaction);

    }
}
