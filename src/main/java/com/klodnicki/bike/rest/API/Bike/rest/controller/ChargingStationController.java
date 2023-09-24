package com.klodnicki.bike.rest.API.Bike.rest.controller;

import com.klodnicki.bike.rest.API.Bike.model.entity.ChargingStation;
import com.klodnicki.bike.rest.API.Bike.service.ChargingStationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/station")
public class ChargingStationController {

    private final ChargingStationService chargingStationService;

    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @PostMapping("/add")
    public ChargingStation addChargingStation(@RequestBody ChargingStation chargingStation) {
        return chargingStationService.addChargingStation(chargingStation);
    }


}
