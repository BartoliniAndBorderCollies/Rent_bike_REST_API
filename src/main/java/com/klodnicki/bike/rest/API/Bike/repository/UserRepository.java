package com.klodnicki.bike.rest.API.Bike.repository;

import com.klodnicki.bike.rest.API.Bike.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
