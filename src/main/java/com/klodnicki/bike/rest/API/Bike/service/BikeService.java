package com.klodnicki.bike.rest.API.Bike.service;

import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
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

    public Bike findBikeById(Long id) throws NotFoundInDatabaseException {
        return bikeRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);
    }

    public void deleteById(Long id) throws NotFoundInDatabaseException {
        Bike bikeToDelete = bikeRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);
        bikeRepository.delete(bikeToDelete);
    }

    public Bike updateBike(Long id, Bike bikeToUpdate) throws NotFoundInDatabaseException {
        Bike bike = bikeRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);

        bike.setSerialNumber(bikeToUpdate.getSerialNumber());
        bike.setRented(bikeToUpdate.isRented());

        return bikeRepository.save(bike);
    }

    public Bike rentBike(Long id, Bike bikeToRent) throws NotFoundInDatabaseException {
        Bike bike = bikeRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);

        bike.setRented(true);
        bike.setUser(bikeToRent.getUser());
        bike.setChargingStation(null);
        bike.getChargingStation().getBikeList().remove(bikeToRent);
        int freeSlots = bike.getChargingStation().getFreeSlots();
        bike.getChargingStation().setFreeSlots(freeSlots+1);

        return bikeRepository.save(bike);
    }
}
