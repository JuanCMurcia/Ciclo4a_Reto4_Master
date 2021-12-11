package com.usa.repository;

import com.usa.interfaces.UserInterface;
import com.usa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserInterface crudInterface;

    public List<User> getAll() {
        return (List<User>) crudInterface.findAll();
    }

    public Optional<User> getUser(int id){
        return crudInterface.findById(id);
    }

    public User create(User user){
        return crudInterface.save(user);
    }

    public void update(User user){
        crudInterface.save(user);
    }

    public void delete(User user){
        crudInterface.delete(user);
    }

    public boolean existeEmail(String email) {
        Optional<User> usuario = crudInterface.findByEmail(email);
        return !usuario.isEmpty();
    }

    public Optional<User> autenticateUser(String email, String password) {
        return crudInterface.findByEmailAndPassword(email, password);
    }

    public Optional<User>lastUserId(){
        return crudInterface.findTopByOrderByIdDesc();
    }
}
