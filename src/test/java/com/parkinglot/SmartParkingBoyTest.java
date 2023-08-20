package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;
import com.parkinglot.parkingboys.SmartParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    @Test
    void should_put_car_in_parkingLot_with_largest_availableCapacity_when_park_given_smartParkingBoy_and_two_parkingLots_and_car() {
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(managedParkingLots);
        Car car = new Car();

        smartParkingBoy.park(car);

        Assertions.assertEquals(5, parkingLot1.getAvailableCapacity());
        Assertions.assertEquals(9, parkingLot2.getAvailableCapacity());
    }
    
    @Test
    void should_put_car_in_parkingLot_with_next_largest_availableCapacity_when_park_given_smartParkingBoy_and_two_parkingLots_with_largestCapacityWithLessSpace_and_car() {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot1,parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(managedParkingLots);
        Car car = new Car();
        List<Car> parkedCars = Arrays.asList(new Car[8]);

        IntStream.range(0,parkedCars.size()).forEach(index->parkedCars.set(index, new Car()));
        parkedCars.forEach(smartParkingBoy::park);

        smartParkingBoy.park(car);

        Assertions.assertEquals(1, parkingLot1.getAvailableCapacity());
        Assertions.assertEquals(2, parkingLot2.getAvailableCapacity());
    }
    
    @Test
    void should_return_right_car_when_fetch_car_twice_given_smartParkingBoy_and_two_parkingLots_and_two_parkingTickets() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(managedParkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstParkingTicket = smartParkingBoy.park(firstCar);
        ParkingTicket secondParkingTicket = smartParkingBoy.park(secondCar);

        Car firstFetchedCar = smartParkingBoy.fetch(firstParkingTicket);
        Car secondFetchedCar = smartParkingBoy.fetch(secondParkingTicket);

        Assertions.assertEquals(firstCar, firstFetchedCar);
        Assertions.assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_return_unrecognizedParkingTicketException_when_fetch_given_smartParkingBoy_and_two_parkingLots_and_unrecognized_parkingTicket() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(managedParkingLots);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> smartParkingBoy.fetch(unrecognizedParkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedParkingTicketException_when_fetch_given_smartParkingBoy_and_two_parkingLots_and_used_parkingTicket() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(managedParkingLots);
        Car car = new Car();

        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> smartParkingBoy.fetch(parkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noAvailablePositionException_when_park_given_smartParkingBoy_two_full_parkingLots_and_car() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> managedParkingLots = List.of(parkingLot1, parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(managedParkingLots);
        Car firstParkedCar = new Car();
        Car secondParkedCar = new Car();
        Car newCar = new Car();

        smartParkingBoy.park(firstParkedCar);
        smartParkingBoy.park(secondParkedCar);

        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class,
                () -> smartParkingBoy.park(newCar));

        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
