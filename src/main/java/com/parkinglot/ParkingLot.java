package com.parkinglot;

import com.parkinglot.exceptions.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY = 10;
    private final int capacity;
    private final Map<ParkingTicket, Car> ticketAssignment = new HashMap<>();

    public ParkingLot(){
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity){
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if(ticketAssignment.size() >= capacity)
            return null;

        ParkingTicket generatedTicket = new ParkingTicket();
        ticketAssignment.put(generatedTicket, car);
        return generatedTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(ticketAssignment.get(parkingTicket) == null){
            throw new UnrecognizedTicketException();
        }
        return ticketAssignment.remove(parkingTicket);
    }
}
