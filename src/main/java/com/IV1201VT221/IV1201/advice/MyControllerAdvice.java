package com.IV1201VT221.IV1201.advice;

import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    @ExceptionHandler(value = {UsernameTakenException.class})
    public ResponseEntity<String> usernameTakenException(UsernameTakenException e){
        return new ResponseEntity<String>("Username is taken...", HttpStatus.BAD_REQUEST);
    }

    /**
    * Exceptionhandler for EmailTakenExcepton.
    * @param  UsernameTakenException a EmailTakenException object
    * @return                        A ResponseEntity representing the whole HTTP response. 
    */
    @ResponseBody
    @ExceptionHandler(value = {EmailTakenException.class})
    public ResponseEntity <String> emailTakenException(EmailTakenException e){
        return new ResponseEntity<String>("Email is taken", HttpStatus.BAD_REQUEST);
    }

    /**
    * Exceptionhandler for PnrTakenException.
    * @param  PnrTakenException a PnrTakenException object
    * @return                   A ResponseEntity representing the whole HTTP response.
    */
    @ResponseBody
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
    @ResponseBody
    public ResponseEntity <String> genericException(Exception e){
        return new ResponseEntity<String>("Something went wrong :/", HttpStatus.BAD_REQUEST);
    }
}
