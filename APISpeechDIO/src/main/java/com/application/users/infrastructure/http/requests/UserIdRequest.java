package com.application.users.infrastructure.http.requests;

import java.util.UUID;

import com.application.users.application.input.SplitedUserInput;

public record UserIdRequest(String id) {
    public SplitedUserInput toInput(){
        return new SplitedUserInput(UUID.fromString(id));
    }
}
