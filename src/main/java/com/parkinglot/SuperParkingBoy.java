package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> managedParkingLots) {
        super(managedParkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot availableParkingLot = managedParkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparingDouble(ParkingLot::getAvailablePositionsRate))
                .orElse(null);
        if(availableParkingLot == null)
            throw new NoAvailablePositionException();
        return availableParkingLot.park(car);
    }

}
