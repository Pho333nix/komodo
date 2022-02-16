package com.IV1201VT221.IV1201.exceptions;

public class UsernameTakenException extends Exception{
    /**
    * Exception for when Username taken already.
    * @param  message message to be displayed to the offender
    */
    public UsernameTakenException(String message) {
        super(message);
    }
}
