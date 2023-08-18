package com.parkinglot;

import com.parkinglot.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StandardParkingBoyTest {
    StandardParkingBoy standardParkingBoy;
    ParkingLot parkingLot;
    Car car;

    @BeforeEach
    void initializeGiven(){
        standardParkingBoy = new StandardParkingBoy();
        parkingLot = new ParkingLot();
        car = new Car();
    }
    @Test
    void should_return_parkingTicket_when_park_given_standardParkingBoy_parkingLot_and_car() {
        ParkingTicket parkingTicket = StandardParkingBoy.park(parkingLot, car);
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parkedCar_when_fetch_given_standardParkingBoy_parkingLot_and_parkingTicket() {
        ParkingTicket parkingTicket = StandardParkingBoy.park(parkingLot, car);
        Car fetchedCar = StandardParkingBoy.fetch(parkingLot, parkingTicket);

        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_the_right_car_when_fetch_car_twice_given_parkingLot_with_two_cars_standardParkingBoy_and_parkingTicket() {
        Car firstCar = car;
        Car secondCar = new Car();

        ParkingTicket firstParkingTicket = StandardParkingBoy.park(parkingLot, firstCar);
        ParkingTicket secondParkingTicket = StandardParkingBoy.park(parkingLot, secondCar);

        Car firstFetchedCar = StandardParkingBoy.fetch(parkingLot, firstParkingTicket);
        Car secondFetchedCar = StandardParkingBoy.fetch(parkingLot, secondParkingTicket);

        Assertions.assertEquals(firstCar, firstFetchedCar);
        Assertions.assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_standardParkingBoy_and_parkingLot_and_wrong_parkingTicket() {
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        StandardParkingBoy.park(parkingLot, car);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> StandardParkingBoy.fetch(parkingLot, wrongParkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_standardParkingBoy_and_parkingLot_and_used_parkingTicket() {
        ParkingTicket parkingTicket = StandardParkingBoy.park(parkingLot, car);
        StandardParkingBoy.fetch(parkingLot, parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> StandardParkingBoy.fetch(parkingLot, parkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }
}
