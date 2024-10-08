package com.example.projekt.repository;

import com.example.projekt.model.ArtillerySite;
import com.example.projekt.model.SupplyStation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface ArtillerySiteRepository extends CrudRepository<ArtillerySite, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE ArtillerySite a SET a.ammunition = :newAmmo WHERE a.id = :artillerySiteId")
    void updateAmmunition(@Param("artillerySiteId") Long artillerySiteId, @Param("newAmmo") int newAmmo);

    @Query("SELECT s FROM SupplyStation s JOIN s.artillerySites a WHERE a.id = :artillerySiteId")
    List<SupplyStation> findSupplyStationsByArtillerySiteId(@Param("artillerySiteId") Long artillerySiteId);





}