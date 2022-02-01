package com.IV1201VT221.IV1201.dao;

import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameNotFoundException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import com.IV1201VT221.IV1201.model.Person;
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
        if (userNameTaken > 0)
            throw new UsernameTakenException("");

        String sqlEmailTaken = "SELECT COUNT(*) FROM person WHERE email = ?";
        int emailTaken = jdbcTemplate.queryForObject(sqlEmailTaken, new Object[] {email}, Integer.class);
        if (emailTaken > 0)
            throw new EmailTakenException("");

        String sqlSsnTaken = "SELECT COUNT(*) FROM person WHERE pnr = ?";
        int ssnTaken = jdbcTemplate.queryForObject(sqlSsnTaken, new Object[] {email}, Integer.class);
        if (ssnTaken > 0)
            throw new PnrTakenException("");

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
                throw new UsernameNotFoundException("");
            }
            return uname;
        }catch(Exception e){
            System.out.println("USER NOT FOUND");
            System.out.println(e);
            return "";
        }

    }
}
