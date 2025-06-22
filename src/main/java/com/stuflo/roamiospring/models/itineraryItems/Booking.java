package com.stuflo.roamiospring.models.itineraryItems;

import com.stuflo.roamiospring.dtos.itineraryItems.BookingDto;
import com.stuflo.roamiospring.models.Itinerary;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "bookings")
public class Booking implements ItineraryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String bookingReference;

    private Long bookingTime;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    public Booking() {}

    public Booking(BookingDto bookingDto, Long itineraryId) {
        Itinerary itinerary = new Itinerary();
        itinerary.setId(itineraryId);

        this.name = bookingDto.getName();
        this.bookingReference = bookingDto.getBookingReference();
        this.bookingTime = bookingDto.getBookingTime();
        this.itinerary = itinerary;
    }

    @Override
    public Long getItemTime() {
        return 0L;
    }

    @Override
    public String getType() {
        return ItineraryItemTypeEnum.BOOKING.name();
    }
}
