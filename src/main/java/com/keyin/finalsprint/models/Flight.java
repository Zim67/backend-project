package com.keyin.finalsprint.models;

import com.keyin.finalsprint.utils.Updateable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Flight implements Updateable<Flight.Update> {

    @Id
    @SequenceGenerator(name = "flight_sequence", sequenceName = "flight_sequence", allocationSize = 1)
    @GeneratedValue(generator = "flight_sequence")
    private long id;

    @ManyToOne
    private Aircraft aircraft;

    @ManyToOne
    private Airport from;
    private String fromGate;
    private Date leavingTime;

    @ManyToOne
    private Airport to;
    private String toGate;
    private Date arrivingTime;

    public Flight(Aircraft aircraft, Airport from, String fromGate, Date leavingTime, Airport to, String toGate, Date arrivingTime) {
        this.aircraft = aircraft;
        this.from = from;
        this.fromGate = fromGate;
        this.leavingTime = leavingTime;
        this.to = to;
        this.toGate = toGate;
        this.arrivingTime = arrivingTime;
    }

    @Override
    public boolean update(Update data) {
        if (data.aircraft != null) this.aircraft = data.aircraft;
        if (data.from != null) this.from = data.from;
        if (data.fromGate != null) this.fromGate = data.fromGate;
        if (data.leavingTime != null) this.leavingTime = data.leavingTime;
        if (data.to != null) this.to = data.to;
        if (data.toGate != null) this.toGate = data.toGate;
        if (data.arrivingTime != null) this.arrivingTime = data.arrivingTime;
        return true;
    }

    public record Update(
            Aircraft aircraft,
            Airport from, String fromGate, Date leavingTime,
            Airport to, String toGate, Date arrivingTime
    ) implements Updateable.UpdateData {

        @Override
        public boolean isComplete() {
            return aircraft != null &&
                    from != null && fromGate != null && leavingTime != null &&
                    to != null && toGate != null && arrivingTime != null;
        }
    }
}