package com.stuflo.roamiospring.repositories.ItineraryItems;

import com.stuflo.roamiospring.models.itineraryItems.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b from Booking b where b.itinerary.id = :itineraryId and b.itinerary.travelPlan.user.id = :userId")
    List<Booking> findAllByItineraryIdAndUserId(Long itineraryId, Long userId);
}
