package com.IV1201VT221.IV1201.service;

import com.IV1201VT221.IV1201.dao.PersonDao;
import com.IV1201VT221.IV1201.exceptions.*;
import com.IV1201VT221.IV1201.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatabaseServiceTest {

    @Autowired
    DatabaseService databaseService;
    @Autowired
    PersonDao persondao;

    @Test
    @Transactional
    @Rollback(true)
    void insertPerson() throws PnrTakenException, EmailTakenException, UsernameTakenException {
        //given
        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"f");
        //when
        int expected = databaseService.insertPerson(person);
        //then
        assertThat(expected).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback(true)
    void insertPersonUsernameTakenException() throws PnrTakenException, EmailTakenException, UsernameTakenException {
        //given
        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"f");
        Person otherPerson = new Person("SomeoneElse", "else", "12345", "email3", "password3", 1,"f");
        //when
        databaseService.insertPerson(person);
        //then
        assertThatExceptionOfType(UsernameTakenException.class)
                .isThrownBy(() -> {
                    databaseService.insertPerson(otherPerson);
                });
    }

    @Test
    @Transactional
    @Rollback(true)
    void insertPersonEmailTakenException() throws PnrTakenException, EmailTakenException, UsernameTakenException {
        //given
        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"f");
        Person otherPerson = new Person("SomeoneElse", "else", "12345", "email", "password3", 1,"username");
        //when
        databaseService.insertPerson(person);
        //then
        assertThatExceptionOfType(EmailTakenException.class)
                .isThrownBy(() -> {
                    databaseService.insertPerson(otherPerson);
                });
    }

    @Test
    @Transactional
    @Rollback(true)
    void insertPersonPnrTakenException() throws PnrTakenException, EmailTakenException, UsernameTakenException {
        //given
        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"f");
        Person otherPerson = new Person("SomeoneElse", "else", "123456789-1234", "email3", "password3", 1,"asd");
        //when
        databaseService.insertPerson(person);
        //then
        assertThatExceptionOfType(UsernameTakenException.class)
                .isThrownBy(() -> {
                    databaseService.insertPerson(otherPerson);
                });
    }




    @Test
    @Transactional
    @Rollback(true)
    void getPersonObject() throws PnrTakenException, EmailTakenException, UsernameTakenException, DataNotFoundException {
        //given
        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"f");
        databaseService.insertPerson(person);
        //when
        Person expected = databaseService.getPersonObject("email");
        //then
        /*assertThat(expected)
                .usingRecursiveComparison()
                .isEqualTo(person);
         */
    }

    @Test
    @Transactional
    @Rollback(true)
    void getCredentialsUsernameNotFoundException() throws UsernameNotFoundException {
        //given
        //when
        //then
        assertThatExceptionOfType(UsernameNotFoundException.class)
                .isThrownBy(() -> {
                    databaseService.getCredentials("akjsdasiqwieijiss");
                });
    }
}