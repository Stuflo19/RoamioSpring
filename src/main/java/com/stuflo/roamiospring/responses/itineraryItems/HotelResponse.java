package com.stuflo.roamiospring.responses.itineraryItems;

import com.stuflo.roamiospring.models.itineraryItems.Hotel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelResponse {
    private Long id;

    private String name;

    private String bookingReference;

    private Long checkIn;

    private Long checkOut;

    private Long itineraryId;

    public HotelResponse() {
    }

    public HotelResponse(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.bookingReference = hotel.getBookingReference();
        this.checkIn = hotel.getCheckIn();
        this.checkOut = hotel.getCheckOut();
        this.itineraryId = hotel.getItinerary().getId();
    }
}
