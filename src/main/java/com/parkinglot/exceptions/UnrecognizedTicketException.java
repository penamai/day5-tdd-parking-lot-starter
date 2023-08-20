package com.parkinglot.exceptions;

public class UnrecognizedTicketException extends RuntimeException{
    public UnrecognizedTicketException() {
        super("Unrecognized parking ticket.");
    }
}
