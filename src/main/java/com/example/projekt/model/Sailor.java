package com.example.projekt.model;

import com.example.projekt.model.Enums.Rank;
import com.example.projekt.model.Enums.SeaRank;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Sailor extends Soldier implements ISailor {
    @Enumerated(EnumType.STRING)
    @Column(name = "searank", nullable = false)
    private SeaRank seaRank;

    @ManyToOne(optional = false)
    @JoinColumn(name = "space_ship_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SpaceShip spaceShip;


    @Override
    public SeaRank getSeaRank() {
        return this.seaRank;
    }

    @Override
    public void setSeaRank(SeaRank rank) {
        this.seaRank = rank;
    }

    @Override
    public SpaceShip getSpaceShip() {
        return this.spaceShip;
    }

    @Override
    public void setSpaceShip(SpaceShip spaceship) {
        this.spaceShip = spaceship;
    }
}
