package com.stuflo.roamiospring.repositories;

import com.stuflo.roamiospring.models.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    List<Itinerary> findAllByTravelPlanId(Long travelPlanId);
}
