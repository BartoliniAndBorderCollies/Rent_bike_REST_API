package com.klodnicki.bike.rest.API.Bike.rest.controller;

import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.model.entity.User;
import com.klodnicki.bike.rest.API.Bike.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}") //dostawałem 400 bad request bo była zła adnotacja, była @RequestParam a powinna być @PathVariable
    // i wtedy daję: localhost:8888/api/user/2
    public User findUserById (@PathVariable("id") Long id) throws NotFoundInDatabaseException {
        return userService.findUserById(id);
    }

    @GetMapping
    public Iterable<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById (@PathVariable("id") Long id) throws NotFoundInDatabaseException {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) throws NotFoundInDatabaseException {
       return userService.updateUser(id, user);
    }





}
