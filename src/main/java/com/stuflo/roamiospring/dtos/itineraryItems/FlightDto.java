package com.stuflo.roamiospring.dtos.itineraryItems;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDto {
    private String name;

    private String flightNumber;

    private String bookingReference;

    private Long departureTime;

    private Long arrivalTime;
}
