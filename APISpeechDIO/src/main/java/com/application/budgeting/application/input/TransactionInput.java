package com.application.budgeting.application.input;

import org.springframework.ai.tool.annotation.ToolParam;

import com.application.budgeting.domain.Category;

public record TransactionInput(@ToolParam (description = "Descrição dos detalhes da transação") String description, 
                               @ToolParam (description = "Valor da transação em centavos") long amount, 
                               @ToolParam (description = "Categoria da transação") Category category) {
    
}
