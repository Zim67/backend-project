package com.keyin.finalsprint;

import com.keyin.finalsprint.models.Aircraft;
import com.keyin.finalsprint.models.Airport;
import com.keyin.finalsprint.models.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

public class FlightTests {

    @Test
    public void testFlightUpdating() {
        Flight flight = new Flight(
                new Aircraft(),
                new Airport(), "from", new Date(),
                new Airport(), "to", new Date()
        );

        Aircraft flightCraft = new Aircraft();
        Airport flightFrom = new Airport();
        Airport flightTo = new Airport();
        Date flightLeaving = Date.from(Instant.ofEpochSecond(54353434));
        Date flightArriving = Date.from(Instant.ofEpochSecond(65423423));

        Flight.Update update = new Flight.Update(
                flightCraft,
                flightFrom, "newfrom", flightLeaving,
                flightTo, "newto", flightArriving
        );

        flight.update(update);

        Assertions.assertSame(flight.getAircraft(), flightCraft);
        Assertions.assertSame(flight.getFrom(), flightFrom);
        Assertions.assertSame(flight.getTo(), flightTo);
        Assertions.assertSame(flight.getArrivingTime(), flightArriving);
        Assertions.assertSame(flight.getLeavingTime(), flightLeaving);
        Assertions.assertEquals(flight.getFromGate(), "newfrom");
        Assertions.assertEquals(flight.getToGate(), "newto");

        Flight.Update nullUpdate = new Flight.Update(null, null, null,
                null, null, null, null);

        flight.update(nullUpdate);

        Assertions.assertSame(flight.getAircraft(), flightCraft);
        Assertions.assertSame(flight.getFrom(), flightFrom);
        Assertions.assertSame(flight.getTo(), flightTo);
        Assertions.assertSame(flight.getArrivingTime(), flightArriving);
        Assertions.assertSame(flight.getLeavingTime(), flightLeaving);
        Assertions.assertEquals(flight.getFromGate(), "newfrom");
        Assertions.assertEquals(flight.getToGate(), "newto");
    }
}
