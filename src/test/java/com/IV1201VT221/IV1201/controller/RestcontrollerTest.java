package com.IV1201VT221.IV1201.controller;

import com.IV1201VT221.IV1201.dao.PersonDao;
import com.IV1201VT221.IV1201.exceptions.DataNotFoundException;
import com.IV1201VT221.IV1201.exceptions.UsernameNotFoundException;
import com.IV1201VT221.IV1201.model.Person;
import com.IV1201VT221.IV1201.service.DatabaseService;
import com.IV1201VT221.IV1201.service.MyUserDetailsService;
import com.IV1201VT221.IV1201.util.JwtUtil;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = Restcontroller.class)
@AutoConfigureMockMvc(addFilters = false)
class RestcontrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatabaseService service;
    @MockBean
    private MyUserDetailsService userDetailsService;
    @MockBean
    private JwtUtil jwtTokenUtil;

    @Test
    void getCred() throws Exception {
        //given
        String name = "Faiz";
        String credentials[] = new String[2];
        credentials[0]="password";
        credentials[1]="username";

        //when
        when(service.getCredentials(name)).thenReturn(credentials);

        //then
        this.mockMvc
                .perform(get("/api/cred/Faiz"))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"password\",\"username\"]"));
    }

    @Test
    void insertUser() throws Exception {
        //given

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
    void getCompetence() throws Exception {
        //given
        List<String> competences = new ArrayList();

        competences.add("ticket sales");
        competences.add("lotteries");
        competences.add("roller coaster operation");

        //when
        when(service.getAllCompetence()).thenReturn(competences);

        //then
        /*this.mockMvc
                .perform(get("/api/competence/"))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"ticket sales\",\"lotteries\",\"roller coaster operation\"]"));
         */
    }

    @Test
    void getPersonObject() throws Exception {
        //given
        String email = "email";

        Person person = new Person("Faiz", "Faizson", "123456789-1234", "email", "password", 1,"f");
        //when
        when(service.getPersonObject(email)).thenReturn(person);

        //then
        this.mockMvc
                .perform(get("/api/user/email")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Faiz"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Faizson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pnr").value("123456789-1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roleid").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("f"));
    }
}
