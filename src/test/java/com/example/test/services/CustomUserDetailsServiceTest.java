package com.example.test.services;

import com.example.test.application.service.CustomUserDetailsService;
import com.example.test.infrastructure.persistence.entity.UserAppEntity;
import com.example.test.infrastructure.persistence.repo.UserAppRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    private UserAppRepository userAppRepository;
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        userAppRepository = mock(UserAppRepository.class);
        customUserDetailsService = new CustomUserDetailsService();
        customUserDetailsService.userAppRepository = userAppRepository; // Direct field injection
    }

    @Test
    void testLoadUserByUsername_Success() {
        String username = "testuser";
        String password = "testpass";

        UserAppEntity mockUser = new UserAppEntity();
        mockUser.setUsername(username);
        mockUser.setPassword(password);

        when(userAppRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());

        verify(userAppRepository, times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsername_NotFound() {
        String username = "nonexistent";

        when(userAppRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });

        verify(userAppRepository, times(1)).findByUsername(username);
    }
}
