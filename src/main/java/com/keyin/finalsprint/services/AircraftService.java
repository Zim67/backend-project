package com.keyin.finalsprint.services;

import com.keyin.finalsprint.models.Aircraft;
import com.keyin.finalsprint.repositories.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository repository;

    public Aircraft add(Aircraft.Updated data) {
        Aircraft aircraft = new Aircraft(data.registrationNumber(), data.manufacturer(), data.model(), data.airline());
        return repository.save(aircraft);
    }

    public Aircraft update(long id, Aircraft.Updated data) {
        Aircraft aircraft = repository.findById(id).orElse(null);
        if (aircraft == null) return null;
        if (data.registrationNumber() != null) aircraft.setRegistrationNumber(data.registrationNumber());
        if (data.manufacturer() != null) aircraft.setManufacturer(data.manufacturer());
        if (data.model() != null) aircraft.setModel(data.model());
        if (data.airline() != null) aircraft.setAirline(data.airline());
        return repository.save(aircraft);
    }

    public boolean delete(long id) {
        if (repository.findById(id).isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }

    public List<Aircraft> get() {
        return repository.findAll();
    }

    public List<Aircraft> find(String airline) {
        return repository.findByAirline(airline);
    }
}
