package com.example.projekt.repository;

import com.example.projekt.model.ArtillerySite;
import com.example.projekt.model.SupplyStation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SupplyStationRepository extends CrudRepository<SupplyStation, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE SupplyStation s SET s.ammunition = :newAmmo WHERE s.id = :supplyStationId")
    void updateAmmunition(@Param("supplyStationId") Long supplyStationId, @Param("newAmmo") int newAmmo);

    @Query("SELECT a FROM ArtillerySite a JOIN a.supplyStations s WHERE s.id = :supplyStationId")
    List<ArtillerySite> findArtillerySitesBySupplyStationId(@Param("supplyStationId") Long supplyStationId);

}
