package com.kolya.easy.controller;

import com.kolya.easy.model.AuthenticationRequest;
import com.kolya.easy.model.User;
import com.kolya.easy.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


public class AuthControllerTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldCreateUser() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        AuthenticationRequest requestData = new AuthenticationRequest();
        requestData.setUsername("testUser");
        requestData.setPassword("testPassword");

        ResponseEntity<String> response = authController.signUp(requestData);

        assertEquals("User created!", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldNotCreateUserUsernameTaken() {
        User user = new User();
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        AuthenticationRequest requestData = new AuthenticationRequest();
        requestData.setUsername("testUser");
        requestData.setPassword("testPassword");

        ResponseEntity<String> response = authController.signUp(requestData);

        assertEquals("Username is already taken!", response.getBody());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}