package com.stuflo.roamiospring.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "itineraries")
@Getter
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
