package com.classroom.restapiproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    // ... other dependencies (like UserRepository) ...

    @Autowired // Spring injects the PasswordEncoder bean here
    public UserService(PasswordEncoder passwordEncoder /*, ...*/) {
        this.passwordEncoder = passwordEncoder;
        // ...
    }

    // Method to save a new user
    public User saveNewUser(User user) { // Assume 'user' object has a plain password field
        // 1. Hash the plain password using the injected encoder
        String hashedPassword = passwordEncoder.encode(user.getPlainPassword());

        // 2. Set the hashed password on the user object
        user.setHashedPassword(hashedPassword); // Assume User model has a setHashedPassword method

        // 3. Save the user object (with the hashed password) to the database
        // userRepository.save(user);

        return user; // Return the saved user
    }

    // Spring Security automatically uses the configured PasswordEncoder
    // when comparing passwords during the login process.
}
