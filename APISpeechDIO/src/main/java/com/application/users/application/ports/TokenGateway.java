package com.application.users.application.ports;

import com.application.users.domain.User;

public interface TokenGateway {
    String generateToken(User user);
    String validateTokenAndGetUserID(String token);
}
