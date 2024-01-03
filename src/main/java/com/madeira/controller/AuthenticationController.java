package com.madeira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.madeira.dto.auth.AuthenticationRequest;
import com.madeira.dto.auth.AuthenticationResponse;
import com.madeira.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private static final String APPLICATION_VND_API_JSON = "application/vnd.api+json";
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/login", produces = APPLICATION_VND_API_JSON, consumes = APPLICATION_VND_API_JSON)
    @ResponseBody
    public AuthenticationResponse employeeLogin(
        @Valid @RequestBody final AuthenticationRequest request
    ) {
        return authenticationService.login(request);
    }

}
