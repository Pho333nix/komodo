package com.IV1201VT221.IV1201.controller;

import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameNotFoundException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import com.IV1201VT221.IV1201.model.AuthenticationRequest;
import com.IV1201VT221.IV1201.model.AuthenticationResponse;
import com.IV1201VT221.IV1201.model.Person;
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
@CrossOrigin(origins = "*")
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

    @Autowired
    public Restcontroller(DatabaseService databaseservice) {
        this.databaseservice = databaseservice;
    }


    @RequestMapping(value = "/api/auth/{username}", method = RequestMethod.GET)
    public String getUser(@PathVariable String username) throws UsernameNotFoundException {
        return databaseservice.getPerson(username);
    }

    @RequestMapping(value = "/api/cred/{username}", method = RequestMethod.GET)
    public String[] getCred(@PathVariable String username) throws UsernameNotFoundException {
        String[] cred = new String[2];
        cred = databaseservice.getCredentials(username);
        return cred;
    }


    @RequestMapping(value = "/api/ins", method = RequestMethod.POST)
    public int insertUser(@RequestBody Person person) throws PnrTakenException, EmailTakenException, UsernameTakenException {
        return databaseservice.insertPerson(person);
    }

    @RequestMapping("/start")
    public String hello(){
        return "logged in";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );

        }catch(BadCredentialsException e){
            logger.error("INCORRECT CREDENTIALS", e);
            throw new Exception("incorrect credentials", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationRequest.getUsername()
        );
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));


    }
}
