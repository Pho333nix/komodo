package com.IV1201VT221.IV1201.controller;

import com.IV1201VT221.IV1201.dao.PersonDao;
import com.IV1201VT221.IV1201.exceptions.*;
import com.IV1201VT221.IV1201.model.*;
import com.IV1201VT221.IV1201.service.DatabaseService;
import com.IV1201VT221.IV1201.service.MyUserDetailsService;
import com.IV1201VT221.IV1201.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Controller for handling incoming HTTP requests.
* A RestController does not return any views. It only handles requests and formats responses.
* It will handle exceptions thrown by methods annotated with @RequestMapping or
*/
@RestController
public class Restcontroller {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    DatabaseService databaseservice;
    Logger logger = LoggerFactory.getLogger(Restcontroller.class);

    /**
    * Constructor which instanciates a databaseservice which communicates with the dao layer which in turn communicates with the database
    * @param  databaseservice A databaseservice object 
    */
    @Autowired
    public Restcontroller(DatabaseService databaseservice) {
        this.databaseservice = databaseservice;
    }

    /*
    @RequestMapping(value = "/api/cred/{username}", method = RequestMethod.GET)
    public String[] getCred(@PathVariable String username) throws UsernameNotFoundException {
        String[] cred = new String[2];
        cred = databaseservice.getCredentials(username);
        return cred;
    }*/

    /**
     * Get all person ids that are available in the specified time period
     * @param startDate String
     * @param endDate String
     * @return List with person ids
     */
    @RequestMapping(value = "/api/available", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailability(@RequestParam String startDate, String endDate,
                                             @RequestHeader (name="Authorization") String token){
        String jwtToken = token.substring(7);
        String email = jwtTokenUtil.extractUsername(jwtToken);
        int roleId;
        try{
            roleId = databaseservice.getRoleId(email);
        }catch(Exception e){
            logger.error("unable to get roleid");
            return ResponseEntity.ok("unable to get roleid for user");
        }
        if(roleId == 1){
            try{
                return ResponseEntity.ok(databaseservice.getAvailability(startDate, endDate));
            }catch(Exception e){
                return ResponseEntity.ok("unable to get availability");
            }
        }
        return ResponseEntity.badRequest().body("your are not authorized to access this information");
    }

    /**
     * Insert the application into database
     * @param app to be inserted
     * @return ok or fail
     */
    @RequestMapping(value = "/api/uploadApp", method = RequestMethod.POST)
    public ResponseEntity<?> uploadCompetence(@RequestBody Application app, @RequestHeader (name="Authorization") String token){
        String jwtToken = token.substring(7);
        String email = jwtTokenUtil.extractUsername(jwtToken);
        String userId;
        int roleId;
        try{
            userId = databaseservice.getUserId(email);
            roleId = databaseservice.getRoleId(email);
        }catch(Exception e){
            logger.error("Unable to get userid");
            return ResponseEntity.ok("unable to get user");
        }
        if(roleId == 2){
            try{
                databaseservice.insertApplication(Integer.parseInt(userId), app.getStartDate(), app.getEndDate(), app.getJobs(),
                        app.getExperience());
                return ResponseEntity.ok("Uploaded your application successfully");
            }catch(Exception e){
                logger.error("unable to insert availability into database");
                return ResponseEntity.ok("unable to insert availability into db");
            }
        }
        return ResponseEntity.badRequest().body("Unauthorized access, only recruits can access this");
    }

    /**
     * Returns all competence from database
     * @return List with competence
     */
    @RequestMapping(value = "/api/competence/", method = RequestMethod.GET)
    public ResponseEntity<?> getCompetence(@RequestHeader (name="Authorization") String token) throws DataNotFoundException {
        int roleId = 0;
        String jwtToken = token.substring(7);
        String email = jwtTokenUtil.extractUsername(jwtToken);
        try{
            roleId = databaseservice.getRoleId(email);
        }catch(Exception e){
            logger.error("user not in db/controller");
            return ResponseEntity.badRequest().body("user does not exist");
        }
        if(roleId == 2){
            try{
                return ResponseEntity.ok(databaseservice.getAllCompetence());
            }catch(Exception e){
                logger.error("Error accessing competences");
                return ResponseEntity.badRequest().body("Could not get competence");
            }
        }else{
            return ResponseEntity.badRequest().body("Unauthorized access");
        }
    }

    /**
    * Inserts a user into the database
    * @param  person A person object
    * @return        A integer representing if the insertion was successful or not. 
    */
    @RequestMapping(value = "/api/ins", method = RequestMethod.POST)
    public int insertUser(@RequestBody Person person) throws PnrTakenException, EmailTakenException, UsernameTakenException {
        return databaseservice.insertPerson(person);
    }

    /**
     * Fetch a person object form database with the provided email as identifier
     * @param email to get object from
     * @return person object
     * @throws DataNotFoundException
     */
    @RequestMapping(value = "/api/user/{email}", method = RequestMethod.GET)
    public Person getPersonObject(@PathVariable String email) throws DataNotFoundException {
        return databaseservice.getPersonObject(email);
    }

    /**
     * Create a jwt token for the posted user and return the token and user object
     * @param authenticationRequest with username and passwrod
     * @return jwt token and person object
     * @throws Exception
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );
        }catch(BadCredentialsException e){
            throw new Exception("incorrect credentials", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationRequest.getUsername()
        );
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        Person p;
        try{
            p = databaseservice.getPersonObject(authenticationRequest.getUsername());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(new JwtPerson(jwt, p));
    }
}
