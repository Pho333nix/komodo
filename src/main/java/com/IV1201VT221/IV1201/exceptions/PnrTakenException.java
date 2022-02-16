package com.IV1201VT221.IV1201.exceptions;

public class PnrTakenException extends Exception{
    /**
    * Exception for when Pnr is taken for a user.
    * @param  message message to be displayed to the offender
    */
    public PnrTakenException(String message) {
        super(message);
    }
}
