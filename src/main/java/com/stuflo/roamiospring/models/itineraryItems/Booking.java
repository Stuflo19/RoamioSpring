package com.stuflo.roamiospring.models.itineraryItems;

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

    @Override
    public Long getItemTime() {
        return 0L;
    }

    @Override
    public String getType() {
        return ItineraryItemTypeEnum.BOOKING.name();
    }
}
