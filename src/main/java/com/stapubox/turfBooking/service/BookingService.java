package com.stapubox.turfBooking.service;

import com.stapubox.turfBooking.dto.responseDTO.BookingResponse;
import com.stapubox.turfBooking.entity.Booking;
import com.stapubox.turfBooking.entity.Slot;
import com.stapubox.turfBooking.enums.BookingStatus;
import com.stapubox.turfBooking.exception.BookingSlotException;
import com.stapubox.turfBooking.repository.BookingRepository;
import com.stapubox.turfBooking.repository.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepo;

    @Transactional
    public BookingResponse book(Long slotId, String user) {

        if (bookingRepository.existsBySlotIdAndStatus(
                slotId, BookingStatus.BOOKED)) {
            throw new BookingSlotException("Already booked");
        }

        Slot slot = slotRepo.findById(slotId).orElseThrow();

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
        Booking b = bookingRepository.findById(id).orElseThrow();
        b.setStatus(BookingStatus.CANCELLED);
        Booking saved = bookingRepository.save(b);
        return new BookingResponse(
                saved.getId(),
                b.getSlot().getId(),
                saved.getStatus(),
                saved.getUserName(),
                b.getSlot().getStartTime(),
                b.getSlot().getEndTime()
        );
    }
}
