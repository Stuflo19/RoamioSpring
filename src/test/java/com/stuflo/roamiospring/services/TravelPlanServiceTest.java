package com.stuflo.roamiospring.services;

import com.stuflo.roamiospring.responses.TravelPlanResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TravelPlanServiceTest {
    @Autowired
    private TravelPlanService service;

    @Test
    public void getTravelPlanById() {
        TravelPlanResponse tp = service.findById(1L);
        assertEquals(1L, tp.getId());
        assertEquals("Plan", tp.getName());
        assertEquals(1750285667L, tp.getDepartureDate());
    }
}
