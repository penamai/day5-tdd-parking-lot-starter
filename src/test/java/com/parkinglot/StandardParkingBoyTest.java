package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StandardParkingBoyTest {
    private StandardParkingBoy standardParkingBoy;
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private Car car;

    @BeforeEach
    void initializeGiven(){
        parkingLot1 = new ParkingLot();
        parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLot = List.of(parkingLot1, parkingLot2);

        standardParkingBoy = new StandardParkingBoy(managedParkingLot);
        car = new Car();
    }
    @Test
    void should_return_parkingTicket_when_park_given_standardParkingBoy_parkingLot_and_car() {
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parkedCar_when_fetch_given_standardParkingBoy_parkingLot_and_parkingTicket() {
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        Car fetchedCar = standardParkingBoy.fetch(parkingTicket);

        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_the_right_car_when_fetch_car_twice_given_parkingLot_with_two_cars_standardParkingBoy_and_parkingTicket() {
        Car firstCar = car;
        Car secondCar = new Car();

        ParkingTicket firstParkingTicket = standardParkingBoy.park(firstCar);
        ParkingTicket secondParkingTicket = standardParkingBoy.park(secondCar);

        Car firstFetchedCar = standardParkingBoy.fetch(firstParkingTicket);
        Car secondFetchedCar = standardParkingBoy.fetch(secondParkingTicket);

        Assertions.assertEquals(firstCar, firstFetchedCar);
        Assertions.assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_standardParkingBoy_and_parkingLot_and_wrong_parkingTicket() {
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        standardParkingBoy.park(car);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> standardParkingBoy.fetch(wrongParkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_standardParkingBoy_and_parkingLot_and_used_parkingTicket() {
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> standardParkingBoy.fetch(parkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noAvailablePositionException_when_park_given_standardParkingBoy_car_and_maxed_out_parkingLot() {
        parkingLot1 = new ParkingLot(1);
        List<ParkingLot> managedParkingLot = List.of(parkingLot1);
        standardParkingBoy = new StandardParkingBoy(managedParkingLot);
        Car nextCar = new Car();

        standardParkingBoy.park(car);

        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class,
                () -> standardParkingBoy.park( nextCar));

        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
