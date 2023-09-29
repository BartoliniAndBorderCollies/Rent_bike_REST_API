package com.klodnicki.bike.rest.API.Bike.model.dto;

import com.klodnicki.bike.rest.API.Bike.model.BikeType;

public class BikeForNormalUserDTO { // DTO - Data Transfer Object
    private String serialNumber;
    private BikeType bikeType;

    public BikeForNormalUserDTO(String serialNumber, BikeType bikeType) {
        this.serialNumber = serialNumber;
        this.bikeType = bikeType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }
}
