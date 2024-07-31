package com.keyin.finalsprint.repositories;

import com.keyin.finalsprint.models.Airport;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends ListCrudRepository<Airport, Long> {
    public Optional<Airport> findByCode(String code);
}
