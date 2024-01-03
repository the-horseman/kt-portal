package com.madeira.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.madeira.dto.auth.AuthenticationRequest;
import com.madeira.dto.auth.AuthenticationResponse;
import com.madeira.entity.Employee;
import com.madeira.jwt.JWTService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthenticationService {
    
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthenticationResponse login(AuthenticationRequest request ) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Employee employee = (Employee) authentication.getPrincipal();
        String token = jwtService.issueToken(
            employee.getEmail(), 
            this.getJWTContent(employee)
        );
        return new AuthenticationResponse(token, "Bearer", 86400000);
    }

    private Map<String, Object> getJWTContent(Employee employee) {
        Map<String, Object> content = new HashMap<>();
        content.put("name", employee.getName());
        content.put("employeeId", employee.getEmployeeId());
        content.put("scopes", employee.getAuthorities().stream()
            .map(role -> role.getAuthority())
            .collect(Collectors.toList())
        );
        return content;
    }

}
