package com.application.budgeting;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping ("/api")
public class ChatClientController {
    private final ChatClient chatClient;

    public ChatClientController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping ("/chat-client")
    public String chat(@RequestParam String prompt) {
        var response = chatClient.prompt(prompt).call().content();
        return response;
    }
}
