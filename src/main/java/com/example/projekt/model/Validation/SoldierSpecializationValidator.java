package com.example.projekt.model.Validation;

import com.example.projekt.model.Enums.SpecializationType;
import com.example.projekt.model.Soldier;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SoldierSpecializationValidator implements ConstraintValidator<ValidSoldierSpecialization, Soldier> {

    private SpecializationType specializationType;

    @Override
    public void initialize(ValidSoldierSpecialization constraintAnnotation) {
        this.specializationType = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Soldier soldier, ConstraintValidatorContext constraintValidatorContext) {
        switch (specializationType){
            case QUARTERMASTER -> {
                return soldier.getLoadCapacity() != null;
            }
            case MEDIC -> {
                return soldier.getMedicCourses() != null && !soldier.getMedicCourses().isEmpty();
            }
            case SPECIALIST -> {
                return soldier.getSpecialities() != null && !soldier.getSpecialities().isEmpty();
            }
            default -> {
                return false;
            }
        }
    }
}
