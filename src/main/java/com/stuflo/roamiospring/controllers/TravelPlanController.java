package com.stuflo.roamiospring.controllers;

import com.stuflo.roamiospring.dtos.TravelPlanDto;
import com.stuflo.roamiospring.responses.TravelPlanResponse;
import com.stuflo.roamiospring.services.TravelPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<TravelPlanResponse>> readTravelPlan() {
        List<TravelPlanResponse> travelPlans = service.findAll();

        return ResponseEntity.ok(travelPlans);
    }

    @GetMapping("/{id}")
    ResponseEntity<TravelPlanResponse> readTravelPlanById(@PathVariable Long id) {
        TravelPlanResponse travelPlan = service.findById(id);

        if (travelPlan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(travelPlan);
    }

    @PostMapping
    ResponseEntity<TravelPlanResponse> createTravelPlan(@RequestBody TravelPlanDto travelPlan) {
        try {
            TravelPlanResponse createdPlan = service.createTravelPlan(travelPlan);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<TravelPlanResponse> updateTravelPlan(@RequestBody TravelPlanDto travelPlan, @PathVariable Long id) {
        TravelPlanResponse updatedPlan = service.updateTravelPlan(travelPlan, id);

        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTravelPlan(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
