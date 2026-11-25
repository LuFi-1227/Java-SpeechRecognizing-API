package com.application.users.infrastructure.http.requests;

import com.application.users.application.input.SplitedUserInput;

public record UserPhoneRequest(String phone) {
    public SplitedUserInput toInput(){
        return new SplitedUserInput(phone);
    }
}
