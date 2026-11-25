package com.application.budgeting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@SpringBootTest 
public class TranscriptionModelTests {
    @Autowired 
    private OpenAiAudioTranscriptionModel transcriptionModel;

    @ParameterizedTest 
    @CsvSource({
        "/tmp/audio10179348243290264525.mp3, valor de serviço",
    })
    void shouldTranscribeAudioFile(String audioFilePath, String expectedKeywords) {
        var recordingFile = new FileSystemResource(audioFilePath);

        var transcription = transcriptionModel.call(recordingFile);

        System.out.println("\n\n\nTranscription: " + transcription + "\n\n\n");
        assert transcription.contains(expectedKeywords);
    }
}
