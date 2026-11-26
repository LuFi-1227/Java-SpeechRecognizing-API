package com.application.budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
@EnabledIfEnvironmentVariable(named = "GROQ_API_KEY", matches = ".+")
public class AudioSpeechModelTests {
    @Autowired 
    private OpenAiAudioSpeechModel speechModel;

    @Test  
    void shouldRecordSpeechFile() throws IOException {
        var response = speechModel.call("O valor do serviço é de cinquenta reais e foi pago no supermercado.");

        assertThat(response).hasSizeGreaterThan(1024);

        var tempFile = Files.createTempFile("audio", ".mp3");
        Files.write(tempFile, response);
        System.out.println(tempFile.toAbsolutePath());

    }
}
