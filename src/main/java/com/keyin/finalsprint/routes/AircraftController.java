package com.keyin.finalsprint.routes;

import com.keyin.finalsprint.models.Aircraft;
import com.keyin.finalsprint.services.AircraftService;
import com.keyin.finalsprint.utils.StatusCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftService service;

    @PostMapping("aircrafts/create")
    public ResponseEntity<Aircraft> create(@RequestBody Aircraft.Updated body) {
        if (!body.isFull()) return StatusCodes.badRequest();
        Aircraft aircraft = service.add(body);
        if (aircraft == null) return StatusCodes.badRequest();
        return ResponseEntity.ofNullable(aircraft);
    }

    @PostMapping("aircrafts/{id}")
    public ResponseEntity<Aircraft> update(@PathVariable Long id, @RequestBody Aircraft.Updated body) {
        if (id == null) return StatusCodes.badRequest();
        Aircraft aircraft = service.update(id, body);
        if (aircraft == null) return StatusCodes.badRequest();
        return ResponseEntity.ofNullable(aircraft);
    }

    @DeleteMapping("aircrafts/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id == null) return StatusCodes.badRequest();
        if (!service.delete(id)) return StatusCodes.badRequest();
        return StatusCodes.noContent();
    }

    @GetMapping("aircrafts")
    public ResponseEntity<List<Aircraft>> get() {
        return ResponseEntity.ofNullable(service.get());
    }

    @GetMapping("aircrafts/search")
    public ResponseEntity<List<Aircraft>> search(@RequestParam(required = false, name = "airline") String airline) {
        return ResponseEntity.ofNullable(service.find(airline));
    }

}
