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
        return userRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) throws NotFoundInDatabaseException {
        User user = userRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);
        userRepository.delete(user);
    }

    public User updateUser(Long id, User userToUpdate) throws NotFoundInDatabaseException {
        User user = userRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);

        user.setName(userToUpdate.getName());
        user.setPhoneNumber(userToUpdate.getPhoneNumber());
        user.setAccountNumber(userToUpdate.getAccountNumber());
        user.setLogin(userToUpdate.getLogin());
        user.setPassword(userToUpdate.getPassword());
        user.setAccountValid(userToUpdate.isAccountValid());
        user.setRole(userToUpdate.getRole());
        return userRepository.save(user);
    }

    public boolean checkIfUserExistInDatabase(Long id) throws NotFoundInDatabaseException {
        userRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);
        return true;
    }
}
