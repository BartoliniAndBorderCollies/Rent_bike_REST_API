package com.klodnicki.bike.rest.API.Bike.service;

import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.model.dto.BikeForNormalUserDTO;
import com.klodnicki.bike.rest.API.Bike.model.entity.Bike;
import com.klodnicki.bike.rest.API.Bike.model.entity.ChargingStation;
import com.klodnicki.bike.rest.API.Bike.model.entity.User;
import com.klodnicki.bike.rest.API.Bike.repository.BikeRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;
    private final UserService userService;
    private final ChargingStationService chargingStationService;

    @Autowired
    private EntityManager entityManager;

    public BikeService(BikeRepository bikeRepository, UserService userService, ChargingStationService chargingStationService) {
        this.bikeRepository = bikeRepository;
        this.userService = userService;
        this.chargingStationService = chargingStationService;
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
@Transactional
    public Bike updateBikeAdmin(Long id, Bike bikeToUpdate) throws NotFoundInDatabaseException  {

        Bike bike = bikeRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);

        bike.setSerialNumber(bikeToUpdate.getSerialNumber());
        bike.setRented(bikeToUpdate.isRented());
        bike.setBikeType(bikeToUpdate.getBikeType());

        //check if provided user is null, if yes throw an exception
        User userToBeAssigned = bikeToUpdate.getUser();
        if(userToBeAssigned == null) {
            throw new NotFoundInDatabaseException();
        }

        //check if provided station is null, if yes throw an exception
        ChargingStation chargingStationToBeAssigned = bikeToUpdate.getChargingStation();
        if(chargingStationToBeAssigned == null) {
            throw new NotFoundInDatabaseException();
        }

        // check if provided user exists. if not throw an exception if yes follow the logic:
        // set user to the bike -> setting on the both sides because relation @OneToOne with owning on one side
        if (userService.checkIfUserExistInDatabase(userToBeAssigned.getId())) {
            bike.setUser(userToBeAssigned);
            bikeToUpdate.getUser().setBike(bike);
        }

//     logic if bike is rented->removing bike from station, free slots of the station+1,
//      make charging station field at this bike null
        if(bikeToUpdate.isRented()) {
            if(bike.getChargingStation() != null) {
                bike.getChargingStation().getBikeList().remove(bikeToUpdate);
                int freeSlots = bike.getChargingStation().getFreeSlots();
                bike.getChargingStation().setFreeSlots(freeSlots+1);
            }
            bike.setChargingStation(null);

        //logic if bike is NOT rented -> checking if provided station exists, if no -> throws exception if yes logic:
        // 1. set the charging station to the bike
        // 2. merge the detached instance back into the current session by using the merge() method provided by the EntityManager
        // 3. adds @Transactional to the method. This annotation will start a new transaction before the method is executed
        // and commit it after the method returns.
        // 4. remove user from the bike, user field at the bike should be null
        } if(!bikeToUpdate.isRented()) {
            if(chargingStationService.checkIfChargingStationExistInDatabase(chargingStationToBeAssigned.getId())) {
                ChargingStation managedChargingStation = entityManager.merge(bikeToUpdate.getChargingStation());

                bike.setChargingStation(managedChargingStation);
                bike.setChargingStation(bikeToUpdate.getChargingStation());

                // Get the User object associated with this Bike
                User user = bike.getUser();

                // Set both sides of the relationship to null (because it is @OneToOne relation with owning one-sided)
                bike.setUser(null);
                if (user != null) {
                    user.setBike(null);
                    userService.addUser(user);  // Save the User to persist changes
                }
            }
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
