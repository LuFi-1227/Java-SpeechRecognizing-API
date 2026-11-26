package com.application.budgeting.application;

import java.util.List;
import java.util.UUID;

import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
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

    public List<TransactionOutput> execute(UUID userId, Category category) {                                            
        return transactionRepository.findAllByUserIdAndCategory(userId, category)                                       
                .stream()                                                                                               
                .map(TransactionOutput::from)                                                                           
                .toList();                                                                                              
    } 

    @Tool(name = "list-transaction-by-category", description = "Lista as transações financeiras por categoria")
    public List<TransactionOutput> executeTool(@ToolParam (description = "Categoria de uma transação") Category category, ToolContext toolContext){
        UUID userId = (UUID) toolContext.getContext().get("userId");
        return execute(userId, category);
    }
}
