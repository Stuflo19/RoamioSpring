package com.stuflo.roamiospring.models.itineraryItems;

import com.stuflo.roamiospring.dtos.itineraryItems.FlightDto;
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

    public Flight() {
    }

    public Flight(FlightDto flightDto, Long itineraryId) {
        Itinerary itinerary = new Itinerary();
        itinerary.setId(itineraryId);

        this.name = flightDto.getName();
        this.flightNumber = flightDto.getFlightNumber();
        this.bookingReference = flightDto.getBookingReference();
        this.departureTime = flightDto.getDepartureTime();
        this.arrivalTime = flightDto.getArrivalTime();
        this.itinerary = itinerary;
    }

    @Override
    public Long getItemTime() {
        return 0L;
    }

    @Override
    public String getType() {
        return ItineraryItemTypeEnum.FLIGHT.name();
    }
}
