package com.madeira.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.madeira.jwt.JWTAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityFilterChainConfig {
    
    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    // private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
            // .requestMatchers("/api/v1/employees").hasRole("ADMIN")
            // .requestMatchers(HttpMethod.POST, "/api/v1/products").hasRole("ADMIN")
            // .requestMatchers(HttpMethod.PATCH, "/api/v1/products").hasRole("ADMIN")
            // .requestMatchers(HttpMethod.DELETE, "/api/v1/products").hasRole("ADMIN")
            // .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAnyRole("ADMIN", "USER")
            // .requestMatchers(HttpMethod.PATCH, "/api/v1/**").hasAnyRole("ADMIN", "USER")
            // .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasAnyRole("ADMIN", "USER")
            // .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                .anyRequest().permitAll()
            ).sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ).authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            // .exceptionHandling(exceptionHandling ->
            //     exceptionHandling.authenticationEntryPoint(authenticationEntryPoint)
            // );

        return http.build();
    }

}
