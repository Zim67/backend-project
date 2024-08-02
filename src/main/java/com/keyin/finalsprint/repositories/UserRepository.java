package com.keyin.finalsprint.repositories;

import com.keyin.finalsprint.models.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

    public Optional<User> findByEmail(String email);
}
