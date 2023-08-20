package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;
import com.parkinglot.parkingboys.SuperParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void should_return_unrecognizedParkingTicketException_when_fetch_given_superParkingBoy_two_parkingLots_and_unrecognized_parkingTicket() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(managedParkingLots);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> superParkingBoy.fetch(unrecognizedParkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedParkingTicketException_when_fetch_given_superParkingBoy_two_parkingLots_and_used_parkingTicket() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(managedParkingLots);
        Car car = new Car();

        ParkingTicket parkingTicket = superParkingBoy.park(car);
        superParkingBoy.fetch(parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
            () -> superParkingBoy.fetch(parkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noAvailablePositionException_when_park_given_superParkingBoy_two_full_parkingLots_car() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(managedParkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();

        superParkingBoy.park(firstCar);
        superParkingBoy.park(secondCar);

        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class,
                () -> superParkingBoy.park(thirdCar));

        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
