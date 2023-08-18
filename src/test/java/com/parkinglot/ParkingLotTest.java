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
    void should_return_nothing_when_fetch_given_wrong_parkingTicket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //when
        parkingLot.park(car);
        Car fetchedCar = parkingLot.fetch(wrongParkingTicket);

        //then
        Assertions.assertNull(fetchedCar);
    }

    @Test
    void should_return_nothing_when_fetch_given_used_parkingTicket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingLot.fetch(parkingTicket);
        Car fetchedCarTwice = parkingLot.fetch(parkingTicket);

        //then
        Assertions.assertNull(fetchedCarTwice);
    }
}
