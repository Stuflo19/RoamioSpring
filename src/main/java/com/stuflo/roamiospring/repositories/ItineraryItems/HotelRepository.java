package com.stuflo.roamiospring.repositories.ItineraryItems;

import com.stuflo.roamiospring.models.itineraryItems.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h from Hotel h where h.itinerary.id = :itineraryId and h.itinerary.travelPlan.user.id = :userId")
    List<Hotel> findAllByItineraryIdAndUserId(Long itineraryId, Long userId);
}
