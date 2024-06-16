package com.example.projekt.model;

import com.example.projekt.model.AssociationsClasses.SpaceShip_Operation;
import com.example.projekt.model.Enums.OperationType;
import jakarta.persistence.*;
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
@SuperBuilder
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    private OperationType operationType;

    @NotBlank(message = "Sector is mandatory")
    @Size(min = 2, max = 255)
    private String sector;

    @NotBlank(message = "Planet is mandatory")
    @Size(min = 2, max = 255)
    private String planet;

    @OneToMany(mappedBy = "operation",fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Service> services = new LinkedHashSet<>();

    @OneToMany(mappedBy = "operation", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SpaceShip_Operation> spaceShip_Operations = new LinkedHashSet<>();


    @OneToMany(mappedBy = "operation")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Unit> units = new LinkedHashSet<>();

}
