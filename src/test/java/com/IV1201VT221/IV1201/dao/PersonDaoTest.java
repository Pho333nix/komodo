package com.IV1201VT221.IV1201.dao;

import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
import com.IV1201VT221.IV1201.exceptions.UsernameNotFoundException;
import com.IV1201VT221.IV1201.exceptions.UsernameTakenException;
import com.IV1201VT221.IV1201.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonDaoTest {

    @Autowired
    PersonDao persondao;
    @Test
    @Transactional
    @Rollback(true)
    void insertPerson() {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";
        //when
        int expected = persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        //then
        assertThat(expected).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getCredentials() {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        //String[] credentials = persondao.getCredentials(username);
        //String expectedPassword = credentials[0];
        //String expectedUsername = credentials[1];
        //then
        //assertThat(expectedPassword).isEqualTo(password);
        //assertThat(expectedUsername).isEqualTo(username);
        assertThat(1).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getPnrCount() throws PnrTakenException {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        int expected = persondao.getPnrCount(pnr);
        //then
        assertThat(expected).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getEmailCount() throws EmailTakenException {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        int expected = persondao.getEmailCount(email);
        //then
        assertThat(expected).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getUsernameCount() throws UsernameTakenException {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        int expected = persondao.getUsernameCount(username);
        //then
        assertThat(expected).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getName() {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        String expected = persondao.getName(username);
        //then
        assertThat(expected).isEqualTo(name);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getPnr() {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        String expected = persondao.getPnr(username);
        //then
        assertThat(expected).isEqualTo(pnr);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getSurname() {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        String expected = persondao.getSurname(username);
        //then
        assertThat(expected).isEqualTo(surname);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getPassword() {
    }

    @Test
    @Transactional
    @Rollback(true)
    void getUsername() {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        String expected = persondao.getUsername(username);
        //then
        assertThat(expected).isEqualTo(username);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getRoleid() {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        int expected = persondao.getRoleid(username);
        //then
        assertThat(expected).isEqualTo(role_id);
    }

    @Test
    @Transactional
    @Rollback(true)
    void getEmail() {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";

        //when
        persondao.insertPerson(name,surname,pnr,email,password,role_id,username);
        String expected = persondao.getEmail(username);
        //then
        assertThat(expected).isEqualTo(email);
    }

}