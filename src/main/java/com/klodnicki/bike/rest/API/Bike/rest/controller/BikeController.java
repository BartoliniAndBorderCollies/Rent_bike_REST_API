package com.klodnicki.bike.rest.API.Bike.rest.controller;

import com.klodnicki.bike.rest.API.Bike.exception.UnauthorizedException;
import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.model.dto.BikeForNormalUserDTO;
import com.klodnicki.bike.rest.API.Bike.model.entity.Bike;
import com.klodnicki.bike.rest.API.Bike.service.BikeService;
import jakarta.validation.Valid;
import org.springframework.security.core.GrantedAuthority;
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

    @PutMapping("/admin/{id}")
    public Bike adminUpdateBike(@PathVariable Long id, @RequestBody @Valid Bike bikeToUpdate,
           @AuthenticationPrincipal UserDetails user) throws NotFoundInDatabaseException, UnauthorizedException {

//        if (user.getUsername().equals("admin")) {
//            return bikeService.updateBikeAdmin(id, bikeToUpdate);
//        }

        for (GrantedAuthority ga : user.getAuthorities()) {
            if (ga.getAuthority().equals("ROLE_ADMIN")) {
                return bikeService.updateBikeAdmin(id, bikeToUpdate);
            }
        }

        throw new UnauthorizedException();
    }

    @PutMapping("/user/{id}")
    public BikeForNormalUserDTO userUpdateBike(@PathVariable Long id, @RequestBody @Valid Bike bikeToUpdate,
       @AuthenticationPrincipal UserDetails user) throws UnauthorizedException, NotFoundInDatabaseException {

//        if(user.getUsername().equals("user")) {
//            return bikeService.updateBikeUser(id, bikeToUpdate);
//        }

        for (GrantedAuthority ga : user.getAuthorities()) {
            if (ga.getAuthority().equals("ROLE_USER")) {
                return bikeService.updateBikeUser(id, bikeToUpdate);
            }
        }

        throw new UnauthorizedException();
    }

}
