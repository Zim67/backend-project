package com.keyin.finalsprint.repositories;

import com.keyin.finalsprint.models.Flight;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends ListCrudRepository<Flight, Long> {

}
