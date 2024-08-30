package com.example.projekt.model;

import com.example.projekt.model.Enums.OperationalStatus;
import com.example.projekt.model.Enums.Rank;
import com.example.projekt.model.Enums.SeaRank;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
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
public class Marine extends Infantryman implements ISailor{

    @PositiveOrZero
    private Integer currentMissionsNumber;

    @Enumerated
    @Column(name = "sea_rank", nullable = false)
    private OperationalStatus operationalStatus;

    @Enumerated
    @Column(name = "operational_status", nullable = false)
    private SeaRank seaRank;

    @Override
    public SeaRank getSeaRank() {
        return seaRank;
    }

    @Override
    public void setSeaRank(SeaRank rank) {
    }

    @Override
    public SpaceShip getSpaceShip() {
        return null;
    }

    @Override
    public void setSpaceShip(SpaceShip spaceship) {

    }
}
