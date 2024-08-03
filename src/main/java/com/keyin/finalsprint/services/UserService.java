package com.keyin.finalsprint.services;

import com.keyin.finalsprint.models.User;
import com.keyin.finalsprint.repositories.UserRepository;
import com.keyin.finalsprint.routes.bodies.TokenResponse;
import com.keyin.finalsprint.utils.UpdateableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends UpdateableService<User, User.Update> {

    @Autowired
    private UserRepository repository;

    @Override
    protected ListCrudRepository<User, Long> repository() {
        return this.repository;
    }

    @Override
    protected User create(User.Update data) {
        return new User(data.email(), data.firstName(), data.lastName(), data.admin(), data.password());
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public User add(User.Update data) {
        if (Boolean.TRUE.equals(data.admin())) return null;
        if (getUserByEmail(data.email()) != null) return null;
        return super.add(data);
    }

    public TokenResponse login(String email, String password) {
        User user = repository.findByEmail(email).orElse(null);
        if (user == null) return null;
        if (!user.isPassword(password)) return null;
        String session = user.createSession();
        return new TokenResponse(user.getId(), session);
    }
}
