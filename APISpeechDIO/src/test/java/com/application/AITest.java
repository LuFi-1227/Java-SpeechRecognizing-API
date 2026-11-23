package com.application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class AITest {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Value("${spring.ai.openai.chat.model}")
    private String model;

    @Test
    void test() {
        var chatModel = OpenAiChatModel.builder()
            .options(OpenAiChatOptions.builder()
                .baseUrl("https://api.groq.com/openai/v1")
                .apiKey(System.getenv("GROQ_API_KEY"))
                .model("llama3-70b-8192")
                .temperature(0.4)
                .maxTokens(200)
                .build())
            .build();


        ChatResponse response = this.chatModel.call(
            new Prompt("Generate the names of 5 famous pirates."));

        // Or with streaming responses
        Flux<ChatResponse> response2 = this.chatModel.stream(
            new Prompt("Generate the names of 5 famous pirates."));
    }
}
