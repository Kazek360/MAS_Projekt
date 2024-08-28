package com.example.projekt;

import com.example.projekt.model.Enums.Rank;
import com.example.projekt.model.Enums.SpecializationType;
import com.example.projekt.model.Enums.TrainingLevel;
import com.example.projekt.model.Infantryman;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SoldierValidatorTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenLoadCapacityAndQuartermaster() {
        Set<SpecializationType> specializations = new HashSet<>();
        specializations.add(SpecializationType.QUARTERMASTER);

        Infantryman infantryman = Infantryman.builder()
                .id(1L)
                .identyficationNumber("AD123456")
                .name("Jan")
                .surname("Kowalski")
                .rank(Rank.PRIVATE)
                .trainingLevel(TrainingLevel.BASIC)
                .pay(2000)
                .loadCapacity(10)
                .specializationTypes(specializations)
                .build();

        System.out.println(infantryman.getSpecializationTypes().toString());
        System.out.println(infantryman.getLoadCapacity());

        Set<ConstraintViolation<Infantryman>> violations = validator.validate(infantryman);

        assertTrue(violations.isEmpty(), violations.toString());
    }

    @Test
    public void whenLoadCapacityAndMedic() {
        Set<SpecializationType> specializations = new HashSet<>();
        specializations.add(SpecializationType.MEDIC);

        Infantryman infantryman = Infantryman.builder()
                .id(1L)
                .identyficationNumber("AD123456")
                .name("Jan")
                .surname("Kowalski")
                .rank(Rank.PRIVATE)
                .trainingLevel(TrainingLevel.BASIC)
                .pay(2000)
                .loadCapacity(10)
                .specializationTypes(specializations)
                .build();


        Set<ConstraintViolation<Infantryman>> violations = validator.validate(infantryman);

        assertFalse(violations.isEmpty(), violations.toString());
    }

    @Test
    public void whenMedicCoursesAndMedic() {
        Set<SpecializationType> specializations = new HashSet<>();
        specializations.add(SpecializationType.MEDIC);

        Set<String> courses = new HashSet<>();
        courses.add("Pierwsza pomoc");
        courses.add("Medycyna pola walki");


        Infantryman infantryman = Infantryman.builder()
                .id(1L)
                .identyficationNumber("AD123456")
                .name("Jan")
                .surname("Kowalski")
                .rank(Rank.PRIVATE)
                .trainingLevel(TrainingLevel.BASIC)
                .pay(2000)
                .medicCourses(courses)
                .specializationTypes(specializations)
                .build();


        Set<ConstraintViolation<Infantryman>> violations = validator.validate(infantryman);

        assertTrue(violations.isEmpty(), violations.toString());
    }

    @Test
    public void whenMedicCoursesAndNoMedic() {
        Set<SpecializationType> specializations = new HashSet<>();
        specializations.add(SpecializationType.QUARTERMASTER);

        Set<String> courses = new HashSet<>();
        courses.add("Pierwsza pomoc");
        courses.add("Medycyna pola walki");


        Infantryman infantryman = Infantryman.builder()
                .id(1L)
                .identyficationNumber("AD123456")
                .name("Jan")
                .surname("Kowalski")
                .rank(Rank.PRIVATE)
                .trainingLevel(TrainingLevel.BASIC)
                .pay(2000)
                .medicCourses(courses)
                .specializationTypes(specializations)
                .build();

        Set<ConstraintViolation<Infantryman>> violations = validator.validate(infantryman);

        assertFalse(violations.isEmpty(), violations.toString());
    }

    @Test
    public void whenMedicCoursesAndNoSpecialization() {
        Set<SpecializationType> specializations = new HashSet<>();
        specializations.add(SpecializationType.QUARTERMASTER);

        Set<String> courses = new HashSet<>();
        courses.add("Pierwsza pomoc");
        courses.add("Medycyna pola walki");


        Infantryman infantryman = Infantryman.builder()
                .id(1L)
                .identyficationNumber("AD123456")
                .name("Jan")
                .surname("Kowalski")
                .rank(Rank.PRIVATE)
                .trainingLevel(TrainingLevel.BASIC)
                .pay(2000)
                .medicCourses(courses)
                .build();

        Set<ConstraintViolation<Infantryman>> violations = validator.validate(infantryman);

        assertFalse(violations.isEmpty(), violations.toString());
    }

    @Test
    public void whenSpecialitiesAndSpecialization() {
        Set<SpecializationType> specializations = new HashSet<>();
        specializations.add(SpecializationType.SPECIALIST);

        Set<String> specialities = new HashSet<>();
        specialities.add("Wspinaczka");
        specialities.add("Strzelectwo");


        Infantryman infantryman = Infantryman.builder()
                .id(1L)
                .identyficationNumber("AD123456")
                .name("Jan")
                .surname("Kowalski")
                .rank(Rank.PRIVATE)
                .trainingLevel(TrainingLevel.BASIC)
                .pay(2000)
                .specialities(specialities)
                .specializationTypes(specializations)
                .build();

        Set<ConstraintViolation<Infantryman>> violations = validator.validate(infantryman);

        assertTrue(violations.isEmpty(), violations.toString());
    }

    @Test
    public void whenSpecialitiesAndOtherSpecialization() {
        Set<SpecializationType> specializations = new HashSet<>();
        specializations.add(SpecializationType.QUARTERMASTER);

        Set<String> specialities = new HashSet<>();
        specialities.add("Wspinaczka");
        specialities.add("Strzelectwo");


        Infantryman infantryman = Infantryman.builder()
                .id(1L)
                .identyficationNumber("AD123456")
                .name("Jan")
                .surname("Kowalski")
                .rank(Rank.PRIVATE)
                .trainingLevel(TrainingLevel.BASIC)
                .pay(2000)
                .specialities(specialities)
                .specializationTypes(specializations)
                .build();

        Set<ConstraintViolation<Infantryman>> violations = validator.validate(infantryman);

        assertFalse(violations.isEmpty(), violations.toString());
    }

    @Test
    public void whenSpecialitiesAndNoSpecialization() {


        Set<String> specialities = new HashSet<>();
        specialities.add("Wspinaczka");
        specialities.add("Strzelectwo");


        Infantryman infantryman = Infantryman.builder()
                .id(1L)
                .identyficationNumber("AD123456")
                .name("Jan")
                .surname("Kowalski")
                .rank(Rank.PRIVATE)
                .trainingLevel(TrainingLevel.BASIC)
                .pay(2000)
                .specialities(specialities)
                .build();

        Set<ConstraintViolation<Infantryman>> violations = validator.validate(infantryman);

        assertFalse(violations.isEmpty(), violations.toString());
    }

}
