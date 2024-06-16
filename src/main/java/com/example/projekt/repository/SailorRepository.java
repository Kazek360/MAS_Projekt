package com.example.projekt.repository;

import com.example.projekt.model.Sailor;
import org.springframework.data.repository.CrudRepository;

public interface SailorRepository extends CrudRepository<Sailor, Long> {
}