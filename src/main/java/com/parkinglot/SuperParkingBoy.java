package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy {
    private final List<ParkingLot> managedParkingLots;

    public SuperParkingBoy(List<ParkingLot> managedParkingLots) {

        this.managedParkingLots = managedParkingLots;
    }

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
