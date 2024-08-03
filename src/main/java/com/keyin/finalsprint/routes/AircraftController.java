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
    public ResponseEntity<Aircraft> create(@RequestBody Aircraft.Update body) {
        return StatusCodes.with(service.add(body));
    }

    @PostMapping("aircrafts/{id}")
    public ResponseEntity<Aircraft> update(@PathVariable Long id, @RequestBody Aircraft.Update body) {
        return StatusCodes.with(service.update(id, body));
    }

    @DeleteMapping("aircrafts/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return !service.delete(id) ? StatusCodes.badRequest() : StatusCodes.noContent();
    }

    @GetMapping("aircrafts")
    public ResponseEntity<List<Aircraft>> get() {
        return StatusCodes.with(service.get());
    }

    @GetMapping("aircrafts/search")
    public ResponseEntity<List<Aircraft>> search(@RequestParam(required = false, name = "airline") String airline) {
        return StatusCodes.with(service.find(airline));
    }

}
