package com.IV1201VT221.IV1201.model;

/**
 * Authentication response
 */
public class AuthenticationResponse {

    private final String jwt;

    /**
     * Creates authentication reposnse with provided jwt token
     * @param jwt
     */
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Get jwt token
     * @return jwt token
     */
    public String getJwt() {
        return jwt;
    }
}
