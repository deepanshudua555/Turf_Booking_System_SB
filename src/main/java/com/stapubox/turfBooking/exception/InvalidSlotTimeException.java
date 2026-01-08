package com.stapubox.turfBooking.exception;

public class InvalidSlotTimeException extends RuntimeException {

    public InvalidSlotTimeException(String message) {
        super(message);
    }
}