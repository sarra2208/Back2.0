package com.example.test.controller;

import com.example.test.Util.JwtUtil;
import com.example.test.domain.model.AuthResponse;
import com.example.test.infrastructure.persistence.entity.UserAppEntity;
import com.example.test.infrastructure.persistence.repo.UserAppRepository;

import com.example.test.web.controller.AuthController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAppRepository userAppRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    private UserAppEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserAppEntity();
        testUser.setUsername("testuser");
        testUser.setPassword("testpassword");
        testUser.setRole("USER");
    }

    @Test
@WithMockUser(roles = "USER")
    void testLoginSuccess() throws Exception {
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        // Stub static JwtUtil.generateToken call -
        // since JwtUtil is static, best to mock JwtUtil with tools like PowerMockito if needed.
        // Here, we can do a simple trick by creating a wrapper or assume token generated.

        // But for simplicity, let's override the JwtUtil.generateToken method for the test context or
        // just verify the response contains a token string.

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testLoginFailure_InvalidCredentials() throws Exception {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Bad credentials") {});

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testRegisterSuccess() throws Exception {
        when(userAppRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(testUser.getPassword())).thenReturn("encodedPassword");
        when(userAppRepository.save(any(UserAppEntity.class))).thenReturn(testUser);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));

        verify(userAppRepository, times(1)).findByUsername(testUser.getUsername());
        verify(passwordEncoder, times(1)).encode(testUser.getPassword());
        verify(userAppRepository, times(1)).save(any(UserAppEntity.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testRegisterFailure_UsernameExists() throws Exception {
        when(userAppRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username already exists"));

        verify(userAppRepository, times(1)).findByUsername(testUser.getUsername());
        verify(passwordEncoder, never()).encode(any());
        verify(userAppRepository, never()).save(any());
    }
}
