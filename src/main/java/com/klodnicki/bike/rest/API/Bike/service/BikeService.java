package com.klodnicki.bike.rest.API.Bike.service;

import com.klodnicki.bike.rest.API.Bike.model.entity.Bike;
import com.klodnicki.bike.rest.API.Bike.repository.BikeRepository;
import org.springframework.stereotype.Service;


@Service
public class BikeService {

    private final BikeRepository bikeRepository;


    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public Iterable<Bike> findAllBikes() {
        return bikeRepository.findAll();
    }
}
