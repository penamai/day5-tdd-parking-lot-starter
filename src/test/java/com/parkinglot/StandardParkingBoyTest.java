package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;
import com.parkinglot.parkingboys.StandardParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StandardParkingBoyTest {
    private StandardParkingBoy standardParkingBoy;
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private List<ParkingLot> managedParkingLot;
    private Car car;
    private Car secondCar;

    @BeforeEach
    void initializeGiven(){
        parkingLot1 = new ParkingLot();
        parkingLot2 = new ParkingLot();
        managedParkingLot = List.of(parkingLot1, parkingLot2);

        standardParkingBoy = new StandardParkingBoy(managedParkingLot);
        car = new Car();
        secondCar = new Car();
    }

    @Test
    void should_put_car_in_first_parking_lot_when_park_given_standardParkingBoy_two_parkingLots_and_car() {
        standardParkingBoy.park(car);
        Assertions.assertEquals(9, parkingLot1.getAvailableCapacity());
        Assertions.assertEquals(10, parkingLot2.getAvailableCapacity());
    }

    @Test
    void should_put_car_in_second_parking_lot_when_park_given_standardParkingBoy_two_parkingLots_with_one_full_parkingLot_and_car() {
        parkingLot1 = new ParkingLot(1);
        managedParkingLot = List.of(parkingLot1,parkingLot2);
        standardParkingBoy = new StandardParkingBoy(managedParkingLot);

        standardParkingBoy.park(car);
        standardParkingBoy.park(secondCar);

        Assertions.assertEquals(9, parkingLot2.getAvailableCapacity());
    }

    @Test
    void should_return_right_car_when_fetch_car_twice_given_standardParkingBoy_and_two_parking_lots_and_two_parking_tickets() {
        parkingLot1 = new ParkingLot(1);
        managedParkingLot = List.of(parkingLot1,parkingLot2);
        standardParkingBoy = new StandardParkingBoy(managedParkingLot);

        ParkingTicket firstParkingTicket = standardParkingBoy.park(car);
        ParkingTicket secondParkingTicket = standardParkingBoy.park(secondCar);

        Car fetchedFirstCar = standardParkingBoy.fetch(firstParkingTicket);
        Car fetchedSecondCar = standardParkingBoy.fetch(secondParkingTicket);

        Assertions.assertEquals(car, fetchedFirstCar);
        Assertions.assertEquals(secondCar, fetchedSecondCar);
    }

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_standardParkingBoy_and_two_parking_lots_and_unrecognized_ticket() {
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        standardParkingBoy.park(car);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> standardParkingBoy.fetch(wrongParkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_standardParkingBoy_and_two_parking_lots_and_used_ticket() {
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> standardParkingBoy.fetch(parkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noAvailablePositionException_when_park_given_standardParkingBoy_car_and_two_maxed_out_parkingLots() {
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(1);
        managedParkingLot = List.of(parkingLot1, parkingLot2);
        standardParkingBoy = new StandardParkingBoy(managedParkingLot);
        Car nextCar = new Car();

        standardParkingBoy.park(car);
        standardParkingBoy.park(secondCar);

        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class,
                () -> standardParkingBoy.park( nextCar));

        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }

}
