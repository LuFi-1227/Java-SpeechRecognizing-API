package com.application.users.application;

import org.springframework.stereotype.Service;

import com.application.users.application.input.SplitedUserInput;
import com.application.users.application.output.UserOutput;
import com.application.users.domain.UserId;
import com.application.users.domain.UserNotFoundException;
import com.application.users.domain.UserRepository;

@Service 
public class FindUserByIdUseCase {
    private final UserRepository userRepository;

    public FindUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput execute(SplitedUserInput input){
        return UserOutput.from(userRepository.findById(new UserId(input.id())).orElseThrow(() -> new UserNotFoundException("User not found by ID")));
    }
}
