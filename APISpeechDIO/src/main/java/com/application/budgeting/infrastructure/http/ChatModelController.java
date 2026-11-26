package com.application.budgeting.infrastructure.http;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ChatModelController {
    private final OpenAiChatModel chatModel;

    public ChatModelController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chat-model")
    public String chat(@RequestParam String prompt) {
        var response = chatModel.call(prompt);
        return response;
    }
}
