package com.stapubox.turfBooking.service;

import com.stapubox.turfBooking.entity.Booking;
import com.stapubox.turfBooking.entity.Slot;
import com.stapubox.turfBooking.repository.BookingRepository;
import com.stapubox.turfBooking.repository.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository repo;
    private final SlotRepository slotRepo;

    @Transactional
    public Booking book(Long slotId, String user) {
        if (repo.existsBySlotIdAndStatus(slotId, "BOOKED")) {
            throw new RuntimeException("Already booked");
        }

        Slot slot = slotRepo.findById(slotId).orElseThrow();

        Booking b = new Booking();
        b.setSlot(slot);
        b.setUserName(user);
        b.setStatus("BOOKED");
        return repo.save(b);
    }

    public Booking cancel(Long id) {
        Booking b = repo.findById(id).orElseThrow();
        b.setStatus("CANCELLED");
        return repo.save(b);
    }
}
