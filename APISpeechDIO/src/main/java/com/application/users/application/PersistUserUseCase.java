package com.application.users.application;

import org.springframework.stereotype.Service;

import com.application.users.application.input.UserInput;
import com.application.users.application.output.UserOutput;
import com.application.users.domain.User;
import com.application.users.domain.UserRepository;

@Service 
public class PersistUserUseCase {
    private final UserRepository userRepository;

    public PersistUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput execute(UserInput user){
        return UserOutput.from(userRepository.save(new User(user.name(), user.phone(), user.email())));
    } 
}
