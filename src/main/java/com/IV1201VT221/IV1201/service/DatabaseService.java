package com.IV1201VT221.IV1201.service;

import com.IV1201VT221.IV1201.dao.PersonDao;
import com.IV1201VT221.IV1201.exceptions.*;
import com.IV1201VT221.IV1201.model.Available;
import com.IV1201VT221.IV1201.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
* DatabaseService u sed for interacting with Dao's which in turn interact with the database.
* This service is used because additional logic might be required before we truly want to interact with the database.
*/
@Service
public class DatabaseService {

    private final PersonDao persondao;
    Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    BCryptPasswordEncoder passwordEncoder;

    /**
    * Constructor setting persondao object.
    * Persondao Object is used for calling methods within the persondao.
    */
    @Autowired
    public DatabaseService(@Qualifier("postgres") PersonDao persondao){
        this.persondao = persondao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
    * Used for inserting a person into the database
    * @param  person to be inserted 
    * @return        An integer representing if the insertion was successful or now 
    */
    public int insertPerson(Person person) throws PnrTakenException, EmailTakenException, UsernameTakenException, DataNotFoundException {
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
            String encodedPassword = this.passwordEncoder.encode(password);
            return persondao.insertPerson(name, surname, pnr, email, encodedPassword, role_id, username);
        }catch(Exception e){
            logger.error("Could not add person to database, check connection", e);
            throw new DataNotFoundException("");
        }
    }

    /**
     * Returns user id from db connected to the provided username
     * @param username
     * @return user id
     * @throws UsernameNotFoundException
     */
    public String getUserId(String username) throws UsernameNotFoundException{
        try{
            String id = persondao.getUserId(username);
            return id;
        }catch(Exception e){
            logger.error("this user does not exist", e);
            throw new UsernameNotFoundException("");
        }
    }

    /**
     * Insert availability into db for the current person id
     * @param person_id the personid to identify availability
     * @param startDate startdate of availablity
     * @param endDate enddate of availability
     * @return rows updated
     * @throws InsertAvailabilityException
     */
    @Transactional(rollbackFor = {InsertAvailabilityException.class})
    public int updateAvailability(int person_id, String startDate, String endDate) throws DataNotFoundException, InsertAvailabilityException {
        Date start = Date.valueOf(startDate);
        Date end = Date.valueOf(endDate);
        try{
            return persondao.insertAvailability(person_id, start, end);
        }catch(Exception e){
            logger.error("unable to update availability", e);
            throw new InsertAvailabilityException("");
        }
    }

    /**
     * inserts new competenceprofile to databse for provided person id
     * @param person_id identifies the person
     * @param jobs array of jobs
     * @param years_of_xp array of experience for jobs
     * @throws DataNotFoundException
     */
    @Transactional(rollbackFor = {InsertCompetenceException.class})
    public void updateCompetenceProfile(int person_id, String[] jobs, float[] years_of_xp) throws DataNotFoundException, InsertCompetenceException {
        int[] job_ids = new int[3];
        for(int i = 0; i < jobs.length; i++){
            try{
                job_ids[i] = persondao.getCompetenceId(jobs[i]);
            }catch(Exception e){
                logger.error("unable to get job id from db");
                throw new DataNotFoundException("");
            }
        }
        for(int i = 0; i < years_of_xp.length; i++){
            try{
                int rows = persondao.insertCompetenceProfile(person_id, job_ids[i], years_of_xp[i]);
            }catch(Exception e){
                logger.error("unable to insert competence profiel");
                throw new InsertCompetenceException("");
            }
        }
    }

    /**
     * calls DAO with dates and retrieves available persons
     * @param startDate String
     * @param endDate String
     * @return List with person ids
     * @throws DataNotFoundException
     */
    public List<Available> getAvailability(String startDate, String endDate) throws DataNotFoundException {
        Date fromDate = Date.valueOf(startDate);
        Date toDate = Date.valueOf(endDate);
        List<Available> available = new ArrayList<Available>();
        try{
            //return persondao.getAvailablePersons(fromDate, toDate);
            List<Integer> avail = persondao.getAvailablePersons(fromDate, toDate);
            for(int id : avail){
                available.add(new Available(id, persondao.getEmailFromId(id)));
            }
            return available;
        }catch(Exception e){
            logger.error("unable to query availability");
            throw new DataNotFoundException("");
        }


    }

    /**
     * Insert application into databse, consists of two steps: inserting availability and inserting
     * competence profile
     * @param person_id identifes person
     * @param startDate startdate for availablity
     * @param endDate enddate for availablity
     * @param jobs array of jobs
     * @param years_of_xp array of experience for the jobs
     * @throws InsertApplicationFailedException
     */
    @Transactional(rollbackFor = {InsertApplicationFailedException.class})
    public void insertApplication(int person_id, String startDate, String endDate, String[] jobs, float[] years_of_xp) throws InsertApplicationFailedException {
        try{
            int res = updateAvailability(person_id, startDate, endDate);
            updateCompetenceProfile(person_id, jobs, years_of_xp);
        }catch(Exception e){
            logger.error("could not insert application, rollbacked");
            throw new InsertApplicationFailedException("");
        }
    }

    /**
    * Used for getting credentials from the database relating to a specific person.
    * @param  username of the person we want credentials from
    * @return          the credentials relating to a specific person
    */
    public String[] getCredentials(String username) throws UsernameNotFoundException, PasswordNotFoundException {
        String[] cred = new String[2];
        try{
            cred[0] = persondao.getEmail(username);
        }catch(Exception e){
            logger.error("username not found");
            throw new UsernameNotFoundException("");
        }
        try{
            cred[1] = persondao.getUserId(username);
        }catch(Exception e){
            logger.error("password not found");
            throw new PasswordNotFoundException("");
        }
        return cred;
    }

    /**
     * Get all info about a person from db given a username
     * @param username pointing to a user
     * @return person object
     * @throws DataNotFoundException
     */
    public Person getPersonObject3(String username) throws DataNotFoundException {
        try {
            return persondao.findPersonByUsername(username);
        }
        catch(Exception e) {
            logger.error("could not find person");
            throw new DataNotFoundException("");
        }
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
            throw new DataNotFoundException("");
        }catch(Exception e){
            logger.error("No password found");
            password = "";
        }
        try{
            username = persondao.getUsername(email);
            throw new DataNotFoundException("");
        }catch(Exception e){
            logger.error("no username found");
            username = "";
        }
        return new Person(name, surname, pnr, mail, password, role, username);
    }

    /**
     * Get all competences from db in a list
     * @return list of competences
     * @throws DataNotFoundException
     */
    public List<String> getAllCompetence() throws DataNotFoundException {
        try{
            return persondao.getAllCompetence();
        }catch(Exception e){
            logger.error("could not get competences");
            throw new DataNotFoundException("");
        }
    }

    /**
     * get role id for a user
     * @param email String
     * @return int role id
     * @throws DataNotFoundException
     */
    public int getRoleId(String email) throws DataNotFoundException{
        try{
            return persondao.getRoleid(email);
        }catch(Exception e){
            logger.error("User not found in db");
            throw new DataNotFoundException("");
        }
    }
}
