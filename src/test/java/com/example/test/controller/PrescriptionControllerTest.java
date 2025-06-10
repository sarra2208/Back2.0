package com.example.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PrescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")  // Simulate authenticated user with USER role
    public void testGetPrescription() throws Exception {
        mockMvc.perform(get("/api/v0/prescription/{id}", "some-id"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetListPrescriptions() throws Exception {
        mockMvc.perform(get("/api/v0/prescription/listPrescriptions"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testSavePrescription() throws Exception {
        String jsonBody = "{\"id\":\"1\",\"name\":\"Test Prescription\"}"; // Example JSON
        mockMvc.perform(post("/api/v0/prescription/addPrescription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testDeletePrescription() throws Exception {
        mockMvc.perform(delete("/api/v0/prescription/{id}", "some-id"))
                .andExpect(status().isNoContent());
    }


    @Test
    @WithMockUser(roles = "USER")
    public void testUpdatePrescriptionFailure() throws Exception {
        String jsonBody = "{\"id\":\"1\",\"name\":\"\"}"; // Invalid data causing failure
        mockMvc.perform(put("/api/v0/prescription/{id}", "non-existent-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest());
    }
}
