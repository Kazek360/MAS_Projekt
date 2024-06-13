package com.example.projekt.model.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ServiceDateValidator.class)
public @interface ValidServiceDate {

    String message() default "Service start date is after end date!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
