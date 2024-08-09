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
public class Airport implements Updateable<Airport.Update> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String code;
    private String name;
    private String city;
    private String country;

    public Airport(String code, String name, String city, String country) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    @Override
    public boolean update(Airport.Update data) {
        if (data.code != null) this.code = data.code;
        if (data.name != null) this.name = data.name;
        if (data.city != null) this.city = data.city;
        if (data.country != null) this.country = data.country;
        return true;
    }

    public record Update(
            String code, String name, String city, String country
    ) implements Updateable.UpdateData {

        @Override
        public boolean isComplete() {
            return code != null && name != null && city != null && country != null;
        }
    }
}
