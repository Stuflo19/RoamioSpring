package com.stuflo.roamiospring.services.itineraryItems;

import com.stuflo.roamiospring.dtos.itineraryItems.FlightDto;
import com.stuflo.roamiospring.models.itineraryItems.Flight;
import com.stuflo.roamiospring.repositories.ItineraryItems.FlightRepository;
import com.stuflo.roamiospring.responses.itineraryItems.FlightResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<FlightResponse> getFlights(Long itineraryId) {
        List<Flight> flights = flightRepository.findAllByItineraryId(itineraryId);

        return flights.stream().map(FlightResponse::new).toList();
    }

    public FlightResponse createFlight(FlightDto flightDto, Long itineraryId) {
        Flight newFlight = flightRepository.save(new Flight(flightDto, itineraryId));

        return new FlightResponse(newFlight);
    }
}
