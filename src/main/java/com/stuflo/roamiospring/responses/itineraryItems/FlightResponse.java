package com.stuflo.roamiospring.responses.itineraryItems;

import com.stuflo.roamiospring.models.itineraryItems.Flight;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightResponse {
    private Long id;

    private String name;

    private String flightNumber;

    private String bookingReference;

    private Long departureTime;

    private Long arrivalTime;

    private Long itineraryId;

    public FlightResponse(Flight flight) {
        this.id = flight.getId();
        this.name = flight.getName();
        this.flightNumber = flight.getFlightNumber();
        this.bookingReference = flight.getBookingReference();
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
        this.itineraryId = flight.getItinerary().getId();
    }
}
