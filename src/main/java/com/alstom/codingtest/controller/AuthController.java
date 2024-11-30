package com.alstom.codingtest.controller;

import com.alstom.codingtest.domain.request.auth.LogInRequest;
import com.alstom.codingtest.domain.request.auth.SignUpRequest;
import com.alstom.codingtest.domain.response.auth.LogInResponse;
import com.alstom.codingtest.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        authService.signup(signUpRequest);
    }
}
