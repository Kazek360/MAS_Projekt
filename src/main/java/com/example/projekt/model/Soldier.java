package com.example.projekt.model;

import com.example.projekt.model.Enums.Rank;
import com.example.projekt.model.Enums.SpecializationType;
import com.example.projekt.model.Enums.TrainingLevel;
import com.example.projekt.model.Validation.ValidSoldierSpecialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Soldier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Identyfication numberis mandatory")
    @Size(min = 2, max = 255)
    @Column(unique = true)
    private String identyficationNumber;

    @NotBlank(message = "Soldier name is mandatory")
    @Size(min = 2, max = 255)
    private String name;

    @NotBlank(message = "Soldier surname is mandatory")
    @Size(min = 2, max = 255)
    private String surname;

    @Enumerated
    @Column(name = "rank", nullable = false)
    private Rank rank;

    @Enumerated
    @Column(name = "training_level", nullable = false)
    private TrainingLevel trainingLevel;

    @Min(1000)
    private Integer pay;

    //Quartermaster
    @Min(1)
    private Integer loadCapacity;

    private Integer supplyPayBonus = 300;

    //Medic
    @ElementCollection
    @CollectionTable(name = "medic_courses", joinColumns = @JoinColumn(name = "soldier_id"))
    @Builder.Default
    private Set<String> medicCourses = new HashSet<>();

    private Integer medicPayBonus = 200;

    //Specialist
    @ElementCollection
    @CollectionTable(name = "specialities", joinColumns = @JoinColumn(name = "soldier_id"))
    @Builder.Default
    private Set<String> specialities = new HashSet<>();

    private Integer specialitiesPayBonus = 100;

/*    @ValidSoldierSpecialization(SpecializationType.QUARTERMASTER)
    public void deliverAmmo(int amount) {
        System.out.println("Soldier will deliver: " + amount + " artillery rounds");
    }

    @ValidSoldierSpecialization(SpecializationType.MEDIC)
    public void provideFirstAid() {
        System.out.println("Providing first aid");
    }*/


/*    @ValidSoldierSpecialization(SpecializationType.QUARTERMASTER)
    public void setLoadCapacity(Integer loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @ValidSoldierSpecialization(SpecializationType.QUARTERMASTER)
    public void setSupplyPayBonus(Integer supplyPayBonus) {
        this.supplyPayBonus = supplyPayBonus;
    }

    @ValidSoldierSpecialization(SpecializationType.MEDIC)
    public void setMedicCourses(Set<String> medicCourses) {
        this.medicCourses = medicCourses;
    }

    @ValidSoldierSpecialization(SpecializationType.MEDIC)
    public void setMedicPayBonus(Integer medicPayBonus) {
        this.medicPayBonus = medicPayBonus;
    }

    @ValidSoldierSpecialization(SpecializationType.SPECIALIST)
    public void setSpecialities(Set<String> specialities) {
        this.specialities = specialities;
    }

    @ValidSoldierSpecialization(SpecializationType.SPECIALIST)
    public void setSpecialitiesPayBonus(Integer specialitiesPayBonus) {
        this.specialitiesPayBonus = specialitiesPayBonus;
    }*/
}
