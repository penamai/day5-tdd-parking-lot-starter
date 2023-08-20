package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParkingLotServiceManagerTest {

    @Test
    void should_put_parkingBoys_to_managementList_when_addToManagementList_given_parkingLotServiceManager_and_parkingBoy() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new StandardParkingBoy(managedParkingLots);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();

        parkingLotServiceManager.addToManagementList(parkingBoy);

        Assertions.assertNotNull(parkingLotServiceManager.getManagementList());
    }

    @Test
    void should_return_parkingTicket_when_askToPark_given_parkingLotServiceManager_parkingBoy_and_car() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> managedParkingLots = List.of(parkingLot);
        ParkingBoy parkingBoy = new StandardParkingBoy(managedParkingLots);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
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

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
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
}
