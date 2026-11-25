package com.application.budgeting.application;

import org.springframework.ai.tool.annotation.Tool;
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

    @Tool(name = "persist-transaction", description = "Persiste no banco de dados uma nova transação financeira")
    public TransactionOutput execute(TransactionInput input){
        var transaction = transactionRepository.save(new Transaction(input.description(), input.amount(), input.category()));
        return TransactionOutput.from(transaction);

    }
}
