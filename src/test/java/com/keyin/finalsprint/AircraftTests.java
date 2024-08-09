package com.keyin.finalsprint;

import com.keyin.finalsprint.models.Aircraft;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AircraftTests {

    @Test
    public void testAircraftUpdating() {
        Aircraft aircraft = new Aircraft("id", "manufacturer", "model", "airline");

        Aircraft.Update update = new Aircraft.Update(
                "newid",
                "newmanufacturer",
                "newmodel",
                "newairline"
        );

        aircraft.update(update);

        Assertions.assertEquals(aircraft.getRegistrationNumber(), "newid");
        Assertions.assertEquals(aircraft.getManufacturer(), "newmanufacturer");
        Assertions.assertEquals(aircraft.getModel(), "newmodel");
        Assertions.assertEquals(aircraft.getAirline(), "newairline");

        Aircraft.Update nullUpdate = new Aircraft.Update(null, null, null, null);

        aircraft.update(nullUpdate);

        Assertions.assertEquals(aircraft.getRegistrationNumber(), "newid");
        Assertions.assertEquals(aircraft.getManufacturer(), "newmanufacturer");
        Assertions.assertEquals(aircraft.getModel(), "newmodel");
        Assertions.assertEquals(aircraft.getAirline(), "newairline");
    }
}
