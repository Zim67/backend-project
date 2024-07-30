package com.keyin.finalsprint.services;

import com.keyin.finalsprint.models.User;
import com.keyin.finalsprint.repositories.UserRepository;
import com.keyin.finalsprint.utils.Optionull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User loginWithEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return null;
        if (!user.isPassword(password)) return null;
        return user;
    }

    public User updateUser(long id, User.Updated updated) {
        User user = getUserById(id);
        if (user == null) return null;
        if (updated.email() != null) user.setEmail(updated.email());
        if (updated.firstName() != null) user.setFirstName(updated.firstName());
        if (updated.lastName() != null) user.setLastName(updated.lastName());
        if (updated.password() != null && !user.setPassword(updated.password())) return null;
        if (updated.admin() != null) user.setAdmin(updated.admin());
        return userRepository.save(user);
    }

    public User addUser(User.Updated updated) {
        return userRepository.save(updated.asUser());
    }

    public String createSession(User user) {
        String session = user.createSession();
        userRepository.save(user);
        return session;
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
