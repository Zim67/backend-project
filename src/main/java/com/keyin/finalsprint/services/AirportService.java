package com.keyin.finalsprint.services;

import com.keyin.finalsprint.models.Airport;
import com.keyin.finalsprint.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    @Autowired
    private AirportRepository repository;

    public Airport addAirport(Airport.Updated data) {
        if (repository.findByCode(data.code()).isPresent()) return null;
        Airport airport = new Airport(data.code(), data.name(), data.city(), data.country());
        return repository.save(airport);
    }

    public Airport updateAirport(long id, Airport.Updated data) {
        Airport airport = repository.findById(id).orElse(null);
        if (airport == null) return null;
        if (data.code() != null) airport.setCode(data.code());
        if (data.name() != null) airport.setName(data.name());
        if (data.city() != null) airport.setCity(data.city());
        if (data.country() != null) airport.setCountry(data.country());
        return repository.save(airport);
    }

    public void deleteAirport(long id) {
        repository.deleteById(id);
    }

    public List<Airport> get() {
        return repository.findAll();
    }

    public List<Airport> find(String name) {
        return repository.findByNameContaining(name);
    }
}
