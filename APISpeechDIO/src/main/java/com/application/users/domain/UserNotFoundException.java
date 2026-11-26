package com.application.users.domain;

import com.application.shared.domain.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException{
    public UserNotFoundException(String message){
        super(message);
    }
}
