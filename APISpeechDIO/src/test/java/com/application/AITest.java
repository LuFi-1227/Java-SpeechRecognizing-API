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

        assert response.getResult().getOutput().getText() != null;
        if (response.getResult().getOutput().getText() == null) {
            throw new RuntimeException("Response text is null");
        }else{
            assert response.getResult().getOutput().getText().length() > 0;
            assert response.getResult().getOutput().getText().contains("Blackbeard");
        }
        System.out.println(response.getResult().getOutput().getText());
    }
}