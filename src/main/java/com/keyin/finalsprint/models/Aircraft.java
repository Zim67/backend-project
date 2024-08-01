package com.keyin.finalsprint.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Aircraft {

    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1)
    @GeneratedValue(generator = "aircraft_sequence")
    private long id;

    private String registrationNumber;
    private String manufacturer;
    private String model;

    private String airline;

    public Aircraft() {

    }

    public Aircraft(String registrationNumber, String manufacturer, String model, String airline) {
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.airline = airline;
    }

    public long getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public record Updated(String registrationNumber, String manufacturer, String model, String airline) {

        public boolean isFull() {
            return registrationNumber != null && manufacturer != null && model != null && airline != null;
        }
    }
}
