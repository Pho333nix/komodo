package com.IV1201VT221.IV1201.dao;

import com.IV1201VT221.IV1201.exceptions.EmailTakenException;
import com.IV1201VT221.IV1201.exceptions.PnrTakenException;
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
        Person person = new Person("name",
                "surname",
                "123456789",
                "asd@xd.com",
                "passwerd",
                1,
                "username"
        );
        int expected = 0;
        //when
        try {
            expected = persondao.insertPerson(person);
        } catch (UsernameTakenException e) {
            e.printStackTrace();
        } catch (EmailTakenException e) {
            e.printStackTrace();
        } catch (PnrTakenException e) {
            e.printStackTrace();
        }
        //then
        assertThat(expected).isEqualTo(1);
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void getCredentials() {
    }

    @Test
    void getPerson() {
    }
}