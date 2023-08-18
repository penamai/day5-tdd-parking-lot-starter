package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    @Test
    void should_return_a_parking_ticket_when_park_given_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket;

        //when
        parkingTicket = parkingLot.park(car);

        //then
        Assertions.assertNotNull(parkingTicket);
    }
}
