package com.klodnicki.bike.rest.API.Bike.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
@Entity
public class ChargingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Must have a value.")
    private String name;

    @NotNull(message = "Must have a value.")
    @PositiveOrZero (message = "Must be equal or bigger than zero!")
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



}
