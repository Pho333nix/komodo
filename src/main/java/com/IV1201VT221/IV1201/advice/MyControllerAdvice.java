package com.IV1201VT221.IV1201.advice;

import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {
    // anvädnarnamn e upptagen
    @ExceptionHandler(value = {UsernameTakenException.class})
    public ResponseEntity<String> usernameTakenException(UsernameTakenException e){
        return new ResponseEntity<String>("Username is taken...", HttpStatus.BAD_REQUEST);
    }
    // email e upptagen
    @ExceptionHandler(value = {EmailTakenException.class})
    public ResponseEntity <String> emailTakenException(EmailTakenException e){
        return new ResponseEntity<String>("Email is taken", HttpStatus.BAD_REQUEST);
    }
    //personnummer är taget
    @ExceptionHandler(value = {PnrTakenException.class})
    public ResponseEntity <String> PnrTakenException(PnrTakenException e){
        return new ResponseEntity<String>("Pnr is taken", HttpStatus.BAD_REQUEST);
    }
}
