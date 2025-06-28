package com.stuflo.roamiospring.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelPlanDto {
    @NotEmpty(message = "Travel plan name cannot be empty")
    private String name;

    @NotNull(message = "Departure date required")
    private Long departureDate;
}
