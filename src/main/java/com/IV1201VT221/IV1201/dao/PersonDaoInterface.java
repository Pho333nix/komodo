package com.IV1201VT221.IV1201.dao;

import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameNotFoundException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import com.IV1201VT221.IV1201.model.Person;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
* PersonDaoInterface for interacting with the database.
*/
public interface PersonDaoInterface {
    int updatePerson(int id, Person person);
    int deletePerson(int id, Person person);
    String getName(String email);
    String getEmail(String email);
    String getPnr(String email);
    String getSurname(String email);
    String getUsername(String email);
    String getPassword(String email);
    int insertAvailability(int person_id, Date startDate, Date endDate);
    int getRoleid(String email);
    String getUserId(String email);
    int insertPerson(String name, String surname, String pnr, String email, String password,
                     int role_id, String username);
    int getPnrCount(String pnr) throws PnrTakenException;
    int getEmailCount(String email) throws EmailTakenException;
    int getUsernameCount(String username) throws UsernameTakenException;
    List<String> getAllCompetence();
    int getCompetenceId(String jobName);
    int insertCompetenceProfile(int person_id, int competence_id, float years_of_experience);
}
