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

    /** * ***
     * Tests if the insertPerson method works
     * InsertPerson insert a user into the database.
     */
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

    /** * ***
     * Tests if the getCredentials method works
     * getCredentials gets the credentials for a perticular user.
     */
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
        //assertThat(1).isEqualTo(1);
    }

    /** * ***
     * Tests if the getPnrCount method works
     * getPnrCount gets amount users with the same SSN
     */
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

    /** * ***
     * Tests if the getEmailCount method works
     * getEmailCount gets amount users with the same email
     */
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

    /** * ***
     * Tests if the getUsernameCount method works
     * getUsernameCount gets amount users with the same username
     */
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

    /** * ***
     * Tests if the getName method works
     * getName get the username of a user
     */
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

    /** * ***
     * Tests if the getPnr method works
     * getPnr get the SSN of a user dependdant on username
     */
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

    /** * ***
     * Tests if the getSurname method works
     * getSurname get the surname of a user dependant on username
     */
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

    /** * ***
     * Tests if the getUsername method works
     * getUsername gets the username of a user dependant on  a username
     */
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

    /** * ***
     * Tests if the getRoleId method works
     * getRoleid gets the role_id of a user dependant on  a username
     */
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

    /** * ***
     * Tests if the getEmail method works
     * getEmail gets the email of a user dependant on  a email
     */
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