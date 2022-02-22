package com.IV1201VT221.IV1201.model;

public class JwtPerson {
    private final String jwtToken;
    private final Person person;

    public JwtPerson(String jwtToken, Person p){
        this.jwtToken = jwtToken;
        this.person = p;
    }

    public String getjwtToken(){
        return jwtToken;
    }
    public Person getPerson(){
        return person;
    }
}
