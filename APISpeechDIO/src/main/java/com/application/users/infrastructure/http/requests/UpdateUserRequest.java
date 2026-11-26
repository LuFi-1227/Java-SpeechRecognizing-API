package com.application.users.infrastructure.http.requests;

import com.application.users.application.input.UserInput;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
    @NotBlank (message = "id is required")
    String id, 
    @NotBlank (message = "Name is required")
    String name, 
    @ValidPhone 
    String phone, 
    @NotBlank (message = "Email is required")
    @Email (message = "Email is not valid")
    String email) {
    public UserInput toInput(){
        return new UserInput(this.name(), this.phone(), this.email());
    }
}
