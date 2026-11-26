package com.application.users.application;

import org.springframework.stereotype.Service;

import com.application.users.application.input.LoginInput;
import com.application.users.application.output.LoginOutput;
import com.application.users.application.ports.TokenGateway;
import com.application.users.domain.Phone;
import com.application.users.domain.UserNotFoundException;
import com.application.users.domain.UserRepository;

@Service
public class LoginWithPhoneUseCase {
    private final UserRepository userRepository;
    private final TokenGateway tokenGateway;

    public LoginWithPhoneUseCase(UserRepository userRepository, TokenGateway tokenGateway) {
        this.userRepository = userRepository;
        this.tokenGateway = tokenGateway;
    }

    public LoginOutput execute(LoginInput input){
        String normalizedPhone = Phone.normalize(input.phone());

        var user = userRepository.findByPhone(normalizedPhone).orElseThrow(() -> new UserNotFoundException("User not founded by PHONE"));

        String token = tokenGateway.generateToken(user);
        return new LoginOutput(token);
    }
}
