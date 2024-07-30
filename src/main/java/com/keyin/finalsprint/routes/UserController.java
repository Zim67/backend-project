package com.keyin.finalsprint.routes;

import com.keyin.finalsprint.models.User;
import com.keyin.finalsprint.routes.bodies.LoginBody;
import com.keyin.finalsprint.services.UserService;
import com.keyin.finalsprint.utils.StatusCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return ResponseEntity.ofNullable(service.getUserById(id));
    }

    @PostMapping("users/login")
    public ResponseEntity<Long> login(@RequestBody LoginBody body) {
        User user = service.loginWithEmailAndPassword(body.email(), body.password());
        if (user == null) return StatusCodes.badRequest();
        return ResponseEntity.ofNullable(user.getId());
    }

    @PostMapping("users/create")
    public ResponseEntity<Void> create(@RequestBody User.Updated updated) {
        if (!updated.isFull()) return StatusCodes.badRequest(); // Make sure that the request body has all values
        if (updated.admin()) return StatusCodes.forbidden(); // Make sure that an admin cant be created on registration
        if (service.getUserByEmail(updated.email()) != null) return StatusCodes.badRequest(); // Make sure the email doesn't already exist
        if (service.addUser(updated) == null) return StatusCodes.badRequest();
        return StatusCodes.noContent();
    }

    @PutMapping("users/{id}/password")
    public ResponseEntity<Void> password(@PathVariable Long id, @RequestBody String password) {
        User user = service.updateUser(id, User.Updated.password(password));
        return user == null ? StatusCodes.badRequest() : StatusCodes.created();
    }

}
