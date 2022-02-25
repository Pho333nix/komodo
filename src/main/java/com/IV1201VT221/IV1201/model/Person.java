package com.IV1201VT221.IV1201.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
* Person object representing a real person.
* The class is used for functionality where a person object might be useful for example inserting a person into the database.
*/
public class Person {
    protected String name;
    protected String surname;
    protected String pnr;
    protected String email;
    protected String password;
    protected int role_id;
    protected String username;


    /**
    * Constructor setting various values of a person.
    * @JsonProperty is used so that we can create a person using json filled requests.
    * @param  name of the person
    * @param  surname of the person
    * @param  pnr of the person 
    * @param  email that the person has.
    * @param  password that the person has.
    * @param  role_id that the person has.
    * @param  username that the person has.
    */
    public Person(
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("pnr") String pnr,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("role_id") int role_id,
            @JsonProperty("username") String username) {
        this.name = name;
        this.surname = surname;
        this.pnr = pnr;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName(){return this.name;}
    public String getSurname(){return this.surname;}
    public String getPnr(){return this.pnr;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public int getRoleid(){return this.role_id;}
    public String getUsername(){return this.username;}
}
