package com.keyin.finalsprint;

import com.keyin.finalsprint.models.Airport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AirportTests {

    @Test
    public void testAirportUpdating() {
        Airport airport = new Airport("code", "name", "city", "country");

        Airport.Update update = new Airport.Update(
                "newcode",
                "newname",
                "newcity",
                "newcountry"
        );

        airport.update(update);

        Assertions.assertEquals(airport.getCode(), "newcode");
        Assertions.assertEquals(airport.getName(), "newname");
        Assertions.assertEquals(airport.getCity(), "newcity");
        Assertions.assertEquals(airport.getCountry(), "newcountry");

        Airport.Update nullUpdate = new Airport.Update(null, null, null, null);

        airport.update(nullUpdate);

        Assertions.assertEquals(airport.getCode(), "newcode");
        Assertions.assertEquals(airport.getName(), "newname");
        Assertions.assertEquals(airport.getCity(), "newcity");
        Assertions.assertEquals(airport.getCountry(), "newcountry");
    }
}
