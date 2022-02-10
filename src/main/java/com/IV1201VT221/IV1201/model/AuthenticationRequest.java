package com.IV1201VT221.IV1201.model;

/*
Username and password for authentication request
 */
public class AuthenticationRequest {
    private String username;
    private String password;

    public AuthenticationRequest() {
    }
    /*
    @param username
    @param password
    Create authentication request with username and password
     */
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /*
    @return username
     */
    public String getUsername() {
        return username;
    }
    /*
    @param username
    Set username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /*
    @return password
     */
    public String getPassword() {
        return password;
    }
    /*
    @param password
    Set password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
