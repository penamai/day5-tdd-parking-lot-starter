package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;
import com.parkinglot.parkingboys.StandardParkingBoy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends StandardParkingBoy {

    private final List<ParkingBoy> managementList;

    public ParkingLotServiceManager(List <ParkingLot> managedParkingLots){
        super(managedParkingLots);
        managementList = new ArrayList<>();
    }

    public List<ParkingBoy> getManagementList() {
        return managementList;
    }

    public void addToManagementList(ParkingBoy parkingBoy) {
        managementList.add(parkingBoy);
    }
    //todo for this one although already good i think can be improved into something different
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
        if(parkingTicket == null)
            throw new NoAvailablePositionException();
        return parkingTicket;
    }

    public Car askToFetch(ParkingTicket parkingTicket) {
        Car car = null;
        for (ParkingBoy parkingBoy : managementList){
            try {
                car = parkingBoy.fetch(parkingTicket);
            } catch (UnrecognizedTicketException unrecognizedTicketException){
                //do nothing
            }
            if (car != null){
                break;
            }
        }
        if(car == null)
            throw new UnrecognizedTicketException();
        return car;
    }
}
