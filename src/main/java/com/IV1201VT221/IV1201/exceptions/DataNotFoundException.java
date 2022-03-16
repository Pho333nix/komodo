package com.IV1201VT221.IV1201.exceptions;

public class DataNotFoundException extends Exception{
    /**
     * Exeption for when data was not found
     * @param message String
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
