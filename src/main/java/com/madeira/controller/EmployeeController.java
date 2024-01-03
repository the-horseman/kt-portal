package com.madeira.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.madeira.dto.employee.CreateEmployeeRequest;
import com.madeira.dto.employee.CreateEmployeeResponse;
import com.madeira.jwt.JWTService;
import com.madeira.service.employee.EmployeeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    
    private static final String APPLICATION_VND_API_JSON = "application/vnd.api+json";
    private final EmployeeService employeeService;
    private final JWTService jwtService;

    @PostMapping(consumes = APPLICATION_VND_API_JSON, produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public ResponseEntity<?> createEmployee(
        @Valid @RequestBody final CreateEmployeeRequest employeeRequest
    ) {
        CreateEmployeeResponse createResponse = employeeService.addEmployee(employeeRequest);
        String jwtToken = jwtService.issueToken(employeeRequest.getEmail(), "ROLE_USER");
        return ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, jwtToken)
                        .body(createResponse);
    }

}
