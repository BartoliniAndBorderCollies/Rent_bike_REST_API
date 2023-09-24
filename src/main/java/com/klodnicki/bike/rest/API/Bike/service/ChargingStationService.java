package com.klodnicki.bike.rest.API.Bike.service;

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


}
