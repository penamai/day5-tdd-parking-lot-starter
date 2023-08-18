package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
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
        if(isFull()){
            throw new NoAvailablePositionException();
        }

        ParkingTicket generatedTicket = new ParkingTicket();
        ticketAssignment.put(generatedTicket, car);
        return generatedTicket;
    }

    private boolean isFull() {
        return ticketAssignment.size() == capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(!ticketAssignment.containsKey(parkingTicket)){
            throw new UnrecognizedTicketException();
        }
        return ticketAssignment.remove(parkingTicket);
    }
}
