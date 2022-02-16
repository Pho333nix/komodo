package com.IV1201VT221.IV1201.exceptions;

public class UsernameNotFoundException extends Exception{
    /**
    * Exception for when Username is not found for a user.
    * @param  message message to be displayed to the offender
    */
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
