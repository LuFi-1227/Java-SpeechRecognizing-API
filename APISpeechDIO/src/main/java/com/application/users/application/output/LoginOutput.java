package com.application.users.application.output;

public record LoginOutput(String token, String type) {
    public LoginOutput(String token){
        this(token, "Bearer");
    }
}
