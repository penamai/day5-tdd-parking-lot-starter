package com.parkinglot.exceptions;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;

public class StandardParkingBoy {

    public StandardParkingBoy() {
    }

    public static ParkingTicket park(ParkingLot parkingLot, Car car) {
        return parkingLot.park(car);
    }

    public static Car fetch(ParkingLot parkingLot, ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket);
    }
}
