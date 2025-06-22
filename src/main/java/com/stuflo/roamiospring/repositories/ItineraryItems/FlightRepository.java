package com.stuflo.roamiospring.repositories.ItineraryItems;

import com.stuflo.roamiospring.models.itineraryItems.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAllByItineraryId(Long itineraryId);
}
