package com.application.budgeting.infrastructure.http;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.application.budgeting.application.ListTransactionByCategoryUseCase;
import com.application.budgeting.application.PersistTransactionUseCase;
import com.application.budgeting.infrastructure.http.requests.TransactionRequest;
import com.application.budgeting.infrastructure.http.responses.TransactionResponse;
import com.application.budgeting.domain.Category;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController 
@RequestMapping ("/transactions")
public class TransactionController {
    private PersistTransactionUseCase persistTransactionUseCase;
    private ListTransactionByCategoryUseCase listTransactionByCategoryUseCase;

    public TransactionController(PersistTransactionUseCase persistTransactionUseCase) {
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.listTransactionByCategoryUseCase = listTransactionByCategoryUseCase;
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request){
        var transaction = persistTransactionUseCase.execute(request.toInput());
        return TransactionResponse.from(transaction);
    }
    
    @GetMapping ("/{category}")
    @ResponseStatus (HttpStatus.OK)
    public List<TransactionResponse> readTransactions(@RequestParam Category category){
        return this.listTransactionByCategoryUseCase.execute(category).stream().map(TransactionResponse::from).toList();
    }
}
