package com.stuflo.roamiospring.repositories.ItineraryItems;

import com.stuflo.roamiospring.models.itineraryItems.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findAllByItineraryId(Long itineraryId);
}
