package com.application.budgeting;

import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TranscriptionModelController {
    private final OpenAiAudioTranscriptionModel transcriptionModel;

    public TranscriptionModelController(OpenAiAudioTranscriptionModel transcriptionModel) {
        this.transcriptionModel = transcriptionModel;
    }

    @GetMapping("/transcription-model")
    public String transcribe(@RequestParam String audioFilePath) {
        var recordingFile = new ClassPathResource(audioFilePath);
        var transcription = transcriptionModel.call(recordingFile);
        return transcription;
    }
    
}
