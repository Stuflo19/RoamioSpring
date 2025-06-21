package com.stuflo.roamiospring.models.itineraryItems;

import com.stuflo.roamiospring.models.Itinerary;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "flights")
public class Flight implements ItineraryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String flightNumber;

    private String bookingReference;

    private Long departureTime;

    private Long arrivalTime;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @Override
    public Long getItemTime() {
        return 0L;
    }

    @Override
    public String getType() {
        return ItineraryItemTypeEnum.FLIGHT.name();
    }
}
