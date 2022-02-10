package com.IV1201VT221.IV1201.model;
/*
Authentication response handles the jwt token to the response
 */
public class AuthenticationResponse {

    private final String jwt;
    /*
    @param jwt
    Sets the jwt token
     */
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
    /*
    @return jwt
    returns the jwt token
     */
    public String getJwt() {
        return jwt;
    }
}
