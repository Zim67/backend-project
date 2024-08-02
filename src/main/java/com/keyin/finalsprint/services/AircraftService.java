package com.keyin.finalsprint.services;

import com.keyin.finalsprint.models.Aircraft;
import com.keyin.finalsprint.repositories.AircraftRepository;
import com.keyin.finalsprint.utils.UpdateableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService extends UpdateableService<Aircraft, Aircraft.Update> {

    @Autowired
    private AircraftRepository repository;


    @Override
    protected Aircraft create(Aircraft.Update data) {
        return new Aircraft(data.registrationNumber(), data.manufacturer(), data.model(), data.airline());
    }

    @Override
    protected ListCrudRepository<Aircraft, Long> repository() {
        return this.repository;
    }

    public List<Aircraft> find(String airline) {
        return repository.findByAirline(airline);
    }
}
