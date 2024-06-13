package com.example.projekt.model;

import com.example.projekt.model.Enums.ArtillerySiteState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtillerySite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(1)
    private Integer cannons;

    @PositiveOrZero
    private Integer ammunition;

    @NotBlank(message = "Location is mandatory")
    @Size(min = 2, max = 255)
    private String location;

    @Enumerated
    @Column(name = "artillery_state", nullable = false)
    private ArtillerySiteState artillerySiteState;

    @OneToMany(mappedBy = "artillerySite",fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Service> services = new LinkedHashSet<>();

}
