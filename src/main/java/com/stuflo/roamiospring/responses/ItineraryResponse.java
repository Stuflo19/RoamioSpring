package com.stuflo.roamiospring.responses;

import com.stuflo.roamiospring.models.Itinerary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItineraryResponse {
    private Long id;

    private String name;

    private Long travelPlanId;

    public ItineraryResponse() {}

    public ItineraryResponse(Itinerary itinerary) {
        this.id = itinerary.getId();
        this.name = itinerary.getName();
        this.travelPlanId = itinerary.getTravelPlan().getId();
    }
}
