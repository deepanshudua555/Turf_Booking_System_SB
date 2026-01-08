package com.stapubox.turfBooking.service;

import com.stapubox.turfBooking.dto.requestDTO.BulkSlotRequest;
import com.stapubox.turfBooking.dto.responseDTO.OverlappingSlotDto;
import com.stapubox.turfBooking.dto.requestDTO.SlotRequest;
import com.stapubox.turfBooking.entity.Slot;
import com.stapubox.turfBooking.entity.Venue;
import com.stapubox.turfBooking.exception.InvalidSlotTimeException;
import com.stapubox.turfBooking.exception.SlotOverlapException;
import com.stapubox.turfBooking.repository.SlotRepository;
import com.stapubox.turfBooking.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final SlotRepository slotRepository;
    private final VenueRepository venueRepository;

    @Transactional
    public List<Slot> addSlots(Long venueId, BulkSlotRequest request) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        List<Slot> savedSlots = new ArrayList<>();
        List<LocalDateTime[]> requestSlots = new ArrayList<>();
        Set<OverlappingSlotDto> allOverlaps = new HashSet<>();

        for (SlotRequest sr : request.getSlots()) {

            LocalDateTime start =
                    LocalDateTime.of(request.getDate(), sr.getStartTime());
            LocalDateTime end =
                    LocalDateTime.of(request.getDate(), sr.getEndTime());

            //INVALID TIME CHECK
            if (!end.isAfter(start)) {
                throw new InvalidSlotTimeException( start+" - "+ end+" "+
                        "End time must be after start time"
                );
            }

            long minutes = Duration.between(start, end).toMinutes();
            if (minutes != 60) {
                throw new InvalidSlotTimeException(start+" - "+ end+" "+ "Slot duration must be equal to 60 minutes");
            }

//            {
//                "date": "2026-01-12",
//                    "slots": [
//                { "startTime": "15:00:00", "endTime": "16:00:00" },
//                { "startTime": "15:30:00", "endTime": "17:00:00" }
//
//              ]
//            }
            //same-request overlap check
            for (LocalDateTime[] existing : requestSlots) {
                if (start.isBefore(existing[1]) && end.isAfter(existing[0])) {
                    allOverlaps.add(
                            new OverlappingSlotDto(existing[0], existing[1])
                    );
                    allOverlaps.add(
                            new OverlappingSlotDto(start, end)
                    );

                }
            }

            //DB overlap check
            List<Slot> dbOverlaps =
                    slotRepository.findOverlappingSlots(venueId, start, end);

            for (Slot s : dbOverlaps) {
                allOverlaps.add(
                        new OverlappingSlotDto(
                                s.getStartTime(),
                                s.getEndTime()
                        )
                );
            }

            // store slot timings for next comparisons
            requestSlots.add(new LocalDateTime[]{start, end});

            // save slot temporarily
            Slot slot = new Slot();
            slot.setVenue(venue);
            slot.setStartTime(start);
            slot.setEndTime(end);
            savedSlots.add(slot);
        }

        //AFTER LOOP — throw if ANY overlap found
        if (!allOverlaps.isEmpty()) {
            throw new SlotOverlapException(allOverlaps);
        }

        //save only when NO overlap
        return slotRepository.saveAll(savedSlots);
    }
}
