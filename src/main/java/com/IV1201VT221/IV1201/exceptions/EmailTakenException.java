package com.IV1201VT221.IV1201.exceptions;

public class EmailTakenException extends Exception{
    /**
    * Exception for when email is taken for a user.
    * @param  message message to be displayed to the offender
    */
    public EmailTakenException(String message) {
        super(message);
    }
}
