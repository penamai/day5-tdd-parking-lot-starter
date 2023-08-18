package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    @Test
    void should_return_a_parking_ticket_when_park_given_parkingLot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_the_parked_car_when_fetch_given_parkingLot_and_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        Car fetchedCar = parkingLot.fetch(parkingTicket);

        //then
        Assertions.assertEquals(car, fetchedCar);
    }
}
