package com.application.users.infrastructure.http.responses;

import com.application.users.application.output.UserOutput;

public record UserResponse(String id, String name, String phone, String email) {
    public static UserResponse from(UserOutput user){
        return new UserResponse(user.id(), user.name(), user.phone(), user.email());
    }
}
