package com.stuflo.roamiospring.models.itineraryItems;

import com.stuflo.roamiospring.dtos.itineraryItems.HotelDto;
import com.stuflo.roamiospring.models.Itinerary;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "hotels")
@Getter
public class Hotel implements ItineraryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String bookingReference;

    private Long checkIn;

    private Long checkOut;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    public Hotel() {
    }

    public Hotel(HotelDto hotelDto, Long itineraryId) {
        Itinerary itinerary = new Itinerary();
        itinerary.setId(itineraryId);

        this.name = hotelDto.getName();
        this.bookingReference = hotelDto.getBookingReference();
        this.checkIn = hotelDto.getCheckIn();
        this.checkOut = hotelDto.getCheckOut();
        this.itinerary = itinerary;
    }

    @Override
    public Long getItemTime() {
        return 0L;
    }

    @Override
    public String getType() {
        return ItineraryItemTypeEnum.HOTEL.name();
    }
}
