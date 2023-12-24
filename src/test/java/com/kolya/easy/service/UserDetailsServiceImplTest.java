package com.kolya.easy.service;

import com.kolya.easy.model.User;
import com.kolya.easy.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize the mocks
    }

    @Test
    void loadUserByUsername_existingUser_shouldSucceed() {
        User user = new User();
        user.setUsername("Test");
        when(userRepository.findByUsername("Test")).thenReturn(Optional.of(user));

        UserDetails returnedUserDetails = userDetailsService.loadUserByUsername("Test");

        assertNotNull(returnedUserDetails);
        assertEquals(user.getUsername(), returnedUserDetails.getUsername());
    }

    @Test
    void loadUserByUsername_nonExistingUser_shouldThrowUsernameNotFoundException() {
        when(userRepository.findByUsername("Test")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("Test"));
    }
}