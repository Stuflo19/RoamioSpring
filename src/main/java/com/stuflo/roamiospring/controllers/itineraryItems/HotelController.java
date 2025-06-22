package com.stuflo.roamiospring.controllers.itineraryItems;

import com.stuflo.roamiospring.dtos.itineraryItems.HotelDto;
import com.stuflo.roamiospring.responses.itineraryItems.HotelResponse;
import com.stuflo.roamiospring.services.itineraryItems.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans/{planId}/itineraries/{itineraryId}/hotels")
public class HotelController {
    private final HotelService hotelService;

    HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> GetHotels(@PathVariable Long planId, @PathVariable Long itineraryId) {
        return ResponseEntity.ok(hotelService.getHotels(itineraryId));
    }

    @PostMapping
    public ResponseEntity<HotelResponse> CreateHotel(@PathVariable Long planId, @PathVariable Long itineraryId, @RequestBody HotelDto hotelDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hotelService.createHotel(hotelDto, itineraryId));
    }
}
