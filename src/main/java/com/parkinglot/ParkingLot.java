package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> ticketAssignment = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket generatedTicket = new ParkingTicket();
        ticketAssignment.put(generatedTicket, car);
        return generatedTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = ticketAssignment.get(parkingTicket);
        return fetchedCar;
    }
}
