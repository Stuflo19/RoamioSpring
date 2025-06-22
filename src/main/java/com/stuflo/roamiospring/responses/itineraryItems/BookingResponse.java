package com.stuflo.roamiospring.responses.itineraryItems;

import com.stuflo.roamiospring.models.itineraryItems.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponse {
    private Long id;

    private String name;

    private String bookingReference;

    private Long bookingTime;

    private Long itineraryId;

    public BookingResponse(Booking booking) {
        this.id = booking.getId();
        this.name = booking.getName();
        this.bookingReference = booking.getBookingReference();
        this.bookingTime = booking.getBookingTime();
        this.itineraryId = booking.getItinerary().getId();
    }
}
