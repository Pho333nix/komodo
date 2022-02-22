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
    * Inserts a person into the database.
    * 
    * @param  person to be inserted.
    * @return        a integer representing if the insertion was successful or not. 
    */
    @Override
    public int insertPerson(Person person) throws UsernameTakenException, EmailTakenException, PnrTakenException {
        System.out.println("test");
        String name = person.getName();
        String surname = person.getSurname();
        String pnr = person.getPnr();
        String email = person.getEmail();
        String password = person.getPassword();
        int role_id = person.getRoleid();
        String username = person.getUsername();

        String sqlUsernameTaken = "SELECT COUNT(*) FROM person WHERE username = ?";
        int userNameTaken = jdbcTemplate.queryForObject(sqlUsernameTaken, new Object[] {username}, Integer.class);
        if (userNameTaken > 0) {
            logger.error("username taken");
            throw new UsernameTakenException("");
        }

        String sqlEmailTaken = "SELECT COUNT(*) FROM person WHERE email = ?";
        int emailTaken = jdbcTemplate.queryForObject(sqlEmailTaken, new Object[] {email}, Integer.class);
        if (emailTaken > 0) {
            logger.error("email taken");
            throw new EmailTakenException("");
        }

        String sqlSsnTaken = "SELECT COUNT(*) FROM person WHERE pnr = ?";
        int ssnTaken = jdbcTemplate.queryForObject(sqlSsnTaken, new Object[] {email}, Integer.class);
        if (ssnTaken > 0) {
            logger.error("pnr taken");
            throw new PnrTakenException("");
        }

        int sqlReturnValue = jdbcTemplate.update(
                "INSERT INTO person(name, surname, pnr, email, password, role_id, username) " + "VALUES (?, ?, ?, ?, ?, ?, ?)", name, surname, pnr, email, password, role_id, username);
        return sqlReturnValue;
    }

    @Override
    public int updatePerson(int id, Person person) {
        return 0;
    }

    @Override
    public int deletePerson(int id, Person person) {
        return 0;
    }

    @Override
    public String getName(String email){
        String sqlString = "SELECT name FROM person WHERE email = ?";
        String name = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return name;
    }

    @Override
    public String getPnr(String email){
        String sqlString = "SELECT pnr FROM person WHERE email = ?";
        String pnr = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return pnr;
    }

    @Override
    public String getSurname(String email){
        String sqlString = "SELECT surname FROM person WHERE email = ?";
        String surname = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return surname;
    }

    @Override
    public String getPassword(String email){
        String sqlString = "SELECT password FROM person WHERE email = ?";
        String password = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return password;
    }

    @Override
    public String getUsername(String email){
        String sqlString = "SELECT username FROM person WHERE email = ?";
        String username = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return username;
    }

    @Override
    public int getRoleid(String email){
        String sqlString = "SELECT role_id FROM person WHERE email = ?";
        int role = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, Integer.class);
        return role;
    }

    @Override
    public String getEmail(String email){
        String sqlString = "SELECT email FROM person WHERE email = ?";
        String mail = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        return mail;
    }


    @Override
    public String getUserId(String email){
        String sql2 = "SELECT person_id FROM person WHERE email = ?";
        String userId = jdbcTemplate.queryForObject(sql2, new Object[] {email}, String.class);
        return userId;
    }

    /*
    @param username The username to check for in the database
    @return return the username is exists
     */
    @Override
    public String getPerson(String username) throws UsernameNotFoundException {
        String sqlGetUser = "SELECT email FROM person WHERE email = ?";
        try{
            String uname = jdbcTemplate.queryForObject(sqlGetUser, new Object[] {username}, String.class);
            if(uname.isEmpty() || uname == null){
                logger.error("username not found");
                throw new UsernameNotFoundException("");
            }
            return uname;
        }catch(Exception e){
            logger.error("user not found", e);
            return "";
        }

    }
}
