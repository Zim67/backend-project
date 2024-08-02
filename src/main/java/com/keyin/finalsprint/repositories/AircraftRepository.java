package com.keyin.finalsprint.repositories;

import com.keyin.finalsprint.models.Aircraft;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends ListCrudRepository<Aircraft, Long> {

    public List<Aircraft> findByAirline(String airline);
}
