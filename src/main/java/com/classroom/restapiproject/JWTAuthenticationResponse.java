package com.classroom.restapiproject;

public class JWTAuthenticationResponse {
    private String accessToken;
    // ... constructor and getter ...
    public JWTAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() { return accessToken; }
}
