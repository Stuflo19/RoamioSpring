package com.stuflo.roamiospring.responses.itineraryItems;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllItemsResponse {
    private List<BookingResponse> bookings;
    private List<HotelResponse> hotels;
    private List<FlightResponse> flights;

    public AllItemsResponse(
            List<BookingResponse> bookings,
            List<HotelResponse> hotels,
            List<FlightResponse> flights
    ) {
        this.bookings = bookings;
        this.hotels = hotels;
        this.flights = flights;
    }
}
