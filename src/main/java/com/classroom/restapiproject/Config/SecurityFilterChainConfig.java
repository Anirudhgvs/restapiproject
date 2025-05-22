package com.classroom.restapiproject.Config;

import com.classroom.restapiproject.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityFilterChainConfig {
    // Inject your custom JWT filter (you need to create this)
     //private final JWTAuthenticationFilter jwtAuthenticationFilter;
    // Inject your UserDetailsService and PasswordEncoder
     @Autowired
     private final MyUserDetailsService userDetailsService;

     @Autowired
     private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityFilterChainConfig(/* JwtAuthenticationFilter jwtAuthenticationFilter, MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder */) {
        // this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        // this.userDetailsService = userDetailsService;
        // this.passwordEncoder = passwordEncoder;
    }

    // Configure the AuthenticationProvider (uses UserDetailsService and PasswordEncoder)
    @Bean
    public DaoAuthenticationProvider authenticationProvider(/* MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder */) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // authProvider.setUserDetailsService(userDetailsService);
        // authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // Configure the AuthenticationManager (used by the login endpoint)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    // Define the Security Filter Chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs (usually needed)
                .authorizeHttpRequests(auth -> auth
                        // Allow specific public endpoints (like the login endpoint)
                        .requestMatchers("/auth/**").permitAll()
                        // Require authentication for any other requests
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        // Configure session management to be stateless (important for JWT)
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider()) // Use our configured auth provider
        // Add the JWT filter BEFORE the standard UsernamePasswordAuthenticationFilter
        // .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);// Build the filter chain
        return http.build();
    }
}
