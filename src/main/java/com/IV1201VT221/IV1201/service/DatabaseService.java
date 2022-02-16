package com.IV1201VT221.IV1201.service;

import com.IV1201VT221.IV1201.dao.PersonDao;
import com.IV1201VT221.IV1201.exceptions.*;
import com.IV1201VT221.IV1201.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
* DatabaseService used for interacting with Dao's which in turn interact with the database. 
* This service is used because additional logic might be required before we truly want to interact with the database.
*/
@Service
public class DatabaseService {

    private final PersonDao persondao;
    Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    /**
    * Constructor setting persondao object.
    * Persondao Object is used for calling methods within the persondao.
    */
    @Autowired
    public DatabaseService(@Qualifier("postgres") PersonDao persondao){
        this.persondao = persondao;
    }

    /**
    * Used for inserting a person into the database
    * @param  person to be inserted 
    * @return        An integer representing if the insertion was successful or now 
    */
    public int insertPerson(Person person) throws PnrTakenException, EmailTakenException, UsernameTakenException {
        return persondao.insertPerson(person);
    }

    /**
    * Used for getting a person from the database
    * @param  username of the person we want to retrieved.
    * @return        integer representing if the insertion was successful or now 
    */
    public String getPerson(String username) throws UsernameNotFoundException{
        return persondao.getPerson(username);
    }

    /**
    * Used for getting credentials from the database relating to a specific person.
    * @param  username of the person we want credentials from
    * @return          the credentials relating to a specific person.
    */
    public String[] getCredentials(String username) throws UsernameNotFoundException{
        try{
            return persondao.getCredentials(username);
        }catch(Exception e){
            logger.error("Unable to get credentials");
            return null;
        }

    }

    public Person getPersonObject(String email) throws DataNotFoundException {
        return persondao.getPersonObject(email);
    }
}
