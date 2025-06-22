package com.stuflo.roamiospring.controllers.itineraryItems;

import com.stuflo.roamiospring.dtos.itineraryItems.BookingDto;
import com.stuflo.roamiospring.responses.itineraryItems.BookingResponse;
import com.stuflo.roamiospring.services.itineraryItems.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans/{planId}/itineraries/{itineraryId}/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> GetBookings(@PathVariable String planId, @PathVariable Long itineraryId) {
        return ResponseEntity
                .ok(bookingService.getBookings(itineraryId));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> CreateBooking(@PathVariable String planId, @PathVariable Long itineraryId, @RequestBody BookingDto bookingDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookingService.createBooking(bookingDto, itineraryId));
    }
}
