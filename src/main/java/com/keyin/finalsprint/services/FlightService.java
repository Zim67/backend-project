package com.keyin.finalsprint.services;

import com.keyin.finalsprint.models.Airport;
import com.keyin.finalsprint.models.Flight;
import com.keyin.finalsprint.repositories.FlightRepository;
import com.keyin.finalsprint.utils.UpdateableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // Method to get departures from a specific airport
    public List<Flight> getDeparturesFromAirport(Airport airport) {
        return repository.findByFrom(airport);
    }

    // Method to get arrivals at a specific airport
    public List<Flight> getArrivalsAtAirport(Airport airport) {
        return repository.findByTo(airport);
    }
}
