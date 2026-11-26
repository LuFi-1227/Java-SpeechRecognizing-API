package com.application.users.application;

import org.springframework.stereotype.Service;

import com.application.users.application.input.SplitedUserInput;
import com.application.users.application.output.UserOutput;
import com.application.users.domain.UserRepository;
import com.application.users.domain.Phone;
import com.application.users.domain.UserNotFoundException;
@Service 
public class FindUserByPhoneUseCase {
    private final UserRepository userRepository;

    public FindUserByPhoneUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput execute(SplitedUserInput input){
        var phone = new Phone(input.phone());
        return UserOutput.from(userRepository.findByPhone(phone.value()).orElseThrow(() -> new UserNotFoundException("User not found by PHONE")));
    }
}
