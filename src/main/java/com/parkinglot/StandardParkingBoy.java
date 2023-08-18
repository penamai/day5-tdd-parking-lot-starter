package com.parkinglot;

import com.parkinglot.exceptions.UnrecognizedTicketException;

public class StandardParkingBoy {

    public StandardParkingBoy() {
    }

    public static ParkingTicket park(ParkingLot parkingLot, Car car) {
        return parkingLot.park(car);
    }

    public static Car fetch(ParkingLot parkingLot, ParkingTicket parkingTicket) {
        Car fetchedCar;
        try {
            fetchedCar = parkingLot.fetch(parkingTicket);
        } catch (UnrecognizedTicketException unrecognizedTicketException) {
            throw new UnrecognizedTicketException();
        }
        return fetchedCar;
    }
}