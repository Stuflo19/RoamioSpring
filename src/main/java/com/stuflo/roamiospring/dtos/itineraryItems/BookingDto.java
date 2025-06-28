package com.stuflo.roamiospring.dtos.itineraryItems;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Name cannot be empty")
    private String bookingReference;

    @NotNull(message = "Booking time cannot be null")
    private Long bookingTime;
}
