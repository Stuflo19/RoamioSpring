package com.stuflo.roamiospring.services;

import com.stuflo.roamiospring.dtos.ItineraryDto;
import com.stuflo.roamiospring.models.Itinerary;
import com.stuflo.roamiospring.repositories.ItineraryRepository;
import com.stuflo.roamiospring.responses.ItineraryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;

    public ItineraryService(ItineraryRepository itineraryRepository) {
        this.itineraryRepository = itineraryRepository;
    }

    public List<ItineraryResponse> getItineraries(Long planId, Long userId) {
        List<Itinerary> itineraries = itineraryRepository.findAllByTravelPlanIdAndUserId(planId, userId);

        return itineraries.stream().map(ItineraryResponse::new).toList();
    }

    public ItineraryResponse createItinerary(ItineraryDto itineraryDto, Long planId) {
        Itinerary itinerary = itineraryRepository.save(new Itinerary(itineraryDto, planId));

        return new ItineraryResponse(itinerary);
    }
}
