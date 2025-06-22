package com.stuflo.roamiospring.dtos.itineraryItems;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDto {
    private String name;

    private String bookingReference;

    private Long checkIn;

    private Long checkOut;
}
