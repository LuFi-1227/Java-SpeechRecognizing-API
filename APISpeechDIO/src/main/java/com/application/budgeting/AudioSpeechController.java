package com.application.budgeting;

import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/api")
public class AudioSpeechController {
    private final TextToSpeechModel textToSpeechModel;

    public AudioSpeechController(TextToSpeechModel textToSpeechModel){
        this.textToSpeechModel = textToSpeechModel;
    }

    @PostMapping(value = "/synthesize", produces = "audio/wav")
    public ResponseEntity<ByteArrayResource> synthesize(@RequestBody SynthesizeRequest request) {
        byte[] audio = textToSpeechModel.call(request.text);

        var resource = new ByteArrayResource(audio);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                    ContentDisposition.inline()
                    .filename("audio.wav")
                    .build()
                    .toString())
                    .body(resource);
    }

    public record SynthesizeRequest(String text) {
    }
}
