package com.stuflo.roamiospring.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelPlanDto {
    private String name;

    private Long departureDate;

    private Long userId;
}
