package com.stuflo.roamiospring.dtos.itineraryItems;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDto {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Flight number cannot be empty")
    private String flightNumber;

    @NotEmpty(message = "Booking reference cannot be empty")
    private String bookingReference;

    @NotNull(message = "Departure time cannot be null")
    private Long departureTime;

    @NotNull(message = "Arrival time cannot be null")
    private Long arrivalTime;
}
