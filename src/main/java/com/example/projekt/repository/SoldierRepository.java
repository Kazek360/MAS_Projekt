package com.example.projekt.repository;

import com.example.projekt.model.Soldier;
import org.springframework.data.repository.CrudRepository;

public interface SoldierRepository extends CrudRepository<Soldier, Long> {
}