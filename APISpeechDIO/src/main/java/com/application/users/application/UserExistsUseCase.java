package com.application.users.application;

import org.springframework.stereotype.Service;

import com.application.users.application.input.SplitedUserInput;
import com.application.users.domain.UserId;
import com.application.users.domain.UserRepository;

@Service 
public class UserExistsUseCase {
    private final UserRepository userRepository;

    public UserExistsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(SplitedUserInput input){
        if (userRepository.findById(new UserId(input.id())).isEmpty()){
            return false;
        }
        return true;
    }
}
