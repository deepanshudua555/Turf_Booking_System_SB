package com.stapubox.turfBooking.repository;

import com.stapubox.turfBooking.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    @Query("""
      select s from Slot s 
      where s.venue.id = :venueId 
      and s.startTime < :end 
      and s.endTime > :start
    """)
    List<Slot> findOverlappingSlots(Long venueId, LocalDateTime start, LocalDateTime end);

    @Query("""
        select s from Slot s
        where s.id not in (
            select b.slot.id from Booking b where b.status = 'BOOKED'
        )
    """)
    List<Slot> findAvailableSlots(
    );
}

