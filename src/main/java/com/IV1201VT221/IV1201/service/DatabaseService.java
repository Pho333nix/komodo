package com.IV1201VT221.IV1201.service;

import com.IV1201VT221.IV1201.dao.PersonDao;
import com.IV1201VT221.IV1201.exceptions.*;
import com.IV1201VT221.IV1201.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final PersonDao persondao;

    @Autowired
    public DatabaseService(@Qualifier("postgres") PersonDao persondao){
        this.persondao = persondao;
    }

    public int insertPerson(Person person) throws PnrTakenException, EmailTakenException, UsernameTakenException {
        return persondao.insertPerson(person);
    }

    public String getPerson(String username) throws UsernameNotFoundException{
        return persondao.getPerson(username);
    }

    public String[] getCredentials(String username) throws UsernameNotFoundException{
        return persondao.getCredentials(username);
    }

    public Person getPersonObject(String email) throws DataNotFoundException {
        return persondao.getPersonObject(email);
    }
}
