package com.keyin.finalsprint.services;

import com.keyin.finalsprint.models.Airport;
import com.keyin.finalsprint.repositories.AirportRepository;
import com.keyin.finalsprint.utils.UpdateableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService extends UpdateableService<Airport, Airport.Update> {

    @Autowired
    private AirportRepository repository;

    @Override
    protected Airport create(Airport.Update data) {
        return new Airport(data.code(), data.name(), data.city(), data.country());
    }

    @Override
    protected ListCrudRepository<Airport, Long> repository() {
        return this.repository;
    }

    @Override
    public Airport add(Airport.Update data) {
        if (repository.findByCode(data.code()).isPresent()) return null;
        return super.add(data);
    }

    public Optional<Airport> findById(Long id) {
        return repository.findById(id);
    }

    public List<Airport> find(String name) {
        return repository.findByNameContaining(name);
    }
}
