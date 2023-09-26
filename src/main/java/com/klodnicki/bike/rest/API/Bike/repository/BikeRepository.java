package com.klodnicki.bike.rest.API.Bike.repository;

import com.klodnicki.bike.rest.API.Bike.model.entity.Bike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends CrudRepository<Bike, Long> {
}
