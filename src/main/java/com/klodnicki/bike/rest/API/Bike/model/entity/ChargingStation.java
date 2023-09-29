package com.klodnicki.bike.rest.API.Bike.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

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

    @OneToMany (mappedBy = "chargingStation", cascade = CascadeType.ALL)
    private List<Bike> bikeList;

    public ChargingStation() {
    }

    public ChargingStation(Long id, String name, int freeSlots, List<Bike> bikeList) {
        this.id = id;
        this.name = name;
        this.freeSlots = freeSlots;
        this.bikeList = bikeList;
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

    public List<Bike> getBikeList() {
        return bikeList;
    }

    public void setBikeList(List<Bike> bikeList) {
        this.bikeList = bikeList;
    }
}
