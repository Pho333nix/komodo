package com.IV1201VT221.IV1201.controller;


import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import com.IV1201VT221.IV1201.model.Person;
import com.IV1201VT221.IV1201.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Restcontroller {

    DatabaseService databaseservice;

    @Autowired
    public Restcontroller(DatabaseService databaseservice){
        this.databaseservice = databaseservice;
    }

    @RequestMapping(value = "/api/ins", method = RequestMethod.POST)
    public int insertUser(@RequestBody Person person) throws PnrTakenException, EmailTakenException, UsernameTakenException {
        return databaseservice.insertPerson(person);
    }
}
