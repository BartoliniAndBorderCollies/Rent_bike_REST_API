package com.klodnicki.bike.rest.API.Bike.service;

import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.model.entity.ChargingStation;
import com.klodnicki.bike.rest.API.Bike.repository.ChargingStationRepository;
import org.springframework.stereotype.Service;

@Service
public class ChargingStationService {


    private final ChargingStationRepository chargingStationRepository;

    public ChargingStationService(ChargingStationRepository chargingStationRepository) {
        this.chargingStationRepository = chargingStationRepository;
    }

    public ChargingStation addChargingStation(ChargingStation chargingStation) {
        return chargingStationRepository.save(chargingStation);
    }

    public Iterable<ChargingStation> findAllChargingStations() {
        return chargingStationRepository.findAll();
    }

    public ChargingStation findStationById(Long id) throws NotFoundInDatabaseException {
        return chargingStationRepository.findById(id).orElseThrow(NotFoundInDatabaseException::new);
    }

}
