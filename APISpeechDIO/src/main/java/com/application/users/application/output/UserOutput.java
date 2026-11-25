package com.application.users.application.output;

import com.application.users.domain.User;

public record UserOutput(String id, String name, String phone, String email) {
    public static UserOutput from(User user){
        return new UserOutput(user.getId().id().toString(), user.getName(), user.getPhone(), user.getEmail());
    }
}
