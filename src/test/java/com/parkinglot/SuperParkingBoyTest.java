package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SuperParkingBoyTest {

    @Test
    void should_put_car_in_parkingLot_with_larger_availableParkingRate_when_park_given_superParkingBoy_two_parkingLots_and_car() {
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(managedParkingLots);
        Car car = new Car();

        superParkingBoy.park(car);

        Assertions.assertEquals(4, parkingLot1.getAvailableCapacity());
        Assertions.assertEquals(10, parkingLot2.getAvailableCapacity());
    }
}
