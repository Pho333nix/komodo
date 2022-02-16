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


@Repository("postgres")
public class PersonDao implements PersonDaoInterface {

    private final JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(PersonDao.class);

    @Autowired
    public PersonDao(JdbcTemplate jdbctemplate){
        this.jdbcTemplate = jdbctemplate;
    }

    @Override
    public int insertPerson(Person person) throws UsernameTakenException, EmailTakenException, PnrTakenException {
        int id = person.getID();
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
    public Person getPersonObject(String email){
        String sqlString = "SELECT name FROM person WHERE email = ?";
        String name = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        sqlString = "SELECT person_id FROM person WHERE email = ?";
        int id = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, Integer.class);
        sqlString = "SELECT surname FROM person WHERE email = ?";
        String surname = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        sqlString = "SELECT pnr FROM person WHERE email = ?";
        String pnr = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        sqlString = "SELECT password FROM person WHERE email = ?";
        String password = "";
        try{
            password = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        }catch(Exception e){
            logger.error("password not found, set empty string");
            password = "";
        }

        sqlString = "SELECT role_id FROM person WHERE email =?";
        int role_id = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, Integer.class);
        sqlString = "SELECT username FROM person WHERE email =?";
        String username = "";
        try{
            username = jdbcTemplate.queryForObject(sqlString, new Object[] {email}, String.class);
        }catch(Exception e){
            logger.error("username not found, set empty string");
            username = "";
        }
        return new Person(name, id, surname, pnr, email, password, role_id, username);

    }

    /*
    @param String username Uses email for now
    @return String[] array with username and password(email and person_id for now)
    */
    @Override
    public String[] getCredentials(String username) throws UsernameNotFoundException{
        String[] result = new String[2];
        String sql = "SELECT email FROM person WHERE email = ?";
        String sql2 = "SELECT person_id FROM person WHERE email = ?";
        result[0] = jdbcTemplate.queryForObject(sql, new Object[] {username}, String.class);
        result[1] = jdbcTemplate.queryForObject(sql2, new Object[] {username}, String.class);
        /*if(result[0] == null || result[1] == null){
            throw new UsernameNotFoundException("USER NOT FOUND");
        }*/
        return result;
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
