package com.example.projekt.model;

import com.example.projekt.model.Enums.OperationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated
    @Column(name = "operation_type", nullable = false)
    private OperationType operationType;

    @NotBlank(message = "Sector is mandatory")
    @Size(min = 2, max = 255)
    private String sector;

    @NotBlank(message = "Planet is mandatory")
    @Size(min = 2, max = 255)
    private String planet;

    @ManyToMany
    @JoinTable(name = "Operation_spaceShips",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "spaceShips_id"))
    private Set<SpaceShip> spaceShips = new LinkedHashSet<>();

    public Set<SpaceShip> getSpaceShips() {
        return spaceShips;
    }

    public void setSpaceShips(Set<SpaceShip> spaceShips) {
        this.spaceShips = spaceShips;
    }
}
