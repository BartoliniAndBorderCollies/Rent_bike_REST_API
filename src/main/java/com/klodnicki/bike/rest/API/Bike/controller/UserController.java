package com.klodnicki.bike.rest.API.Bike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String goToUser() {
        return "login";
    }


}
