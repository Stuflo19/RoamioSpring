package com.stuflo.roamiospring.models.itineraryItems;

import com.stuflo.roamiospring.models.Itinerary;
import com.stuflo.roamiospring.models.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "hotels")
@Getter
public class Hotel implements ItineraryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String bookingReference;

    private Long checkIn;

    private Long checkOut;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @Override
    public Long getItemTime() {
        return 0L;
    }

    @Override
    public String getType() {
        return ItineraryItemTypeEnum.HOTEL.name();
    }
}
