package com.IV1201VT221.IV1201.controller;

import com.IV1201VT221.IV1201.model.Person;
import com.IV1201VT221.IV1201.service.DatabaseService;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Person person = new Person("test", "tes", "123456789", "email", "password",1, "username");
        when(service.insertPerson(person)).thenReturn(1);
        /*this.mockMvc
                .perform(post("/api/ins")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"test\", \"surname\": \"tes\", \"pnr\":\"123456789\", \"email\": \"email\", \"password\":\"password\", \"role_id\": \"1\", \"username\":\"username\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(status().isOk());
                */
    }

    @Test
    void getPersonObject() {
    }

    @Test
    void createAuthenticationToken() {
    }
}