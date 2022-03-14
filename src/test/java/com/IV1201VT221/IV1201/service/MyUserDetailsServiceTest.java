package com.IV1201VT221.IV1201.service;

import com.IV1201VT221.IV1201.dao.PersonDao;
import com.IV1201VT221.IV1201.exceptions.*;
import com.IV1201VT221.IV1201.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyUserDetailsServiceTest {
    @Autowired
    DatabaseService databaseService;
    @Autowired
    PersonDao persondao;


    @Test
    @Transactional
    @Rollback(true)
    void getCredentials() throws PnrTakenException, EmailTakenException, UsernameTakenException, UsernameNotFoundException, DataNotFoundException, PasswordNotFoundException {
        //given
        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"f");
        String expectedUsername = "email";
        databaseService.insertPerson(person);
        //when
        String expected[] = databaseService.getCredentials("email");
        //then
        assertThat(expected[0]).isEqualTo(expectedUsername);
    }

    @Test
    @Transactional
    @Rollback(true)
    void loadUserByUsername() {
    }
}