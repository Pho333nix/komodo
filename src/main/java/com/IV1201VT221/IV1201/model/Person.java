package com.IV1201VT221.IV1201.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {
    protected String name;
    protected final int personID;
    protected String surname;
    protected String pnr;
    protected String email;
    protected String password;
    protected int role_id;
    protected String username;


    public Person(
            @JsonProperty("name") String name,
            @JsonProperty("id") int personID,
            @JsonProperty("surname") String surname,
            @JsonProperty("pnr") String pnr,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("role_id") int role_id,
            @JsonProperty("username") String username) {
        this.name = name;
        this.personID = personID;
        this.surname = surname;
        this.pnr = pnr;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.username = username;
    }


    public String getName(){return this.name;}
    public int getID(){return this.personID;}
    public String getSurname(){return this.surname;}
    public String getPnr(){return this.pnr;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public int getRoleid(){return this.role_id;}
    public String getUsername(){return this.username;}
}
