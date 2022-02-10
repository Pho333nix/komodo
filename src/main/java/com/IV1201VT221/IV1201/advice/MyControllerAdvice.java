package com.IV1201VT221.IV1201.advice;

import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
* Global exception handler.
* @ControllerAdvice allows us to handle exceptions globally
* It will handle exceptions thrown by methods annotated with @RequestMapping or
*   Get
*   Post
*   Delete
*   Patch
* Mappings.
*/
@ControllerAdvice
public class MyControllerAdvice {
    /**
    * Exception handler for UsernameTakenException.
    * @param  UsernameTakenException a UsernameTakenException object
    * @return                        A ResponseEntity representing the whole HTTP response. 
    */
    @ExceptionHandler(value = {UsernameTakenException.class})
    public ResponseEntity<String> usernameTakenException(UsernameTakenException e){
        return new ResponseEntity<String>("Username is taken...", HttpStatus.BAD_REQUEST);
    }

    /**
    * Exception handler for EmailTakenExcepton.
    * @param  UsernameTakenException a EmailTakenException object
    * @return                        A ResponseEntity representing the whole HTTP response. 
    */
    @ExceptionHandler(value = {EmailTakenException.class})
    public ResponseEntity <String> emailTakenException(EmailTakenException e){
        return new ResponseEntity<String>("Email is taken", HttpStatus.BAD_REQUEST);
    }

    /**
    * Exception handler for PnrTakenException.
    * @param  PnrTakenException a PnrTakenException object
    * @return                   A PnrTakenException representing the whole HTTP response. 
    */
    @ExceptionHandler(value = {PnrTakenException.class})
    public ResponseEntity <String> PnrTakenException(PnrTakenException e){
        return new ResponseEntity<String>("Pnr is taken", HttpStatus.BAD_REQUEST);
    }
}
