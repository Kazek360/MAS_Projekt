package com.example.projekt.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@SuperBuilder
public class Carrier extends SpaceShip {
    @ElementCollection
    @CollectionTable(name = "FIGHTERS", joinColumns = @JoinColumn(name = "carrier_id"))
    @Builder.Default
    private Set<String> fighters = new HashSet<>();
}
