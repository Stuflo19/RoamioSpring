package com.stuflo.roamiospring.controllers;

import com.stuflo.roamiospring.dtos.TravelPlanDto;
import com.stuflo.roamiospring.models.User;
import com.stuflo.roamiospring.responses.TravelPlanResponse;
import com.stuflo.roamiospring.services.TravelPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class TravelPlanController {
    private final TravelPlanService service;

    public TravelPlanController(TravelPlanService travelPlanService) {
        this.service = travelPlanService;
    }

    @GetMapping
    ResponseEntity<List<TravelPlanResponse>> readTravelPlan(@AuthenticationPrincipal User user) {
        List<TravelPlanResponse> travelPlans = service.findAll(user.getId());

        return ResponseEntity.ok(travelPlans);
    }

    @GetMapping("/{id}")
    ResponseEntity<TravelPlanResponse> readTravelPlanById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        TravelPlanResponse travelPlan = service.findById(user.getId(), id);

        if (travelPlan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(travelPlan);
    }

    @PostMapping
    ResponseEntity<TravelPlanResponse> createTravelPlan(@RequestBody TravelPlanDto travelPlan, @AuthenticationPrincipal User user) {
        try {
            TravelPlanResponse createdPlan = service.createTravelPlan(travelPlan, user.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<TravelPlanResponse> updateTravelPlan(@RequestBody TravelPlanDto travelPlan, @PathVariable Long id, @AuthenticationPrincipal User user) {
        TravelPlanResponse updatedPlan = service.updateTravelPlan(travelPlan, user.getId(), id);

        if (updatedPlan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTravelPlan(@PathVariable Long id, @AuthenticationPrincipal User user) {
        TravelPlanResponse tp = service.findById(user.getId(), id);

        if (tp == null) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
