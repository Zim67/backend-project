package com.keyin.finalsprint.routes;

import com.keyin.finalsprint.models.Flight;
import com.keyin.finalsprint.routes.bodies.FlightsUpdateBody;
import com.keyin.finalsprint.services.AircraftService;
import com.keyin.finalsprint.services.AirportService;
import com.keyin.finalsprint.services.FlightService;
import com.keyin.finalsprint.utils.StatusCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FlightController {

    @Autowired private FlightService service;
    @Autowired private AircraftService aircrafts;
    @Autowired private AirportService airports;

    @PostMapping("flights/create")
    public ResponseEntity<Flight> create(@RequestBody FlightsUpdateBody body) {
        return StatusCodes.with(service.add(body.toUpdate(aircrafts, airports)));
    }

    @PostMapping("flights/{id}")
    public ResponseEntity<Flight> update(@PathVariable Long id, @RequestBody FlightsUpdateBody body) {
        return StatusCodes.with(service.update(id, body.toUpdate(aircrafts, airports)));
    }

    @DeleteMapping("flights/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return !service.delete(id) ? StatusCodes.badRequest() : StatusCodes.noContent();
    }

    @GetMapping("flights")
    public ResponseEntity<List<Flight>> get() {
        return StatusCodes.with(service.get());
    }
}
