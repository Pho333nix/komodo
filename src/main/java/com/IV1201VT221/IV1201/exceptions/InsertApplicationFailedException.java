package com.IV1201VT221.IV1201.exceptions;

public class InsertApplicationFailedException extends Exception{
    /**
     * Failed to isnert application into db exception
     * @param message String message
     */
    public InsertApplicationFailedException(String message){super(message);}
}

