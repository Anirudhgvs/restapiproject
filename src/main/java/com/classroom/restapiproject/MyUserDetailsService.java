package com.classroom.restapiproject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    // You would inject your UserRepository here to load user data from the database
    // private final UserRepository userRepository;

    // ... constructor ...

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Load the user from your database based on the username
        // User user = userRepository.findByUsername(username)
        // if (user == null) {
        //    throw new UsernameNotFoundException("User not found");// }

        // For this example, let's use our simple hardcoded list conceptually
        // In a real app, you would load from the database and include roles
        if ("test_user".equals(username)) {
            // Return a Spring Security UserDetails object
            // It contains username, HASHED password, and authorities (roles)
            return org.springframework.security.core.userdetails.User
                    .withUsername("test_user")
                    .password(new BCryptPasswordEncoder().encode("password")) // Use your actual PasswordEncoder bean here!
                    .roles("USER") // Assign roles
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
