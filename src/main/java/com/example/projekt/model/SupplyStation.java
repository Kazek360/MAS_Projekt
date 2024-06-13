package com.example.projekt.model;

import com.example.projekt.model.AssociationsClasses.ArtillerySite_SupplyStation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplyStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PositiveOrZero
    private Integer capacity;

    @NotBlank(message = "Location is mandatory")
    @Size(min = 2, max = 255)
    private String location;

    @ElementCollection
    @CollectionTable(name = "SUPPLIES", joinColumns = @JoinColumn(name = "supplySite_id"))
    @Builder.Default
    private Set<String> fighters = new HashSet<>();

    @OneToMany(mappedBy = "supplyStation", orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ArtillerySite_SupplyStation> artillerySite_SupplyStations = new LinkedHashSet<>();

}
