package com.example.bookshop.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchesValidator.class)
@Documented
public @interface FieldMatch {

    String message() default "Passwords must be identical";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
