package com.stuflo.roamiospring.dtos.itineraryItems;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDto {
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String bookingReference;

    @NotNull(message = "Check in cannot be null")
    private Long checkIn;

    @NotNull(message = "Check out cannot be null")
    private Long checkOut;
}
