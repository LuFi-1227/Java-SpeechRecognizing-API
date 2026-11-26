package com.application.users.infrastructure.http.requests;

import com.application.users.domain.Phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    @Override 
    public boolean isValid(String value, ConstraintValidatorContext context){
        if (value == null || value.isBlank()){
            return false;
        }
        return Phone.isValid(value);
    }
    
}
