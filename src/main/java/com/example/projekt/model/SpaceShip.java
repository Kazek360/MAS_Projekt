package com.example.projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class SpaceShip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Ship name is mandatory")
    @Size(min = 2, max = 255)
    private String name;

    @Min(1)
    private Double mass;

    @Min(1)
    private Double fuel;

    @OneToMany(mappedBy = "spaceShip")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Sailor> sailors = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "SpaceShip_operations",
            joinColumns = @JoinColumn(name = "spaceShip_id"),
            inverseJoinColumns = @JoinColumn(name = "operations_id")
    )
    private Set<Operation> operations = new LinkedHashSet<>();

}
