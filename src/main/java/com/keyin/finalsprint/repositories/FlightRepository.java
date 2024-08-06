package com.keyin.finalsprint.repositories;

import com.keyin.finalsprint.models.Airport;
import com.keyin.finalsprint.models.Flight;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends ListCrudRepository<Flight, Long> {
    // Method to find flights departing from a specific airport
    List<Flight> findByFrom(Airport airport);

    // Method to find flights arriving at a specific airport
    List<Flight> findByTo(Airport airport);
}
