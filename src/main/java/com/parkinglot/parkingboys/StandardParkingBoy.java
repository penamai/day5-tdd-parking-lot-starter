package com.parkinglot.parkingboys;

import com.parkinglot.Car;
import com.parkinglot.ParkingBoy;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exceptions.NoAvailablePositionException;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy {
    public StandardParkingBoy(List <ParkingLot> managedParkingLots) {
        super(managedParkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot availableParkingLot = managedParkingLots.stream()
                        .filter(parkingLot -> !parkingLot.isFull())
                        .findFirst()
                        .orElse(null);
        if(availableParkingLot == null)
            throw new NoAvailablePositionException();
        return availableParkingLot.park(car);
    }
}