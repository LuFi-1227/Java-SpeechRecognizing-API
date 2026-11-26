package com.application.budgeting.application.gateway;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.application.users.application.UserExistsUseCase;
import com.application.users.domain.UserId;


import com.application.users.application.input.SplitedUserInput;

@Service 
public class UserExistsCaller {
    private UserExistsUseCase userExistsUseCase;

    public UserExistsCaller(UserExistsUseCase userExistsUseCase){
        this.userExistsUseCase = userExistsUseCase;
    }

    public boolean call(UUID userId){
        return userExistsUseCase.execute(new SplitedUserInput(userId));
    }
}
