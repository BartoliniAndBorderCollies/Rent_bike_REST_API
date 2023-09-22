package com.klodnicki.bike.rest.API.Bike.controller;

import com.klodnicki.bike.rest.API.Bike.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

//    private final User user;
//
//    public UserController(User user) {
//        this.user = user;
//    }

    @GetMapping("/api/login")
    public String goToUser(Model model) {
//        model.addAttribute("user", user);

        return "login";
    }


}
