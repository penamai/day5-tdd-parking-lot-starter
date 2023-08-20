package com.parkinglot.exceptions;

public class NoAvailablePositionException extends RuntimeException{
    public NoAvailablePositionException() {
        super("No available position.");
    }
}
