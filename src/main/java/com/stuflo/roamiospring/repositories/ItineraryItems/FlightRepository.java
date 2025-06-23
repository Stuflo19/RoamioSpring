package com.stuflo.roamiospring.repositories.ItineraryItems;

import com.stuflo.roamiospring.models.itineraryItems.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f from Flight f where f.itinerary.id = :itineraryId and f.itinerary.travelPlan.user.id = :userId")
    List<Flight> findAllByItineraryIdAndUserId(Long itineraryId, Long userId);
}
