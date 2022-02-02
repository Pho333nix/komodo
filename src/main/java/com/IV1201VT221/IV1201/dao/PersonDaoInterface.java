package com.IV1201VT221.IV1201.dao;

import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameNotFoundException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import com.IV1201VT221.IV1201.model.Person;

import java.util.UUID;

public interface PersonDaoInterface {
    int insertPerson(Person user) throws UsernameTakenException, EmailTakenException, PnrTakenException;
    int updatePerson(int id, Person person);
    int deletePerson(int id, Person person);
    String getPerson(String username) throws UsernameNotFoundException;
    String[] getCredentials(String username) throws UsernameNotFoundException;
}
