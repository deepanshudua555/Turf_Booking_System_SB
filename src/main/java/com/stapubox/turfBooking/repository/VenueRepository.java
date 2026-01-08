package com.stapubox.turfBooking.repository;

import com.stapubox.turfBooking.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    boolean existsByNameAndLocation(
            String name,
            String location
    );
}
