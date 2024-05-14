package com.example.bookshop.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldMatchesValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstField;
    private String secondField;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstField = constraintAnnotation.first();
        secondField = constraintAnnotation.second();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object firstObj = null;
        try {
            firstObj = getProperty(o, firstField);
            Object secondObj = getProperty(o, secondField);
            return firstObj != null && firstObj.equals(secondObj);
        } catch (Exception e) {
            return false;
        }

    }

    private Object getProperty(Object obj, String fieldName) throws Exception {
        Class<?> clazz = obj.getClass();
        java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
