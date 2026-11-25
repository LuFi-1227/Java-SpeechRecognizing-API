package com.application.users.application.input;

import java.util.UUID;

public record SplitedUserInput(UUID id, String phone) {
    public SplitedUserInput(UUID id){
        this(id, null);
    }

    public SplitedUserInput(String phone){
        this(null, phone);
    }
}
