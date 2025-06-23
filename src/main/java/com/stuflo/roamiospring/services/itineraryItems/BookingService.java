package com.stuflo.roamiospring.services.itineraryItems;

import com.stuflo.roamiospring.dtos.itineraryItems.BookingDto;
import com.stuflo.roamiospring.models.itineraryItems.Booking;
import com.stuflo.roamiospring.repositories.ItineraryItems.BookingRepository;
import com.stuflo.roamiospring.responses.itineraryItems.BookingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    BookingRepository bookingRepository;

    BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingResponse> getBookings(Long itineraryId, Long userId) {
        List<Booking> bookings = bookingRepository.findAllByItineraryIdAndUserId(itineraryId, userId);

        return bookings.stream().map(BookingResponse::new).toList();
    }

    public BookingResponse createBooking(BookingDto bookingDto, Long itineraryId) {
        Booking booking = bookingRepository.save(new Booking(bookingDto, itineraryId));

        return new BookingResponse(booking);
    }
}
