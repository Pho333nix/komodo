package com.IV1201VT221.IV1201.advice;

import com.IV1201VT221.IV1201.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
* Global exception handling...
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
    * Exceptionhandler for UsernameTakenException.
    * @param  UsernameTakenException a UsernameTakenException object
    * @return                        A ResponseEntity representing the whole HTTP response. 
    */
    @ExceptionHandler(value = {UsernameTakenException.class})
    public ResponseEntity<String> usernameTakenException(UsernameTakenException e){
        return new ResponseEntity<String>("Username is taken...", HttpStatus.BAD_REQUEST);
    }

    /**
    * Exceptionhandler for EmailTakenExcepton.
    * @param  UsernameTakenException a EmailTakenException object
    * @return                        A ResponseEntity representing the whole HTTP response. 
    */
    @ExceptionHandler(value = {EmailTakenException.class})
    public ResponseEntity <String> emailTakenException(EmailTakenException e){
        return new ResponseEntity<String>("Email is taken", HttpStatus.BAD_REQUEST);
    }

    /**
    * Exceptionhandler for PnrTakenException.
    * @param  PnrTakenException a PnrTakenException object
    * @return                   A ResponseEntity representing the whole HTTP response.
    */
    @ExceptionHandler(value = {PnrTakenException.class})
    public ResponseEntity <String> PnrTakenException(PnrTakenException e){
        return new ResponseEntity<String>("Pnr is taken", HttpStatus.BAD_REQUEST);
    }
    /**
     * Exceptionhandler for PnrTakenException.
     * @param  Exception a generic excepion
     * @return                   A ResponseEntity representing which gives a generic message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity <String> genericException(Exception e){
        return new ResponseEntity<String>("Generic exception, something went wrong..", HttpStatus.BAD_REQUEST);
        //return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity <String> dataNotFoundException(DataNotFoundException e){
        return new ResponseEntity<String>("Could not find data", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsertApplicationFailedException.class)
    public ResponseEntity <String> insertApplicationFailedException(InsertApplicationFailedException e){
        return new ResponseEntity<String>("Could not insert application, check params",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotFoundException.class)
    public ResponseEntity <String> passwordNotFoundException(PasswordNotFoundException e){
        return new ResponseEntity<String>("Could not find password",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsertCompetenceException.class)
    public ResponseEntity <String> insertCompetenceException(InsertCompetenceException e){
        return new ResponseEntity<String>("Could not upload competence profile, check params",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsertAvailabilityException.class)
    public ResponseEntity <String> insertAvailabilityException(InsertAvailabilityException e){
        return new ResponseEntity<String>("Could not upload availability",
                HttpStatus.BAD_REQUEST);
    }
}
