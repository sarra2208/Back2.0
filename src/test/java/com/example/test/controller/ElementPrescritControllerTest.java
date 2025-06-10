package com.example.test.controller;

import com.example.test.application.service.ElementPrescritService;
import com.example.test.application.usecase.ElementPrescrit.DeleteElementPrescritUseCase;
import com.example.test.application.usecase.ElementPrescrit.UpdateElementPrescritUseCase;
import com.example.test.domain.model.ElementPrescrit;
import com.example.test.web.controller.ElementPrescritController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ElementPrescritControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ElementPrescritService elementPrescritService;

    @MockBean
    private UpdateElementPrescritUseCase updateElementPrescritUseCase;

    @MockBean
    private DeleteElementPrescritUseCase deleteElementPrescritUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private ElementPrescrit testElementPrescrit;

    @BeforeEach
    void setUp() {
        testElementPrescrit = new ElementPrescrit();
        testElementPrescrit.setId("1");
        testElementPrescrit.setNom("Test ElementPrescrit");
        // set other properties as needed
    }

    @Test
@WithMockUser(roles = "USER")
    void testSaveElementPrescrit() throws Exception {
        when(elementPrescritService.saveElementPrescrit(any(ElementPrescrit.class))).thenReturn(testElementPrescrit);

        mockMvc.perform(post("/api/v0/ElementPrescrit/addElementPrescrit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testElementPrescrit)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nom").value("Test ElementPrescrit"));

        verify(elementPrescritService, times(1)).saveElementPrescrit(any(ElementPrescrit.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetListElementPrescrits() throws Exception {
        when(elementPrescritService.getListElementPrescrits()).thenReturn(List.of(testElementPrescrit));

        mockMvc.perform(get("/api/v0/ElementPrescrit/listElementPrescrits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].nom").value("Test ElementPrescrit"));

        verify(elementPrescritService, times(1)).getListElementPrescrits();
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetElementPrescrit() throws Exception {
        when(elementPrescritService.getElementPrescritById("1")).thenReturn(Optional.of(testElementPrescrit));

        mockMvc.perform(get("/api/v0/ElementPrescrit/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nom").value("Test ElementPrescrit"));

        verify(elementPrescritService, times(1)).getElementPrescritById("1");
    }

    @Test
@WithMockUser(roles = "USER")
    void testDeleteElementPrescrit() throws Exception {
        doNothing().when(deleteElementPrescritUseCase).execute("1");

        mockMvc.perform(delete("/api/v0/ElementPrescrit/1"))
                .andExpect(status().isNoContent());

        verify(deleteElementPrescritUseCase, times(1)).execute("1");
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdateElementPrescritSuccess() throws Exception {
        when(updateElementPrescritUseCase.execute(eq("1"), any(ElementPrescrit.class))).thenReturn(testElementPrescrit);

        mockMvc.perform(put("/api/v0/ElementPrescrit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testElementPrescrit)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nom").value("Test ElementPrescrit"));

        verify(updateElementPrescritUseCase, times(1)).execute(eq("1"), any(ElementPrescrit.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdateElementPrescritFailure() throws Exception {
        when(updateElementPrescritUseCase.execute(eq("1"), any(ElementPrescrit.class)))
                .thenThrow(new RuntimeException("ElementPrescrit not found"));

        mockMvc.perform(put("/api/v0/ElementPrescrit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testElementPrescrit)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ElementPrescrit not found"));

        verify(updateElementPrescritUseCase, times(1)).execute(eq("1"), any(ElementPrescrit.class));
    }
}

