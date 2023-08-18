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

    @Test
    void should_return_the_right_car_when_fetch_car_twice_given_parkingLot_with_two_cars_standardParkingBoy_and_parkingTicket() {
        //given
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();

        //when
        ParkingTicket firstParkingTicket = StandardParkingBoy.park(parkingLot, firstCar);
        ParkingTicket secondParkingTicket = StandardParkingBoy.park(parkingLot, secondCar);
        Car firstFetchedCar = StandardParkingBoy.fetch(parkingLot, firstParkingTicket);
        Car secondFetchedCar = StandardParkingBoy.fetch(parkingLot, secondParkingTicket);

        //then
        Assertions.assertEquals(firstCar, firstFetchedCar);
        Assertions.assertEquals(secondCar, secondFetchedCar);
    }
}
