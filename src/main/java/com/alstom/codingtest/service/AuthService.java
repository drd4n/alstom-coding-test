package com.alstom.codingtest.service;

import com.alstom.codingtest.domain.request.auth.LogInRequest;
import com.alstom.codingtest.domain.request.auth.SignUpRequest;
import com.alstom.codingtest.domain.response.auth.LogInResponse;
import com.alstom.codingtest.entity.User;
import com.alstom.codingtest.exception.AuthErrorMessage;
import com.alstom.codingtest.mapper.UserMapper;
import com.alstom.codingtest.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            log.error("User not found: {}", email);
            throw new EntityNotFoundException(AuthErrorMessage.USER_NOT_FOUND.getMessage(email));
        });
    }

    @Transactional
    public void signup(SignUpRequest signUpRequest) {
        String email = signUpRequest.email();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            log.error("Email already exists: {}", email);
            throw new RuntimeException(AuthErrorMessage.EMAIL_DUPLICATED.getMessage(email));
        }
        String hashedPassword = passwordEncoder.encode(signUpRequest.password());
        User user = userMapper.fromSignUpRequest(signUpRequest, hashedPassword);
        userRepository.save(user);
        log.info("User created: {}", user.getEmail());
    }
}
