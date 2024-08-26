package com.example.projekt.model;

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

    @PositiveOrZero
    private Integer ammunition;

    @NotBlank(message = "Location is mandatory")
    @Size(min = 2, max = 255)
    private String location;

    @ElementCollection
    @CollectionTable(name = "SUPPLIES", joinColumns = @JoinColumn(name = "supplySite_id"))
    @Builder.Default
    private Set<String> supplies = new HashSet<>();

    @ManyToMany(mappedBy = "supplyStations", cascade = CascadeType.MERGE)
    private Set<ArtillerySite> artillerySites = new LinkedHashSet<>();


}
