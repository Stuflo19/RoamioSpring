package com.stuflo.roamiospring.responses;

import com.stuflo.roamiospring.models.TravelPlan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelPlanResponse {
    private Long id;

    private String name;

    private Long departureDate;

    private Long userId;

    public TravelPlanResponse(TravelPlan travelPlan) {
        this.id = travelPlan.getId();
        this.name = travelPlan.getName();
        this.departureDate = travelPlan.getDepartureDate();
        this.userId = travelPlan.getUser().getId();
    }
}
