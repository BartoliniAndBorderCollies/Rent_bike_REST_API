package com.klodnicki.bike.rest.API.Bike.service;

import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.model.dto.BikeForNormalUserDTO;
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

    public Bike updateBikeAdmin(Long id, Bike bikeToUpdate) throws NotFoundInDatabaseException  {

        Bike bike = bikeRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);

        bike.setSerialNumber(bikeToUpdate.getSerialNumber());
        bike.setRented(bikeToUpdate.isRented());
        bike.setBikeType(bikeToUpdate.getBikeType());
        bike.setUser(bikeToUpdate.getUser()); //user doesn't work (400 bad request) -> it works, I forgot to put object
        //into object in JSON (user is an object and I passed String in postman)
        //if I updated a bike with user it didn't show up when I send GET request on a bike. the response was that
        //bike has a field user with null value. The reason for this was that @OneToOne relationship between User and
        // Bike, with User being the owning side of the relationship. This means that if I set the User on the Bike,
        // but don’t set the Bike on the User, then JPA won’t know about the relationship and won’t update the
        // database accordingly.
        // To fix this, I should also set the Bike on the User when I'm setting the User on the Bike:
        bikeToUpdate.getUser().setBike(bike);


        if(bikeToUpdate.isRented()) {
            if(bike.getChargingStation() != null) {
                bike.getChargingStation().getBikeList().remove(bikeToUpdate);
                int freeSlots = bike.getChargingStation().getFreeSlots();
                bike.getChargingStation().setFreeSlots(freeSlots+1);
            }
            bike.setChargingStation(null);
        } else {
            bike.setChargingStation(bikeToUpdate.getChargingStation());//doesn't save this information in db, now it does
            //I must have erased @JsonIgnore and use @JsonIdentityInfo instead
        }
        return bikeRepository.save(bike);
    }

    public BikeForNormalUserDTO updateBikeUser(Long id, Bike bikeToUpdate) throws NotFoundInDatabaseException {
        Bike bike = bikeRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);

        bike.setRented(bikeToUpdate.isRented());

        // Save the updated Bike object
        Bike savedBike = bikeRepository.save(bike);

        // Convert the saved Bike object to BikeForNormalUserDTO
        BikeForNormalUserDTO bikeDTO = new BikeForNormalUserDTO(savedBike.getSerialNumber(), savedBike.isRented(),
                savedBike.getBikeType());

        return bikeDTO;
    }
}
