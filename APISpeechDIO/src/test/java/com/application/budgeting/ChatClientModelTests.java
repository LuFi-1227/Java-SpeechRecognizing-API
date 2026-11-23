package com.application.budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
//@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class ChatClientModelTests {
    @Autowired 
    OpenAiChatModel chatModel;

    @Test 
    void shouldExecuteSumWhenPrompted(){
        var chatClient = ChatClient.builder(chatModel).defaultSystem("You are a helpful assistant that can perform calculations.").build();

        var response = chatClient.prompt("What is 2 + 2?").call().content();

        assertThat(response).contains("4");
        System.out.println("Response: " + response);
    }
}
