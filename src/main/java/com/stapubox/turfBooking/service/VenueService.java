package com.stapubox.turfBooking.service;

import com.stapubox.turfBooking.entity.Slot;
import com.stapubox.turfBooking.entity.Venue;
import com.stapubox.turfBooking.exception.DuplicateVenueException;
import com.stapubox.turfBooking.repository.SlotRepository;
import com.stapubox.turfBooking.repository.VenueRepository;
import com.stapubox.turfBooking.dto.responseDTO.AvailableVenueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {
    private final VenueRepository venuerepository;
    private final SlotRepository slotRepository;
    public Venue create(Venue v) {
        boolean alreadyExists =
                venuerepository.existsByNameAndLocation(
                        v.getName(),
                        v.getLocation()
                );

        if (alreadyExists) {
            throw new DuplicateVenueException("Venue already present");
        }

        return venuerepository.save(v);
    }

    public List<Venue> getAll() {
        return venuerepository.findAll();
    }

    public List<AvailableVenueResponse> getAvailableVenues(
            Long sportId,
            LocalDateTime start,
            LocalDateTime end
    ) {
        List<Slot> slots =
                slotRepository.findAvailableSlots(sportId, start, end);

        return slots.stream()
                .map(s -> new AvailableVenueResponse(
                        s.getVenue().getId(),
                        s.getVenue().getName(),
                        s.getId(),
                        s.getStartTime(),
                        s.getEndTime()
                ))
                .toList();
    }
}

