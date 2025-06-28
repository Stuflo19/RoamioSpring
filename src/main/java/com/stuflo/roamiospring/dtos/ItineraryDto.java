package com.stuflo.roamiospring.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItineraryDto {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
