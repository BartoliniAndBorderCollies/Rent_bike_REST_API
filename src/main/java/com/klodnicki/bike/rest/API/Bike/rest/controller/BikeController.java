package com.klodnicki.bike.rest.API.Bike.rest.controller;

import com.klodnicki.bike.rest.API.Bike.model.entity.Bike;
import com.klodnicki.bike.rest.API.Bike.service.BikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bike")
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }


    @PostMapping("/add")
    public Bike addBike(@RequestBody Bike bike) {
        return bikeService.addBike(bike);
    }




}
