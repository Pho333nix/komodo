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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Controller for handling incoming HTTP requests.
* A RestController does not return any views. It only handles requests and formats responses.
* It will handle exceptions thrown by methods annotated with @RequestMapping or
*/
@RestController
@CrossOrigin(origins = "*")
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


    /**
     * Get all person ids that are available in the specified time period
     * @param startDate String
     * @param endDate String
     * @return List with person ids
     */
    @RequestMapping(value = "/api/available", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailability(@RequestParam String startDate, String endDate,
                                             @RequestHeader (name="Authorization") String token) throws DataNotFoundException {
        String jwtToken = token.substring(7);
        String email = jwtTokenUtil.extractUsername(jwtToken);
        int roleId = databaseservice.getRoleId(email);
        if(roleId == 1){
            return ResponseEntity.ok(databaseservice.getAvailability(startDate, endDate));
        }
        return ResponseEntity.badRequest().body("your are not authorized to access this information");
    }

    /**
     * Insert the application into database
     * @param app to be inserted
     * @return ok or fail
     */
    @RequestMapping(value = "/api/uploadApp", method = RequestMethod.POST)
    public ResponseEntity<?> uploadCompetence(@RequestBody Application app, @RequestHeader (name="Authorization") String token) throws DataNotFoundException, UsernameNotFoundException, InsertApplicationFailedException {
        String jwtToken = token.substring(7);
        String email = jwtTokenUtil.extractUsername(jwtToken);
        String userId = databaseservice.getUserId(email);
        int roleId = databaseservice.getRoleId(email);
        if(roleId == 2){
            databaseservice.insertApplication(Integer.parseInt(userId), app.getStartDate(), app.getEndDate(), app.getJobs(),
                    app.getExperience());
            return ResponseEntity.ok("Uploaded your application successfully");
        }
        logger.info("Access with wrong role");
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
        roleId = databaseservice.getRoleId(email);
        if(roleId == 2){
            return ResponseEntity.ok(databaseservice.getAllCompetence());
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
    public int insertUser(@RequestBody Person person) throws PnrTakenException, EmailTakenException, UsernameTakenException, DataNotFoundException {
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
            logger.error("badcred ", e);
            return ResponseEntity.badRequest().body("User not found");
        }
        final String jwt;
        try{
            final UserDetails userDetails = userDetailsService.loadUserByUsername(
                    authenticationRequest.getUsername());
            jwt = jwtTokenUtil.generateToken(userDetails);
        }catch(Exception e){
            logger.error("could not find user", e);
            return ResponseEntity.badRequest().body("user not found");
        }
        Person p = databaseservice.getPersonObject3(authenticationRequest.getUsername());
        return ResponseEntity.ok(new JwtPerson(jwt, p));
    }
}
