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
    public ResponseEntity<Airport> create(@RequestBody Airport.Update body) {
        return StatusCodes.with(service.add(body));
    }

    @PostMapping("airports/{id}")
    public ResponseEntity<Airport> update(@PathVariable Long id, @RequestBody Airport.Update body) {
        return StatusCodes.with(service.update(id, body));
    }

    @DeleteMapping("airports/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return !service.delete(id) ? StatusCodes.badRequest() : StatusCodes.noContent();
    }

    @GetMapping("airports")
    public ResponseEntity<List<Airport>> get() {
        return StatusCodes.with(service.get());
    }

    @GetMapping("airports/search")
    public ResponseEntity<List<Airport>> search(@RequestParam(required = false, name = "name") String name) {
        return StatusCodes.with(service.find(name));
    }
}
