package com.example.bookshop.annotation;

import com.example.bookshop.dto.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldMatchesValidator implements ConstraintValidator<FieldMatch, Object> {
    private String[] fields;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        fields = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistrationRequestDto userDto = (UserRegistrationRequestDto) o;
        return userDto.getPassword().equals(userDto.getRepeatPassword());
    }
}
