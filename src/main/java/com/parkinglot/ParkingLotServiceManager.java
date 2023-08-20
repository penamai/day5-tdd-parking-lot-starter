package com.parkinglot;

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
}
