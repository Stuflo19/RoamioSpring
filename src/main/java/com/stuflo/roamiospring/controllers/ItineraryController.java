package com.stuflo.roamiospring.controllers;

import com.stuflo.roamiospring.dtos.ItineraryDto;
import com.stuflo.roamiospring.models.User;
import com.stuflo.roamiospring.responses.ItineraryResponse;
import com.stuflo.roamiospring.responses.itineraryItems.AllItemsResponse;
import com.stuflo.roamiospring.responses.itineraryItems.BookingResponse;
import com.stuflo.roamiospring.responses.itineraryItems.FlightResponse;
import com.stuflo.roamiospring.responses.itineraryItems.HotelResponse;
import com.stuflo.roamiospring.services.ItineraryService;
import com.stuflo.roamiospring.services.itineraryItems.BookingService;
import com.stuflo.roamiospring.services.itineraryItems.FlightService;
import com.stuflo.roamiospring.services.itineraryItems.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans/{planId}/itineraries")
public class ItineraryController {
    private final ItineraryService itineraryService;
    private final HotelService hotelService;
    private final FlightService flightService;
    private final BookingService bookingService;

    public ItineraryController(ItineraryService itineraryService, HotelService hotelService, FlightService flightService, BookingService bookingService) {
        this.itineraryService = itineraryService;
        this.hotelService = hotelService;
        this.flightService = flightService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<ItineraryResponse>> getItineraries(@PathVariable Long planId, @AuthenticationPrincipal User user) {
        return ResponseEntity
                .ok(itineraryService.getItineraries(planId, user.getId()));
    }

    @GetMapping("/{itineraryId}/allItems")
    public ResponseEntity<AllItemsResponse> getAllItineraryItems(@PathVariable Long planId, @PathVariable Long itineraryId, @AuthenticationPrincipal User user) {
        List<HotelResponse> hotels = hotelService.getHotels(itineraryId, user.getId());
        List<FlightResponse> flights = flightService.getFlights(itineraryId, user.getId());
        List<BookingResponse> bookings = bookingService.getBookings(itineraryId, user.getId());

        return ResponseEntity.ok(new AllItemsResponse(bookings, hotels, flights));
    }

    @PostMapping
    public ResponseEntity<ItineraryResponse> createItinerary(@PathVariable Long planId, @RequestBody ItineraryDto itineraryDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itineraryService.createItinerary(itineraryDto, planId));
    }
}
