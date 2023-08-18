package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SmartParkingBoyTest {
    private SmartParkingBoy smartParkingBoy;
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private List<ParkingLot> managedParkingLots;
    private Car car;
    private Car secondCar;

    @BeforeEach
    void initializeGiven(){
        parkingLot1 = new ParkingLot(5);
        parkingLot2 = new ParkingLot();
        managedParkingLots = List.of(parkingLot1, parkingLot2);
        smartParkingBoy = new SmartParkingBoy(managedParkingLots);
        car = new Car();
        secondCar = new Car();
    }
    @Test
    void should_put_car_in_parkingLot_with_largest_availableCapacity_when_park_given_smartParkingBoy_and_two_parkingLots_and_car() {
        smartParkingBoy.park(car);

        Assertions.assertEquals(5, parkingLot1.getAvailableCapacity());
        Assertions.assertEquals(9, parkingLot2.getAvailableCapacity());
    }
}
