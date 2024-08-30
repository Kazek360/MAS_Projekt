package com.example.projekt.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Infantryman extends Soldier {
    @ElementCollection
    @CollectionTable(name = "Equipment", joinColumns = @JoinColumn(name = "infantryman_id"))
    @Builder.Default
    private Set<String> equipment = new HashSet<>();


    @ManyToOne(optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Unit unit;


    @OneToOne(mappedBy = "commander")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Unit commandingUnit;

}
