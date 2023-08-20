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
        Car car1 = new Car();
        Car car2 = new Car();

        superParkingBoy.park(car);
        superParkingBoy.park(car1);
        superParkingBoy.park(car2);

        Assertions.assertEquals(4, parkingLot1.getAvailableCapacity());
        Assertions.assertEquals(8, parkingLot2.getAvailableCapacity());
    }

    @Test
    void should_return_right_cars_when_fetch_twice_given_superParkingBoy_two_parkingLots_and_tickets() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(managedParkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstParkingTicket = superParkingBoy.park(firstCar);
        ParkingTicket secondParkingTicket = superParkingBoy.park(secondCar);

        Car firstFetchedCar = superParkingBoy.fetch(firstParkingTicket);
        Car secondFetchedCar = superParkingBoy.fetch(secondParkingTicket);

        Assertions.assertEquals(firstCar, firstFetchedCar);
        Assertions.assertEquals(secondCar, secondFetchedCar);
    }
}
