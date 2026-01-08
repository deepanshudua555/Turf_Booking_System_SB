package com.stapubox.turfBooking.repository;

import com.stapubox.turfBooking.entity.Booking;
import com.stapubox.turfBooking.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsBySlotIdAndStatus(Long slotId, BookingStatus status);
}

