package com.keyin.finalsprint.routes;

import com.keyin.finalsprint.models.User;
import com.keyin.finalsprint.routes.bodies.LoginBody;
import com.keyin.finalsprint.routes.bodies.TokenResponse;
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
        User user = service.get(id);
        if (user == null) return StatusCodes.notFound();
        return StatusCodes.with(user.toBasic());
    }

    @PostMapping("users/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginBody body) {
        return StatusCodes.with(service.login(body.email(), body.password()));
    }

    @PostMapping("users/create")
    public ResponseEntity<User> create(@RequestBody User.Update data) {
        return StatusCodes.with(service.add(data), User::toBasic);
    }

    @PutMapping("users/{id}/password")
    public ResponseEntity<User> password(@PathVariable Long id, @RequestHeader("Authorization") String session, @RequestBody String password) {
        User user = service.get(id);
        if (user == null) return StatusCodes.badRequest();
        if (!user.isSession(session)) return StatusCodes.unauthorized();
        user = service.update(id, new User.Update(null, null, null, null, password));
        return StatusCodes.with(user, User::toBasic);
    }

}
