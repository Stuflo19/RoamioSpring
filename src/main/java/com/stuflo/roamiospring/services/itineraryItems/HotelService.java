package com.stuflo.roamiospring.services.itineraryItems;

import com.stuflo.roamiospring.dtos.itineraryItems.HotelDto;
import com.stuflo.roamiospring.models.itineraryItems.Hotel;
import com.stuflo.roamiospring.repositories.ItineraryItems.HotelRepository;
import com.stuflo.roamiospring.responses.itineraryItems.HotelResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelResponse> getHotels(Long itineraryId, Long userId) {
        List<Hotel> hotels = hotelRepository.findAllByItineraryIdAndUserId(itineraryId, userId);

        return hotels.stream().map(HotelResponse::new).toList();
    }

    public HotelResponse createHotel(HotelDto hotelDto, Long itineraryId) {
        Hotel hotel = hotelRepository.save(new Hotel(hotelDto, itineraryId));

        return new HotelResponse(hotel);
    }
}
