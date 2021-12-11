package com.usa.service;

import com.usa.model.User;
import com.usa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> getUser(int id) {
        return userRepository.getUser(id);
    }

    public User create(User user) {
        Optional<User> userIdMaximo = userRepository.lastUserId();

        if (user.getId() == null) {
            if (userIdMaximo.isEmpty()) {
                user.setId(1);
            } else {
                user.setId(userIdMaximo.get().getId() + 1);
            }
        }

            Optional<User> evc = userRepository.getUser(user.getId());
            if (evc.isEmpty()) {
                if (existeEmail(user.getEmail()) == false) {
                    return userRepository.create(user);
                } else {
                    return user;
                }
            }else {
                return user;
            }
    }

    public User update(User user) {
        if (user.getId() != null) {
            Optional<User> userDb = userRepository.getUser(user.getId());
            if (!userDb.isEmpty()) {
                if (user.getIdentification() != null) {
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userDb.get().setName(user.getName());
                }
                if (user.getBirthtDay() != null){
                    userDb.get().setBirthtDay(user.getBirthtDay());
                }
                if (user.getMonthBirthtDay() != null){
                    userDb.get().setMonthBirthtDay(user.getMonthBirthtDay());
                }
                if (user.getAddress() != null) {
                    userDb.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userDb.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userDb.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    userDb.get().setZone(user.getZone());
                }
                if (user.getType() != null) {
                    userDb.get().setType(user.getType());
                }

                userRepository.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    public boolean delete(int userId) {
        boolean del = getUser(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return del;
    }

    public boolean existeEmail(String email) {
        return userRepository.existeEmail(email);
    }

    public User autenticateUser(String email, String password) {
        Optional<User> usuario = userRepository.autenticateUser(email, password);

        if (usuario.isEmpty()) {
            return new User();
        } else {
            return usuario.get();
        }

    }
}
