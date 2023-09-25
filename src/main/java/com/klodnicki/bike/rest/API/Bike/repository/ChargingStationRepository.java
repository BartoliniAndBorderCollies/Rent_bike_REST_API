package com.klodnicki.bike.rest.API.Bike.repository;

import com.klodnicki.bike.rest.API.Bike.model.entity.ChargingStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingStationRepository extends CrudRepository<ChargingStation, Long> {

}
