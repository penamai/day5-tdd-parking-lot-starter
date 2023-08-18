package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;

import java.util.List;

public class StandardParkingBoy {
    private final List<ParkingLot> managedParkingLots;

    public StandardParkingBoy(List <ParkingLot> managedParkingLots) {
        this.managedParkingLots = managedParkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingLot availableParkingLot = managedParkingLots.stream()
                        .filter(parkingLot -> !parkingLot.isFull())
                        .findFirst()
                        .orElse(null);
        if(availableParkingLot == null)
            throw new NoAvailablePositionException();
        return availableParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        ParkingLot parkingLotParked = managedParkingLots.stream()
                        .filter(parkingLot -> parkingLot.containsCarForTicket(parkingTicket))
                        .findFirst()
                        .orElse(null);
        if(parkingLotParked == null)
            throw new UnrecognizedTicketException();
        return parkingLotParked.fetch(parkingTicket);
    }
}