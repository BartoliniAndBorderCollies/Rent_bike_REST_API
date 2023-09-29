package com.klodnicki.bike.rest.API.Bike.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.klodnicki.bike.rest.API.Bike.model.BikeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Must have a value.")
    private String serialNumber;

    @NotNull (message = "Must have a value")
    private boolean isRented;

    @NotNull (message = "Must have a value")
    private BikeType bikeType;

    @OneToOne (mappedBy = "bike", cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "charging_station_id")
//    @JsonBackReference
    @JsonIgnore
    private ChargingStation chargingStation;

    public Bike(Long id, String serialNumber, BikeType bikeType, boolean isRented, User user) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.bikeType = bikeType;
        this.isRented = isRented;
        this.user = user;
    }

    public Bike() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public ChargingStation getChargingStation() {
        return chargingStation;
    }

    public void setChargingStation(ChargingStation chargingStation) {
        this.chargingStation = chargingStation;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
