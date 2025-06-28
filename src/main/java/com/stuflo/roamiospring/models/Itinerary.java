package com.stuflo.roamiospring.models;

import com.stuflo.roamiospring.dtos.ItineraryDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itineraries")
@Getter
@Setter
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "travel_plan_id")
    private TravelPlan travelPlan;

    public Itinerary() {

    }

    public Itinerary(ItineraryDto itineraryDto, Long travelPlanId) {
        TravelPlan emptyTravelPlan = new TravelPlan();
        emptyTravelPlan.setId(travelPlanId);

        this.name = itineraryDto.getName();
        this.travelPlan = emptyTravelPlan;
    }
}
