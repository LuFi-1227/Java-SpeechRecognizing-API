package com.application.budgeting.application;

import java.util.UUID;

import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import com.application.budgeting.application.exceptions.CannotCreateTransaction;
import com.application.budgeting.application.gateway.UserExistsCaller;
import com.application.budgeting.application.input.TransactionInput;
import com.application.budgeting.application.output.TransactionOutput;
import com.application.budgeting.domain.Transaction;
import com.application.budgeting.domain.TransactionRepository;

@Service
public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;
    private UserExistsCaller userExistsCaller;

    public PersistTransactionUseCase(TransactionRepository transactionRepository, UserExistsCaller userExistsCaller){
        this.transactionRepository = transactionRepository;
        this.userExistsCaller = userExistsCaller;
    }

    public TransactionOutput execute(UUID userId, TransactionInput input){
        if (!userExistsCaller.call(userId)){
            throw new CannotCreateTransaction("UserId not exists");
        }
        var transaction = transactionRepository.save(new Transaction(userId, input.description(), input.amount(), input.category()));
        return TransactionOutput.from(transaction);
        
    }
    
    @Tool(name = "persist-transaction", description = "Persiste no banco de dados uma nova transação financeira")
    public TransactionOutput executeTool(TransactionInput input, ToolContext toolContext) {                         
        UUID userId = (UUID) toolContext.getContext().get("userId");
        return execute(userId, input);                                                                              
    }  
}
