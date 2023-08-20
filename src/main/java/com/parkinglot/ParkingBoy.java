package com.parkinglot;

import com.parkinglot.exceptions.UnrecognizedTicketException;

import java.util.List;

public class ParkingBoy {
    protected final List<ParkingLot> managedParkingLots;

    public ParkingBoy(List<ParkingLot> managedParkingLots) {
        this.managedParkingLots = managedParkingLots;
    }

    public ParkingTicket park(Car car){
        return null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        ParkingLot parkingLotParked = managedParkingLots.stream()
                .filter(parkingLot -> parkingLot.containsCarForTicket(parkingTicket))
                .findFirst()
                .orElse(null);
        if (parkingLotParked == null)
            throw new UnrecognizedTicketException();
        return parkingLotParked.fetch(parkingTicket);
    }
}
