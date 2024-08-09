package com.keyin.finalsprint.models;

import com.keyin.finalsprint.utils.Updateable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aircraft implements Updateable<Aircraft.Update> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    protected String registrationNumber;
    protected String manufacturer;
    protected String model;
    protected String airline;

    public Aircraft(String registrationNumber, String manufacturer, String model, String airline) {
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.airline = airline;
    }

    @Override
    public boolean update(Update data) {
        if (data.registrationNumber != null) this.registrationNumber = data.registrationNumber;
        if (data.manufacturer != null) this.manufacturer = data.manufacturer;
        if (data.model != null) this.model = data.model;
        if (data.airline != null) this.airline = data.airline;
        return true;
    }

    public record Update(String registrationNumber, String manufacturer, String model, String airline) implements Updateable.UpdateData {

        @Override
        public boolean isComplete() {
            return registrationNumber != null && manufacturer != null && model != null && airline != null;
        }
    }
}
