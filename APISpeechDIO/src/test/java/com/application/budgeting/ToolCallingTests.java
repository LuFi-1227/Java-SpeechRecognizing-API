package com.application.budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
@EnabledIfEnvironmentVariable(named = "GROQ_API_KEY", matches = ".+")
public class ToolCallingTests {
    @Autowired 
    OpenAiChatModel chatModel;

    static class MathTools {
        @Tool(description = "Calculates the sum of two numbers.")
        public int sum(int a, int b) {
            return a + b;
        }

        @Tool(description = "Calculates the difference of two numbers.")
        public int diff(int a, int b) {
            return a - b;
        }
    }

    @Test 
    void shouldExecuteSumWhenPrompted(){
        var chatClient = ChatClient.builder(chatModel)
        .defaultSystem("You are a helpful assistant that can perform calculations.")
        .defaultTools(new MathTools())
        .build();

        var response = chatClient.prompt("What is 2 + 2?")
        .call().content();

        assertThat(response).contains("4");
        System.out.println("Response: " + response);
    }
}
