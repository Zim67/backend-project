package com.keyin.finalsprint.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Airport {

    @Id
    @SequenceGenerator(name = "airport_sequence", sequenceName = "airport_sequence", allocationSize = 1)
    @GeneratedValue(generator = "airport_sequence")
    private long id;

    private String code;
    private String name;
    private String city;
    private String country;

    public Airport() {

    }

    public Airport(String code, String name, String city, String country) {
        this();
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public record Updated(String code, String name, String city, String country) {

        public boolean isFull() {
            return code != null && name != null && city != null && country != null;
        }
    }
}
