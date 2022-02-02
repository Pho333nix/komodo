package com.IV1201VT221.IV1201.service;

import com.IV1201VT221.IV1201.dao.PersonDao;
import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameNotFoundException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
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
}
