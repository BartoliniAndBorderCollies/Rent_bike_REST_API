package com.klodnicki.bike.rest.API.Bike.service;

import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.model.entity.User;
import com.klodnicki.bike.rest.API.Bike.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) throws NotFoundInDatabaseException {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.orElseThrow(NotFoundInDatabaseException::new);

    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
}
