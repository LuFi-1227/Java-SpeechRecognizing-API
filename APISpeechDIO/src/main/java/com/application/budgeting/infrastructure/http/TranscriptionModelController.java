package com.application.budgeting.infrastructure.http;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class TranscriptionModelController {
    private final TranscriptionModel transcriptionModel;

    public TranscriptionModelController(TranscriptionModel transcriptionModel) {
        this.transcriptionModel = transcriptionModel;
    }

    @PostMapping(value = "/transcription", consumes = "multipart/form-data")
    public String transcribe(@RequestParam("file") MultipartFile file) {
        var resource = file.getResource();
        var transcription = transcriptionModel.transcribe(resource);
        return transcription;
    }
    
}
