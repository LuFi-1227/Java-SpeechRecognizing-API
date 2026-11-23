package com.application.budgeting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

@SpringBootTest 
public class TranscriptionModelTests {
    @Autowired 
    private OpenAiAudioTranscriptionModel transcriptionModel;

    @ParameterizedTest 
    @CsvSource({
        "src/test/resources/audio/test1.wav, Paguei cinquenta reais no supermercado",
        "src/test/resources/audio/test2.wav, Recebi cem reais no banco",
        "src/test/resources/audio/test3.wav, Comprei trinta reais na padaria",
        "src/test/resources/audio/test4.wav, Fui ao cinema",
        "src/test/resources/audio/test5.wav, Paguei vinte e cinco reais no restaurante"
    })
    void shouldTranscribeAudioFile(String audioFilePath, String expectedKeywords) {
        var recordingFile = new ClassPathResource(audioFilePath);

        var transcription = transcriptionModel.call(recordingFile);

        assert transcription.contains(expectedKeywords);
        System.out.println("Transcription: " + transcription);
    }
}
