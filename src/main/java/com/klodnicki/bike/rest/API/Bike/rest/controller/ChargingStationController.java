package com.klodnicki.bike.rest.API.Bike.rest.controller;

import com.klodnicki.bike.rest.API.Bike.exception.NotFoundInDatabaseException;
import com.klodnicki.bike.rest.API.Bike.model.entity.ChargingStation;
import com.klodnicki.bike.rest.API.Bike.service.ChargingStationService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/station")
public class ChargingStationController {

    private final ChargingStationService chargingStationService;

    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @PostMapping("/add")
    public ChargingStation addChargingStation(@RequestBody @Valid ChargingStation chargingStation)
            throws ConstraintViolationException {
        return chargingStationService.addChargingStation(chargingStation);
    }

    @GetMapping()
    public Iterable<ChargingStation> findAllChargingStations() {
        return chargingStationService.findAllChargingStations();
    }

    @GetMapping("/{id}")
    public ChargingStation findStationById(@PathVariable("id") Long id) throws NotFoundInDatabaseException {
        return chargingStationService.findStationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteChargingStation(@PathVariable("id") Long id) throws NotFoundInDatabaseException {
        chargingStationService.deleteChargingStation(id);
    }

    @PutMapping("/{id}")
    public ChargingStation updateChargingStation(@PathVariable("id") Long id, @RequestBody @Valid ChargingStation
            stationToUpdate)
            throws NotFoundInDatabaseException {
        return chargingStationService.updateChargingStation(id, stationToUpdate);
    }

}
