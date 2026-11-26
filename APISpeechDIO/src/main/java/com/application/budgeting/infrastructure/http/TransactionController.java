package com.application.budgeting.infrastructure.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.budgeting.application.ListTransactionByCategoryUseCase;
import com.application.budgeting.application.PersistTransactionUseCase;
import com.application.budgeting.infrastructure.http.requests.TransactionRequest;
import com.application.budgeting.infrastructure.http.responses.TransactionResponse;
import com.application.budgeting.domain.Category;

@RestController 
@RequestMapping ("/users/{userId}/transactions")
public class TransactionController {
    private PersistTransactionUseCase persistTransactionUseCase;
    private ListTransactionByCategoryUseCase listTransactionByCategoryUseCase;
    private final TranscriptionModel transcriptionModel;
    private final ChatClient chatClient;
    private final TextToSpeechModel textToSpeechModel;
    
    public TransactionController(PersistTransactionUseCase persistTransactionUseCase, 
                                 ListTransactionByCategoryUseCase listTransactionByCategoryUseCase, 
                                 TranscriptionModel transcriptionModel,
                                 ChatClient.Builder chatClientBuilder, 
                                 @Value ("classpath:/prompts/system-message.st") Resource systemPrompt,
                                TextToSpeechModel textToSpeechModel) throws IOException 
    {
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.listTransactionByCategoryUseCase = listTransactionByCategoryUseCase;
        this.transcriptionModel = transcriptionModel;
        this.chatClient = chatClientBuilder.defaultTools(persistTransactionUseCase, listTransactionByCategoryUseCase)
                            .defaultSystem(systemPrompt.getContentAsString(Charset.defaultCharset()))
                            .build();
        this.textToSpeechModel = textToSpeechModel;
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public TransactionResponse createTransaction(@PathVariable UUID userId, @RequestBody TransactionRequest request){
        var transaction = persistTransactionUseCase.execute(userId, request.toInput());
        return TransactionResponse.from(transaction);
    }
    
    @GetMapping ("/{category}")
    @ResponseStatus (HttpStatus.OK)
    public List<TransactionResponse> readTransactions(@PathVariable UUID userId, @PathVariable Category category){
        return this.listTransactionByCategoryUseCase.execute(userId, category).stream().map(TransactionResponse::from).toList();
    }

    @PostMapping (value = "/ai", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/wav")
    public ResponseEntity<ByteArrayResource> transcribe(@PathVariable UUID userId, @RequestParam ("file") MultipartFile file){
        var resource = file.getResource();
        var prompt = transcriptionModel.transcribe(resource);
        var result = chatClient.prompt().user(prompt).toolContext(Map.of("userId", userId)).call().content();
        byte[] audio = textToSpeechModel.call(result);

        var response = new ByteArrayResource(audio);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                    ContentDisposition.inline()
                    .filename("audio.wav")
                    .build()
                    .toString())
                    .body(response);
    }

}
