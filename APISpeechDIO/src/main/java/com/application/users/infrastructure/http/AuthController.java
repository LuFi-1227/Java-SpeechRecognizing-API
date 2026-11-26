package com.application.users.infrastructure.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.users.application.LoginWithPhoneUseCase;
import com.application.users.application.input.LoginInput;
import com.application.users.application.output.LoginOutput;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/auth")
public class AuthController {
    private final LoginWithPhoneUseCase loginWithPhoneUseCase;

    public AuthController(LoginWithPhoneUseCase loginWithPhoneUseCase) {
        this.loginWithPhoneUseCase = loginWithPhoneUseCase;
    }

    @PostMapping ("/login")
    public ResponseEntity<LoginOutput> login(@Valid @RequestBody LoginInput request){
        var output = loginWithPhoneUseCase.execute(request);
        return ResponseEntity.ok(output);
    }
}
