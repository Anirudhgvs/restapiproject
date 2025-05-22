package com.classroom.restapiproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    // Inject your JWT token generator
    // private final JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager /*, JwtTokenGenerator jwtTokenGenerator*/) {
        this.authenticationManager = authenticationManager;
        // this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthenticationResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Use AuthenticationManager to authenticate the user
        // This manager uses your UserDetailsService and PasswordEncoder
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // If authentication is successful, generate the JWT
        // String jwt = jwtTokenGenerator.generateToken(authentication); // Example call

        // Return the JWT in the response
        // return ResponseEntity.ok(new JwtAuthenticationResponse(jwt)); // Example response object

        // Placeholder success response for now
        return ResponseEntity.ok(new JWTAuthenticationResponse("fake-jwt-token-generated"));
    }




}
