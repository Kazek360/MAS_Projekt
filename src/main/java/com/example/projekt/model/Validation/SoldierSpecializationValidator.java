package com.example.projekt.model.Validation;

import com.example.projekt.model.Enums.SpecializationType;
import com.example.projekt.model.Soldier;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class SoldierSpecializationValidator implements ConstraintValidator<ValidSoldierSpecialization, Soldier> {

    @Override
    public void initialize(ValidSoldierSpecialization constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Soldier soldier, ConstraintValidatorContext constraintValidatorContext) {
        if (soldier == null)
            return true;

        Integer loadCapacity = soldier.getLoadCapacity();
        boolean isQuartermaster = soldier.getSpecializationTypes().contains(SpecializationType.QUARTERMASTER);

        boolean hasMedicCourses = soldier.getMedicCourses() != null && !soldier.getMedicCourses().isEmpty();
        boolean isMedic = soldier.getSpecializationTypes().contains(SpecializationType.MEDIC);

        boolean hasSpecialities = soldier.getSpecialities() != null && !soldier.getSpecialities().isEmpty();
        boolean isSpecialist = soldier.getSpecializationTypes().contains(SpecializationType.SPECIALIST);

        if (loadCapacity == null){
            if (hasMedicCourses && !isMedic)
                return false;

            if (hasSpecialities && !isSpecialist)
                return false;
        } else {
            if (loadCapacity > 1 && !isQuartermaster)
                return false;

            if (hasMedicCourses && !isMedic)
                return false;

            if (hasSpecialities && !isSpecialist)
                return false;
        }

        return true;
    }
}
