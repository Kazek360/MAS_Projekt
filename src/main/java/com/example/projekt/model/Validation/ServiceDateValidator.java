package com.example.projekt.model.Validation;

import com.example.projekt.model.Service;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ServiceDateValidator implements ConstraintValidator<ValidServiceDate, Service> {
    @Override
    public void initialize(ValidServiceDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Service service, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate hireDate = service.getDateFrom();
        LocalDate fireDate = service.getDateTo();
        return fireDate == null || !hireDate.isAfter(fireDate);
    }
}
