package com.example.projekt.model;

import com.example.projekt.model.Enums.SeaRank;

public interface ISailor {
    SeaRank getSeaRank();
    void setSeaRank(SeaRank rank);
    SpaceShip getSpaceShip();
    void setSpaceShip(SpaceShip spaceship);
}
