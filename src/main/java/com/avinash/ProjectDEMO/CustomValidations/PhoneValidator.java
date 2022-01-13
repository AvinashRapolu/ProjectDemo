package com.avinash.ProjectDEMO.CustomValidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneNumber,Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(value.toString().length() > 10 ||value.toString().length() < 10)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
