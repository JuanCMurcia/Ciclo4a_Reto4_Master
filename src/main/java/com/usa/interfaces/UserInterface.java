package com.usa.interfaces;

import com.usa.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserInterface extends MongoRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findTopByOrderByIdDesc();
}
