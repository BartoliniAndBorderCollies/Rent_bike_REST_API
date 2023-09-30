package com.klodnicki.bike.rest.API.Bike.rest.controller;

import com.klodnicki.bike.rest.API.Bike.exception.UnauthorizedException;
import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.model.entity.Bike;
import com.klodnicki.bike.rest.API.Bike.service.BikeService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bike")
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @PostMapping("/add")
    public Bike addBike(@RequestBody @Valid Bike bike) {
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

    @PutMapping("/{id}")
    public Bike updateBike(@PathVariable Long id, @RequestBody @Valid Bike bikeToUpdate,
           @AuthenticationPrincipal UserDetails user) throws NotFoundInDatabaseException, UnauthorizedException {

        if (user.getUsername().equals("admin")) {
            return bikeService.updateBikeAdmin(id, bikeToUpdate);
        }
        if (user.getUsername().equals("user")) {
            return bikeService.updateBikeUser(id, bikeToUpdate);
        }

        throw new UnauthorizedException();
    }
}
