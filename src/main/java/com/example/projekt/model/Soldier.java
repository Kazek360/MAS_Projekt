package com.example.projekt.model;

import com.example.projekt.model.Enums.Rank;
import com.example.projekt.model.Enums.TrainingLevel;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.UniqueElements;

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

    @NotBlank(message = "Rank is mandatory")
    @Enumerated
    @Column(name = "rank", nullable = false)
    private Rank rank;

    @NotBlank(message = "Training level is mandatory")
    @Enumerated
    @Column(name = "training_level", nullable = false)
    private TrainingLevel trainingLevel;

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
}
