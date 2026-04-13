package com.yychainsaw.validation;

import com.yychainsaw.anno.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidation implements ConstraintValidator<Gender, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        return value.equals("MALE") || value.equals("FEMALE") || value.equals("OTHER");
    }
}
