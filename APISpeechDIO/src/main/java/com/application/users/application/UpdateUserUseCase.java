package com.application.users.application;

import org.springframework.stereotype.Service;

import com.application.users.application.input.SplitedUserInput;
import com.application.users.application.input.UserInput;
import com.application.users.application.output.UserOutput;
import com.application.users.domain.User;
import com.application.users.domain.UserId;
import com.application.users.domain.UserRepository;

@Service 
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput execute(UserInput user, SplitedUserInput input){
        return UserOutput.from(userRepository.update(new UserId(input.id()), new User(user.name(), user.phone(), user.email())));
    }
}
