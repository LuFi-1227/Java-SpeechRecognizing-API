package com.application.users.infrastructure.http.requests;

import com.application.users.application.input.UserInput;

public record UserRequest(String name, String phone, String email) {
    public UserInput toInput(){
        return new UserInput(this.name(), this.phone(), this.email());
    }
}
