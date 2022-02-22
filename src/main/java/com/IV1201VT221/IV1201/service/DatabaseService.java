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
        String name = person.getName();
        String surname = person.getSurname();
        String pnr = person.getPnr();
        String email = person.getEmail();
        String password = person.getPassword();
        int role_id = person.getRoleid();
        String username = person.getUsername();

        if(persondao.getUsernameCount(username) != 0){
            logger.error("Username already exists");
            throw new UsernameTakenException("");
        }
        if(persondao.getEmailCount(email) != 0){
            logger.error("Email already exists");
            throw new EmailTakenException("");
        }
        if(persondao.getPnrCount(pnr) != 0){
            logger.error("Person number already exists");
            throw new PnrTakenException("");
        }
        try{
            return persondao.insertPerson(name, surname, pnr, email, password, role_id, username);
        }catch(Exception e){
            logger.error("Could not add person to database, check connection");
            return 0;
        }
    }

    /**
    * Used for getting credentials from the database relating to a specific person.
    * @param  username of the person we want credentials from
    * @return          the credentials relating to a specific person.
    */
    public String[] getCredentials(String username) throws UsernameNotFoundException{
        String[] cred = new String[2];
        try{
            cred[0] = persondao.getEmail(username);
            cred[1] = persondao.getUserId(username);
        }catch(Exception e){
            logger.error("CREDENTIALS NOT FOUND");
            return null;
        }
        return cred;
    }

    /**
     * Get all info about a person from db given an email
     * @param email email pointing to a user
     * @return person object
     * @throws DataNotFoundException
     */
    public Person getPersonObject(String email) throws DataNotFoundException {
        String name = persondao.getName(email);
        String surname = persondao.getSurname(email);
        String pnr = persondao.getPnr(email);
        int role = persondao.getRoleid(email);
        String mail = persondao.getEmail(email);
        String password;
        String username;
        try{
            password = persondao.getPassword(email);
        }catch(Exception e){
            logger.error("No email found");
            password = "";
        }
        try{
            username = persondao.getUsername(email);
        }catch(Exception e){
            logger.error("no username found");
            username = "";
        }
        return new Person(name, surname, pnr, mail, password, role, username);
    }
}
