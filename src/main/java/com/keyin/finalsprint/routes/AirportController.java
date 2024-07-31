package com.keyin.finalsprint.routes;

import com.keyin.finalsprint.models.Airport;
import com.keyin.finalsprint.services.AirportService;
import com.keyin.finalsprint.utils.StatusCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportService service;

    @PostMapping("airports/create")
    public ResponseEntity<Airport> create(@RequestBody Airport.Updated body) {
        if (!body.isFull()) return StatusCodes.badRequest();
        Airport airport = service.addAirport(body);
        if (airport == null) return StatusCodes.badRequest();
        return ResponseEntity.ofNullable(airport);
    }

    @PostMapping("airports/{id}")
    public ResponseEntity<Airport> update(@PathVariable Long id, @RequestBody Airport.Updated body) {
        if (id == null) return StatusCodes.badRequest();
        Airport airport = service.updateAirport(id, body);
        if (airport == null) return StatusCodes.badRequest();
        return ResponseEntity.ofNullable(airport);
    }

    @DeleteMapping("airports/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        if (id == null) return StatusCodes.badRequest();
        service.deleteAirport(id);
        return StatusCodes.noContent();
    }

    @GetMapping("airports")
    public ResponseEntity<List<Airport>> get() {
        return ResponseEntity.ofNullable(service.get());
    }

    @GetMapping("airports/search")
    public ResponseEntity<List<Airport>> search(@RequestParam(required = false, name = "name") String name) {
        return ResponseEntity.ofNullable(service.find(name));
    }
}
