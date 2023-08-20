package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;
import com.parkinglot.parkingboys.SmartParkingBoy;
import com.parkinglot.parkingboys.StandardParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotServiceManagerTest {

    @Test
    void should_put_parkingBoys_to_managementList_when_addToManagementList_given_parkingLotServiceManager_and_parkingBoy() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new StandardParkingBoy(managedParkingLots);
        List<ParkingLot> managedParkingLotsByServiceManager = new ArrayList<>();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);

        parkingLotServiceManager.addToManagementList(parkingBoy);

        Assertions.assertNotNull(parkingLotServiceManager.getManagementList());
    }

    @Test
    void should_return_parkingTicket_when_askToPark_given_parkingLotServiceManager_parkingBoy_and_car() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new StandardParkingBoy(managedParkingLots);
        List<ParkingLot> managedParkingLotsByServiceManager = new ArrayList<>();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        parkingLotServiceManager.addToManagementList(parkingBoy);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLotServiceManager.askToPark(car);

        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_right_car_when_askToFetch_given_parkingLotServiceManager_two_parkingBoys_and_two_parkingTickets() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        List<ParkingLot> firstManagedParkingLots = List.of(parkingLot1);
        List<ParkingLot> secondManagedParkingLots = List.of(parkingLot2);
        ParkingBoy firstParkingBoy = new StandardParkingBoy(firstManagedParkingLots);
        ParkingBoy secondParkingBoy = new SmartParkingBoy(secondManagedParkingLots);

        List<ParkingLot> managedParkingLotsByServiceManager = new ArrayList<>();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        parkingLotServiceManager.addToManagementList(firstParkingBoy);
        parkingLotServiceManager.addToManagementList(secondParkingBoy);

        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstParkingTicket = parkingLotServiceManager.askToPark(firstCar);
        ParkingTicket secondParkingTicket = parkingLotServiceManager.askToPark(secondCar);

        Car firstFetchedCar = parkingLotServiceManager.askToFetch(firstParkingTicket);
        Car secondFetchedCar = parkingLotServiceManager.askToFetch(secondParkingTicket);

        Assertions.assertEquals(firstCar, firstFetchedCar);
        Assertions.assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_return_unrecognizedParkingTicketException_when_askToFetch_given_parkingLotServiceManager_two_parkingBoys_and_unrecognized_parkingTicket() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> firstManagedParkingLots = List.of(parkingLot1);
        List<ParkingLot> secondManagedParkingLots = List.of(parkingLot2);
        ParkingBoy firstParkingBoy = new StandardParkingBoy(firstManagedParkingLots);
        ParkingBoy secondParkingBoy = new SmartParkingBoy(secondManagedParkingLots);

        List<ParkingLot> managedParkingLotsByServiceManager = new ArrayList<>();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        parkingLotServiceManager.addToManagementList(firstParkingBoy);
        parkingLotServiceManager.addToManagementList(secondParkingBoy);

        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLotServiceManager.askToFetch(unrecognizedParkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedParkingTicketException_when_askToFetch_given_parkingLotServiceManager_two_parkingBoys_and_used_parkingTicket() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> firstManagedParkingLots = List.of(parkingLot1);
        List<ParkingLot> secondManagedParkingLots = List.of(parkingLot2);
        ParkingBoy firstParkingBoy = new StandardParkingBoy(firstManagedParkingLots);
        ParkingBoy secondParkingBoy = new SmartParkingBoy(secondManagedParkingLots);

        List<ParkingLot> managedParkingLotsByServiceManager = new ArrayList<>();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        parkingLotServiceManager.addToManagementList(firstParkingBoy);
        parkingLotServiceManager.addToManagementList(secondParkingBoy);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingLotServiceManager.askToPark(car);
        parkingLotServiceManager.askToFetch(parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLotServiceManager.askToFetch(parkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noAvailablePositionException_when_askToPark_given_parkingLotServiceManager_two_parkingBoys_with_full_parkingLots_and_car() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> firstManagedParkingLots = List.of(parkingLot1);
        List<ParkingLot> secondManagedParkingLots = List.of(parkingLot2);
        ParkingBoy firstParkingBoy = new StandardParkingBoy(firstManagedParkingLots);
        ParkingBoy secondParkingBoy = new SmartParkingBoy(secondManagedParkingLots);

        List<ParkingLot> managedParkingLotsByServiceManager = new ArrayList<>();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        parkingLotServiceManager.addToManagementList(firstParkingBoy);
        parkingLotServiceManager.addToManagementList(secondParkingBoy);

        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();
        parkingLotServiceManager.askToPark(firstCar);
        parkingLotServiceManager.askToPark(secondCar);

        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class,
                () -> parkingLotServiceManager.askToPark(thirdCar));

        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }

    @Test
    void should_put_car_in_first_parkingLot_when_park_given_parkingLotServiceManager_two_parkingLots_and_car() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLotsByServiceManager = List.of(parkingLot1, parkingLot2);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        Car car = new Car();

        parkingLotServiceManager.park(car);

        Assertions.assertEquals(0, parkingLot1.getAvailableCapacity());
        Assertions.assertEquals(10, parkingLot2.getAvailableCapacity());
    }

    @Test
    void should_put_car_in_second_parkingLot_when_park_given_parkingLotServiceManager_two_parkingLots_with_first_full_and_car() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLotsByServiceManager = List.of(parkingLot1, parkingLot2);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        Car firstCar = new Car();
        Car secondCar = new Car();

        parkingLotServiceManager.park(firstCar);
        parkingLotServiceManager.park(secondCar);

        Assertions.assertEquals(0, parkingLot1.getAvailableCapacity());
        Assertions.assertEquals(9, parkingLot2.getAvailableCapacity());
    }

    @Test
    void should_return_right_car_when_fetch_twice_given_parkingLotServiceManager_two_parkingLots_and_two_parkingTickets() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLotsByServiceManager = List.of(parkingLot1, parkingLot2);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstParkingTicket = parkingLotServiceManager.park(firstCar);
        ParkingTicket secondParkingTicket = parkingLotServiceManager.park(secondCar);

        Car firstFetchedCar = parkingLotServiceManager.fetch(firstParkingTicket);
        Car secondFetchedCar = parkingLotServiceManager.fetch(secondParkingTicket);

        Assertions.assertEquals(firstCar, firstFetchedCar);
        Assertions.assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_parkingLotServiceManager_two_parkingLots_and_unrecognized_parkingTicket() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLotsByServiceManager = List.of(parkingLot1, parkingLot2);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLotServiceManager.fetch(unrecognizedParkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedTicketException_when_fetch_given_parkingLotServiceManager_two_parkingLots_and_used_parkingTicket() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> managedParkingLotsByServiceManager = List.of(parkingLot1, parkingLot2);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);

        parkingLotServiceManager.fetch(parkingTicket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLotServiceManager.fetch(parkingTicket));

        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noAvailablePositionException_when_park_given_parkingLotServiceManager_two_full_parkingLots_and_car() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> managedParkingLotsByServiceManager = List.of(parkingLot1, parkingLot2);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(managedParkingLotsByServiceManager);

        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();

        parkingLotServiceManager.park(firstCar);
        parkingLotServiceManager.park(secondCar);

        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class,
                () -> parkingLotServiceManager.park(thirdCar));

        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
