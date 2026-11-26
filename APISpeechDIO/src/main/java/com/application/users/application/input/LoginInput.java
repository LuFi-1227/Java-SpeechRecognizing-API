package com.application.users.application.input;

import com.application.users.infrastructure.http.requests.ValidPhone;

import jakarta.validation.constraints.NotBlank;

public record LoginInput(@NotBlank @ValidPhone String phone) {
    
}
