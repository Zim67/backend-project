package com.keyin.finalsprint.routes.bodies;

import com.keyin.finalsprint.models.Aircraft;
import com.keyin.finalsprint.models.Airport;
import com.keyin.finalsprint.models.Flight;
import com.keyin.finalsprint.services.AircraftService;
import com.keyin.finalsprint.services.AirportService;

import java.util.Date;

public record FlightsUpdateBody(
        long aircraft,
        long from, String fromGate, Date leavingTime,
        long to, String toGate, Date arrivingTime
) {

    public Flight.Update toUpdate(AircraftService aircrafts, AirportService airports) {
        Aircraft aircraft = aircrafts.get(this.aircraft);
        Airport from = airports.get(this.from);
        Airport to = airports.get(this.to);
        return new Flight.Update(aircraft, from, fromGate, leavingTime, to, toGate, arrivingTime);
    }
}
