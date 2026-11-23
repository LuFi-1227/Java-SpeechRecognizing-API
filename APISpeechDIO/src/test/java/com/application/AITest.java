package com.application;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class AITest {

    @Autowired
    private OpenAiChatModel chatModel;

    @Test
    void test() {
        ChatResponse response = chatModel.call(
            new Prompt(
                "Generate the names of 5 famous pirates.",
                OpenAiChatOptions.builder()
                    .temperature(0.4)
                    .build()
            )
        );

        System.out.println(response.getResult().getOutput().getText());
    }
}