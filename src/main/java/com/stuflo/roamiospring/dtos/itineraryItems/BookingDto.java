package com.stuflo.roamiospring.dtos.itineraryItems;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {
    private String name;

    private String bookingReference;

    private Long bookingTime;
}
