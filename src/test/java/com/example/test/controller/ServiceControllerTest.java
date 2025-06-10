package com.example.test.controller;

import com.example.test.domain.model.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void testGetService() throws Exception {
        mockMvc.perform(get("/api/v0/service/{id}", "some-id"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetListServices() throws Exception {
        mockMvc.perform(get("/api/v0/service/listServices"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testSaveService() throws Exception {
        String jsonBody = "{\"id\":\"1\",\"name\":\"Test Service\"}";
        mockMvc.perform(post("/api/v0/service/addService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testDeleteService() throws Exception {
        mockMvc.perform(delete("/api/v0/service/{id}", "some-id"))
                .andExpect(status().isNoContent());
    }



    @Test
    @WithMockUser(roles = "USER")
    public void testUpdateServiceFailure() throws Exception {
        String jsonBody = "{\"id\":\"1\",\"name\":\"\"}";
        mockMvc.perform(put("/api/v0/service/{id}", "non-existent-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest());
    }
}
