package com.application.users.application;

import org.springframework.stereotype.Service;

import com.application.users.application.input.SplitedUserInput;
import com.application.users.application.output.UserOutput;
import com.application.users.domain.UserRepository;

@Service 
public class FindUserByPhoneUseCase {
    private final UserRepository userRepository;

    public FindUserByPhoneUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput execute(SplitedUserInput input){
        return UserOutput.from(userRepository.findByPhone(input.phone()));
    }
}
