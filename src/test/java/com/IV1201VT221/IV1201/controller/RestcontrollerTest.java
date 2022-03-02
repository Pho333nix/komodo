package com.IV1201VT221.IV1201.controller;

import com.IV1201VT221.IV1201.model.Person;
import com.IV1201VT221.IV1201.service.DatabaseService;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@RunWith(SpringRunner.class)
class RestcontrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatabaseService service;

    @Test
    void getUser() {
    }

    @Test
    void getCred() {
    }

    @Test
    void insertUser() throws Exception {
        //given
        String name = "name";
        String surname = "surname";
        String pnr = "123456789-1011";
        String email =  "email";
        String password = "password";
        int role_id = 1;
        String username = "username";
        Person person = new Person(name,surname,pnr,email,password,role_id,username);

        //when
        when(service.insertPerson(ArgumentMatchers.any(Person.class))).thenReturn(1);

        //then
        this.mockMvc
                .perform(post("/api/ins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"name\", \"surname\": \"surname\", \"pnr\":\"123456789-1011\", \"email\": \"email\", \"password\":\"password\", \"role_id\": 1, \"username\":\"username\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void getPersonObject() {
    }

    @Test
    void createAuthenticationToken() {
    }
}