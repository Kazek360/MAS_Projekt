package com.example.projekt.repository;

import com.example.projekt.model.FireOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FireOrderRepository extends CrudRepository<FireOrder, Long> {

    @Query
    int countByArtillerySiteId(long artillerySiteId);
}