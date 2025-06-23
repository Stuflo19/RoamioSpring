package com.stuflo.roamiospring.repositories;

import com.stuflo.roamiospring.models.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    @Query("SELECT i from Itinerary i where i.id = :travelPlanId and i.travelPlan.user.id = :userId")
    List<Itinerary> findAllByTravelPlanIdAndUserId(Long travelPlanId, Long userId);
}
