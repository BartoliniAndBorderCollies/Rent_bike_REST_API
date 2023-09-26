package com.klodnicki.bike.rest.API.Bike.rest.controller;

import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.model.entity.Bike;
import com.klodnicki.bike.rest.API.Bike.service.BikeService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Iterable<Bike> findAllBikes() {
        return bikeService.findAllBikes();
    }

    @GetMapping("/{id}")
    public Bike findBikeById(@PathVariable("id") Long id) throws NotFoundInDatabaseException {
        return bikeService.findBikeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBikeById(@PathVariable("id") Long id) throws NotFoundInDatabaseException {
        bikeService.deleteById(id);
    }




}
