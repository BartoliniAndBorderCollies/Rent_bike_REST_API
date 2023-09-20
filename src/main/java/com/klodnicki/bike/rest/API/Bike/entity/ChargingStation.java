package com.klodnicki.bike.rest.API.Bike.entity;

import jakarta.persistence.*;

@Entity
public class ChargingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private int freeSlots;

    public ChargingStation() {
    }

    public ChargingStation(String name, int freeSlots) {

        this.name = name;
        this.freeSlots = freeSlots;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }

    public void rentBike() {

    }

    public void returnBike() {

    }



}
