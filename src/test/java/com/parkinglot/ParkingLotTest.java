package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void should_return_the_right_car_when_fetch_given_parkingLot_and_two_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();

        //when
        ParkingTicket firstParkingTicket = parkingLot.park(firstCar);
        ParkingTicket secondParkingTicket = parkingLot.park(secondCar);
        Car firstFetchedCar = parkingLot.fetch(firstParkingTicket);
        Car secondFetchedCar = parkingLot.fetch(secondParkingTicket);

        //then
        Assertions.assertEquals(firstCar, firstFetchedCar);
        Assertions.assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_wrong_parkingTicket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //when
        parkingLot.park(car);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLot.fetch(wrongParkingTicket));

        //then
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_used_parkingTicket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingLot.fetch(parkingTicket);
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLot.fetch(parkingTicket));

        //then
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noAvailablePositionException_when_park_given_parkingLot_with_maxed_out_capacity_of_10() {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        List<Car> parkedCars = Arrays.asList(new Car[10]);
        Car car = new Car();

        IntStream.range(0,10).forEach(index -> parkedCars.set(index, new Car()));

        //when
        parkedCars.forEach(parkingLot::park);
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class,
                () -> parkingLot.park(car));

        //then
        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
