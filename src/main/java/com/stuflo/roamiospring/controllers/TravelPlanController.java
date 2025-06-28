package com.stuflo.roamiospring.controllers;

import com.stuflo.roamiospring.dtos.TravelPlanDto;
import com.stuflo.roamiospring.models.User;
import com.stuflo.roamiospring.responses.TravelPlanResponse;
import com.stuflo.roamiospring.services.AuthenticationService;
import com.stuflo.roamiospring.services.TravelPlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/plans")
public class TravelPlanController {
    private final TravelPlanService service;
    private final AuthenticationService authenticationService;

    public TravelPlanController(TravelPlanService travelPlanService, AuthenticationService authenticationService) {
        this.service = travelPlanService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    ResponseEntity<List<TravelPlanResponse>> readTravelPlan() {
        Optional<User> user = authenticationService.getAuthenticatedUser();

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<TravelPlanResponse> travelPlans = service.findAll(user.get().getId());

        return ResponseEntity.ok(travelPlans);
    }

    @GetMapping("/{id}")
    ResponseEntity<TravelPlanResponse> readTravelPlanById(@PathVariable Long id) {
        Optional<User> user = authenticationService.getAuthenticatedUser();

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        TravelPlanResponse travelPlan = service.findById(user.get().getId(), id);

        if (travelPlan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(travelPlan);
    }

    @PostMapping
    ResponseEntity<TravelPlanResponse> createTravelPlan(@Valid @RequestBody TravelPlanDto travelPlan) {
        try {
            Optional<User> user = authenticationService.getAuthenticatedUser();

            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            TravelPlanResponse createdPlan = service.createTravelPlan(travelPlan, user.get().getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<TravelPlanResponse> updateTravelPlan(@RequestBody TravelPlanDto travelPlan, @PathVariable Long id) {
        Optional<User> user = authenticationService.getAuthenticatedUser();

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else if (travelPlan.getName() == null && travelPlan.getDepartureDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        TravelPlanResponse updatedPlan = service.updateTravelPlan(travelPlan, user.get().getId(), id);

        if (updatedPlan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTravelPlan(@PathVariable Long id) {
        Optional<User> user = authenticationService.getAuthenticatedUser();

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        TravelPlanResponse tp = service.findById(user.get().getId(), id);

        if (tp == null) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
