package com.application.users.application;

import org.springframework.stereotype.Service;

import com.application.users.application.input.SplitedUserInput;
import com.application.users.domain.UserId;
import com.application.users.domain.UserRepository;

@Service 
public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(SplitedUserInput input){
        userRepository.delete(new UserId(input.id()));
    }
}
