package com.stapubox.turfBooking.service;

import com.stapubox.turfBooking.dto.responseDTO.BookingResponse;
import com.stapubox.turfBooking.entity.Booking;
import com.stapubox.turfBooking.entity.Slot;
import com.stapubox.turfBooking.enums.BookingStatus;
import com.stapubox.turfBooking.exception.BookingAlreadyCancelledException;
import com.stapubox.turfBooking.exception.BookingSlotException;
import com.stapubox.turfBooking.exception.ResourceNotFoundException;
import com.stapubox.turfBooking.repository.BookingRepository;
import com.stapubox.turfBooking.repository.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepo;

    @Transactional
    public BookingResponse book(Long slotId, String user) {

        Slot slot = slotRepo.findById(slotId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Slot not found"));


        if (bookingRepository.existsBySlotIdAndStatus(
                slotId, BookingStatus.BOOKED)) {
            throw new BookingSlotException("Slot already booked");
        }
//        Slot slot = slotRepo.findById(slotId).orElseThrow();

        Booking b = new Booking();
        b.setSlot(slot);
        b.setUserName(user);
        b.setStatus(BookingStatus.BOOKED);

        Booking saved = bookingRepository.save(b);

        return new BookingResponse(
                saved.getId(),
                slot.getId(),
                saved.getStatus(),
                saved.getUserName(),
                slot.getStartTime(),
                slot.getEndTime()
        );
    }


    public BookingResponse cancel(Long id) {
//        Booking b = bookingRepository.findById(id).orElseThrow();
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found"));


        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingAlreadyCancelledException(
                    "Booking already cancelled");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        Booking saved = bookingRepository.save(booking);
        return new BookingResponse(
                saved.getId(),
                booking.getSlot().getId(),
                saved.getStatus(),
                saved.getUserName(),
                booking.getSlot().getStartTime(),
                booking.getSlot().getEndTime()
        );
    }

    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(b -> new BookingResponse(
                        b.getId(),
                        b.getSlot().getId(),
                        b.getStatus(),
                        b.getUserName(),
                        b.getSlot().getStartTime(),
                        b.getSlot().getEndTime()
                ))
                .toList();
    }

}
