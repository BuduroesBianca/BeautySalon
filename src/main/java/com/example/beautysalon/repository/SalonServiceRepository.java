package com.example.beautysalon.repository;

import com.example.beautysalon.model.SalonService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalonServiceRepository extends CrudRepository<SalonService, Long> {

    public Optional<SalonService> findByName(String name);
}
