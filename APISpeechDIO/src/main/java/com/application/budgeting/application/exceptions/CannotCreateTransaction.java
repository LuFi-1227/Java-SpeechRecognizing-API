package com.application.budgeting.application.exceptions;

import com.application.shared.domain.ResourceNotFoundException;

public class CannotCreateTransaction extends ResourceNotFoundException{
    public CannotCreateTransaction(String message){
        super(message);
    }
}
