package com.example.projekt.model.AssociationsClasses;

import com.example.projekt.model.Operation;
import com.example.projekt.model.SpaceShip;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpaceShip_Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "space_ship_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SpaceShip spaceShip;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Operation operation;

}
