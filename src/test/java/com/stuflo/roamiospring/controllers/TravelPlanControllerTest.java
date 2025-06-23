package com.stuflo.roamiospring.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class TravelPlanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTravelPlansWhenEmptyTest() throws Exception {
        this.mockMvc.perform(
                        get("/plans").contentType(MediaType.APPLICATION_JSON)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Plan\",\"departureDate\":1750285667,\"userId\":1}]"));
    }
}
