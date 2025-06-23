package com.stuflo.roamiospring.controllers.itineraryItems;

import com.stuflo.roamiospring.dtos.itineraryItems.FlightDto;
import com.stuflo.roamiospring.models.User;
import com.stuflo.roamiospring.responses.itineraryItems.FlightResponse;
import com.stuflo.roamiospring.services.itineraryItems.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/plans/{planId}/itineraries/{itineraryId}/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightResponse>> GetFlights(@PathVariable Long planId, @PathVariable Long itineraryId, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(flightService.getFlights(itineraryId, user.getId()));
    }

    @PostMapping
    public ResponseEntity<FlightResponse> CreateFlight(@PathVariable Long planId, @PathVariable Long itineraryId, @RequestBody FlightDto flightDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.createFlight(flightDto, itineraryId));
    }
}
