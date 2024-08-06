package com.keyin.finalsprint.services;

import com.keyin.finalsprint.models.Flight;
import com.keyin.finalsprint.repositories.FlightRepository;
import com.keyin.finalsprint.utils.UpdateableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightService extends UpdateableService<Flight, Flight.Update> {

    @Autowired
    private FlightRepository repository;

    @Override
    protected Flight create(Flight.Update data) {
        Flight flight = new Flight();
        flight.update(data);
        return flight;
    }

    @Override
    protected ListCrudRepository<Flight, Long> repository() {
        return this.repository;
    }
}
