package com.example.projekt.model.Validation;

import com.example.projekt.model.Enums.SpecializationType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SoldierSpecializationValidator.class)
public @interface ValidSoldierSpecialization {

    String message() default "Soldier does not have the required specialization.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

//    SpecializationType value();
}
