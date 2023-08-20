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
}
