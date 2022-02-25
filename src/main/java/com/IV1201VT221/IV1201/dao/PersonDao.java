package com.IV1201VT221.IV1201.dao;

import com.IV1201VT221.IV1201.controller.Restcontroller;
import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameNotFoundException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import com.IV1201VT221.IV1201.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
* Person-Access Object implementing the DaoInterface.
* This class contains methods for directly interacting with the database.
* Classes wanting to use this Dao should be placed in the service package.
*/
@Repository("postgres")
public class PersonDao implements PersonDaoInterface {

    private final JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(PersonDao.class);

    /**
    * Constructor setting jdbcTemplate
    * @param  jdbctemplate object used for writing queries.
    */
    @Autowired
    public PersonDao(JdbcTemplate jdbctemplate){
        this.jdbcTemplate = jdbctemplate;
    }

    /**
     * Counts the number of same pnr in db
     * @param pnr
     * @return number of same pnr
     * @throws PnrTakenException
     */
    @Override
    public int getPnrCount(String pnr) throws PnrTakenException{
        String sqlSsnTaken = "SELECT COUNT(*) FROM person WHERE pnr = ?";
        int ssnTaken = jdbcTemplate.queryForObject(sqlSsnTaken, new Object[] {pnr}, Integer.class);
        return ssnTaken;
    }

    /**
     * Counts the number of same emails in db
     * @param email
     * @return number of emails that match
     * @throws EmailTakenException
     */
    @Override
    public int getEmailCount(String email) throws EmailTakenException{
        String sqlEmailTaken = "SELECT COUNT(*) FROM person WHERE email = ?";
        int emailTaken = jdbcTemplate.queryForObject(sqlEmailTaken, new Object[] {email}, Integer.class);
        return emailTaken;
    }

    /**
     * Counts the number of same usernames in db
     * @param username
     * @return the number of same usernames
     * @throws UsernameTakenException
     */
    @Override
    public int getUsernameCount(String username) throws UsernameTakenException{
        String sqlUsernameTaken = "SELECT COUNT(*) FROM person WHERE username = ?";
        int usernameTaken = jdbcTemplate.queryForObject(sqlUsernameTaken, new Object[] {username}, Integer.class);
        return usernameTaken;
    }

    /**
     * Inserts a person object into the db
     * @param name
     * @param surname
     * @param pnr
     * @param email
     * @param password
     * @param role_id
     * @param username
     * @return
     */
    @Override
    public int insertPerson(String name, String surname, String pnr, String email, String password,
                            int role_id, String username){
        int sqlReturnValue = jdbcTemplate.update(
                "INSERT INTO person(name, surname, pnr, email, password, role_id, username) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                name, surname, pnr, email, password, role_id, username);
        return sqlReturnValue;
    }

    /**
     * Not used
     * @param id
     * @param person
     * @return
     */
    @Override
    public int updatePerson(int id, Person person) {
        return 0;
    }

    /**
     * Not used
     * @param id
     * @param person
     * @return
     */
    @Override
    public int deletePerson(int id, Person person) {
        return 0;
    }

    /**
     * Return a name from the db that has given email
     * @param email
     * @return name
     */
    @Override
    public String getName(String email){
        String sqlString = "SELECT name FROM person WHERE email = ?";
        String name = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return name;
    }

    /**
     * Get pnr from db that has matching email
     * @param email
     * @return pnr
     */
    @Override
    public String getPnr(String email){
        String sqlString = "SELECT pnr FROM person WHERE email = ?";
        String pnr = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return pnr;
    }

    /**
     * Get surname that has matching email
     * @param email
     * @return surname
     */
    @Override
    public String getSurname(String email){
        String sqlString = "SELECT surname FROM person WHERE email = ?";
        String surname = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return surname;
    }

    /**
     * Get password that has matching email
     * @param email
     * @return password
     */
    @Override
    public String getPassword(String email){
        String sqlString = "SELECT password FROM person WHERE email = ?";
        String password = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return password;
    }

    /**
     * Get username that has matching email
     * @param email
     * @return username
     */
    @Override
    public String getUsername(String email){
        String sqlString = "SELECT username FROM person WHERE email = ?";
        String username = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return username;
    }

    /**
     * Get role id for a user that has given email
     * @param email
     * @return role_id
     */
    @Override
    public int getRoleid(String email){
        String sqlString = "SELECT role_id FROM person WHERE email = ?";
        int role = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, Integer.class);
        return role;
    }

    /**
     * Get email for a user given the email
     * @param email
     * @return email
     */
    @Override
    public String getEmail(String email){
        String sqlString = "SELECT email FROM person WHERE email = ?";
        String mail = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return mail;
    }

    /**
     * Get user id for a user that has given email
     * @param email
     * @return user_id
     */
    @Override
    public String getUserId(String email){
        String sql2 = "SELECT person_id FROM person WHERE email = ?";
        String userId = jdbcTemplate.queryForObject(sql2, new Object[] {email}, String.class);
        return userId;
    }

}
