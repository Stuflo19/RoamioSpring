package com.stuflo.roamiospring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stuflo.roamiospring.dtos.TravelPlanDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TravelPlanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //GET TESTS
    //region
    @Test
    public void unauthorizedGetTest() throws Exception {
        this.mockMvc.perform(
                        get("/plans").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(1)
    public void getTravelPlansTest() throws Exception {
        this.mockMvc.perform(
                        get("/plans").contentType(MediaType.APPLICATION_JSON)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Plan\",\"departureDate\":1750285667,\"userId\":1}]"));
    }

    @Test
    @Order(1)
    public void getTravelPlanByIdTest() throws Exception {
        this.mockMvc.perform(
                        get("/plans/1").contentType(MediaType.APPLICATION_JSON)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Plan\",\"departureDate\":1750285667,\"userId\":1}"));
    }

    @Test
    public void getTravelPlanByIdWithWrongUserTest() throws Exception {
        this.mockMvc.perform(
                        get("/plans/1").contentType(MediaType.APPLICATION_JSON)
                                .with(user("un@authorized.com").password("password").roles("USER"))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void unauthorizedGetTravelPlanByIdTest() throws Exception {
        this.mockMvc.perform(
                        get("/plans/1").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized());
    }
    //endregion

    //CREATE TESTS
    //region
    @Test
    public void unauthorizedPostTest() throws Exception {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setName("TestPlan");
        travelPlanDto.setDepartureDate(1750285667L);

        String jsonString = new ObjectMapper().writeValueAsString(travelPlanDto);

        this.mockMvc.perform(
                        post("/plans")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(2)
    public void createTravelPlan() throws Exception {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setName("TestPlan");
        travelPlanDto.setDepartureDate(1750285667L);

        String jsonString = new ObjectMapper().writeValueAsString(travelPlanDto);

        this.mockMvc.perform(
                        post("/plans")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":2,\"name\":\"TestPlan\",\"departureDate\":1750285667,\"userId\":1}"));
    }

    @Test
    public void createTravelPlanReturnsErrorWhenNameEmptyTest() throws Exception {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setName("");
        travelPlanDto.setDepartureDate(1750285667L);
        String jsonString = new ObjectMapper().writeValueAsString(travelPlanDto);

        this.mockMvc.perform(
                        post("/plans")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"name\":\"Travel plan name cannot be empty\"}"));
    }

    @Test
    public void createTravelPlanReturnsErrorWhenNullDepartureDateTest() throws Exception {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setName("Test Plan");
        travelPlanDto.setDepartureDate(null);
        String jsonString = new ObjectMapper().writeValueAsString(travelPlanDto);

        this.mockMvc.perform(
                        post("/plans")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"departureDate\":\"Departure date required\"}"));
    }
    //endregion

    //PUT TESTS
    //region
    @Test
    public void unauthorizedPutTest() throws Exception {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setName("TestPlan");
        travelPlanDto.setDepartureDate(1750285667L);
        String jsonString = new ObjectMapper().writeValueAsString(travelPlanDto);

        this.mockMvc.perform(
                        put("/plans/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(3)
    public void fullDtoUpdatesTravelPlan() throws Exception {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setName("Updated TestPlan");
        travelPlanDto.setDepartureDate(123L);
        String jsonString = new ObjectMapper().writeValueAsString(travelPlanDto);

        this.mockMvc.perform(
                        put("/plans/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Updated TestPlan\",\"departureDate\":123,\"userId\":1}"));
    }

    @Test
    @Order(4)
    public void nameOnlyDtoUpdatesTravelPlanName() throws Exception {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setName("Name only update");
        String jsonString = new ObjectMapper().writeValueAsString(travelPlanDto);

        this.mockMvc.perform(
                        put("/plans/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name only update"))
                .andExpect(jsonPath("$.departureDate").isNumber());
    }

    @Test
    @Order(5)
    public void departureDateOnlyDtoUpdatesTravelPlanDepartureDate() throws Exception {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setDepartureDate(123456789L);
        String jsonString = new ObjectMapper().writeValueAsString(travelPlanDto);

        this.mockMvc.perform(
                        put("/plans/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.departureDate").value(123456789L));
    }

    @Test
    public void userCannotUpdateTravelPlansThatAreNotTheirsTest() throws Exception {
        TravelPlanDto travelPlanDto = new TravelPlanDto();
        travelPlanDto.setDepartureDate(123456789L);
        String jsonString = new ObjectMapper().writeValueAsString(travelPlanDto);

        this.mockMvc.perform(
                        put("/plans/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                                .with(user("un@authorized.com").password("password").roles("USER"))
                )
                .andExpect(status().isNotFound());
    }
    //endregion

    //DELETE TESTS
    //region
    @Test
    public void unauthorizedDeleteTest() throws Exception {
        this.mockMvc.perform(
                        delete("/plans/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(6)
    public void deleteDoesNotRemoveWhenUserDoesNotOwn() throws Exception {
        this.mockMvc.perform(
                        delete("/plans/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user("un@authorized.com").password("password").roles("USER"))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(6)
    public void deleteSuccessfullyRemoves() throws Exception {
        this.mockMvc.perform(
                        delete("/plans/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user("joe@bloggs.com").password("password").roles("USER"))
                )
                .andExpect(status().isOk());
    }
    //endregion
}
