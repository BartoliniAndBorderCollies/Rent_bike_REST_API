package com.klodnicki.bike.rest.API.Bike.model.entity;

import jakarta.persistence.*;

@Entity
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String serialNumber;
    private boolean isAvailable;

    public Bike(Long id, String serialNumber, boolean isAvailable) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.isAvailable = isAvailable;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
