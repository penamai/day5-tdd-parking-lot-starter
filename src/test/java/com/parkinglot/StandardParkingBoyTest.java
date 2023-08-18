package com.parkinglot;

import com.parkinglot.exceptions.StandardParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StandardParkingBoyTest {

    @Test
    void should_return_parkingTicket_when_park_given_standardParkingBoy_parkingLot_and_car() {
        //given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = StandardParkingBoy.park(parkingLot, car);

        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parkedCar_when_fetch_given_standardParkingBoy_parkingLot_and_parkingTicket() {
        //given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = StandardParkingBoy.park(parkingLot, car);
        Car fetchedCar = StandardParkingBoy.fetch(parkingLot, parkingTicket);

        //then
        Assertions.assertEquals(car, fetchedCar);
    }
}
