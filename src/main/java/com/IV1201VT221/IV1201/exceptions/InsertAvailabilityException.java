package com.IV1201VT221.IV1201.exceptions;

public class InsertAvailabilityException extends Exception{
    /**
     * Failed to insert availability
     * @param message String message
     */
    public InsertAvailabilityException(String message){super(message);}
}
