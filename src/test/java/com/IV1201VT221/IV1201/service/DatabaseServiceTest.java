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

    /** * ***
     * Tests if InsertPerson method works.
     * We inesert a person and see if it the returns the expected result, which is 1 if it succeeds.
     */
    @Test
    @Transactional
    @Rollback(true)
    void insertPerson() throws PnrTakenException, EmailTakenException, UsernameTakenException, DataNotFoundException {
        //given
        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"f");
        //when
        int expected = databaseService.insertPerson(person);
        //then
        assertThat(expected).isEqualTo(1);
    }

    /** * ***
     * Tests if insertPerson will throw a UsernameTakenException
     * We inesert a person then we insert another person with the same username too see if it triggers.
     */
    @Test
    @Transactional
    @Rollback(true)
    void insertPersonUsernameTakenException() throws PnrTakenException, EmailTakenException, UsernameTakenException, DataNotFoundException {
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

    /** * ***
     * Tests if insertPerson will throw a EmailTakenException
     * We insert a person then we insert another person with the same email too see if it triggers.
     */
    @Test
    @Transactional
    @Rollback(true)
    void insertPersonEmailTakenException() throws PnrTakenException, EmailTakenException, UsernameTakenException, DataNotFoundException {
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

    /** * ***
     * Tests if insertPerson will throw a PnrTakenException
     * We insert a person then we insert another person with the same Pnr too see if it triggers.
     */
    @Test
    @Transactional
    @Rollback(true)
    void insertPersonPnrTakenException() throws PnrTakenException, EmailTakenException, UsernameTakenException, DataNotFoundException {
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


    /** * ***
     * Tests if when we insert a person the getPersonObject3 method will return
     * a person with the same attributes.
     */
    @Test
    @Transactional
    @Rollback(true)
    void getPersonObject() throws PnrTakenException, EmailTakenException, UsernameTakenException, DataNotFoundException {
        //given
        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"f");
        databaseService.insertPerson(person);
        //when
        Person expected = databaseService.getPersonObject3("f");
        //then
        assertThat(expected)
                .usingRecursiveComparison()
                .ignoringFields("password")
                .isEqualTo(person);

    }

    /** * ***
     * Tests if UsernameNotFoundException is thrown when
     * we try to get a username that is not in the database
     */
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

    /** * ***
     * Tests if the right credentials is returned when
     * trying to get the credentials of a person
     */
    @Test
    @Transactional
    @Rollback(true)
    void getCredentials() throws PnrTakenException, EmailTakenException, UsernameTakenException, UsernameNotFoundException {
        //given
        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"qwerty");
        String expectedUsername = "email";
        databaseService.insertPerson(person);
        //when
        String expected[] = databaseService.getCredentials("qwerty");
        //then
        assertThat(expected[0]).isEqualTo(expectedUsername);
    }

}
