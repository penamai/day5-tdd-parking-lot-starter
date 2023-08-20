package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager {

    private final List<ParkingBoy> managementList;

    public ParkingLotServiceManager(){
        managementList = new ArrayList<>();
    }

    public List<ParkingBoy> getManagementList() {
        return managementList;
    }

    public void addToManagementList(ParkingBoy parkingBoy) {
        managementList.add(parkingBoy);
    }

    public ParkingTicket askToPark(Car car) {
        ParkingTicket parkingTicket = null;
        for (ParkingBoy parkingBoy : managementList) {
            try {
                parkingTicket = parkingBoy.park(car);
            } catch (NoAvailablePositionException noAvailablePositionException) {
                //do nothing
            }
            if (parkingTicket != null) {
                break;
            }
        }
        return parkingTicket;
    }
}
