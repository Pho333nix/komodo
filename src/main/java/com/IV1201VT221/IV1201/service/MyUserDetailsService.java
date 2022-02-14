package com.IV1201VT221.IV1201.service;

import com.IV1201VT221.IV1201.dao.PersonDao;
import com.IV1201VT221.IV1201.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PersonDao persondao;

    @Autowired
    public MyUserDetailsService(@Qualifier("postgres") PersonDao persondao){
        this.persondao = persondao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        String[] cred = new String[2];
        try{
            cred = getCredentials(username);
        }catch(Exception e){
            System.out.println(e);
            throw new UsernameNotFoundException("user not found");
        }
        return new User(cred[0], cred[1], new ArrayList<>());
    }

    private String[] getCredentials(String username) throws UsernameNotFoundException, com.IV1201VT221.IV1201.exceptions.UsernameNotFoundException {
        return persondao.getCredentials(username);
    }
}
