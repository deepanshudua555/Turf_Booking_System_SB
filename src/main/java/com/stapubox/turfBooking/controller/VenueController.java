package com.stapubox.turfBooking.controller;

import com.stapubox.turfBooking.entity.Venue;
import com.stapubox.turfBooking.dto.responseDTO.AvailableVenueResponse;
import com.stapubox.turfBooking.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    @PostMapping
    public Venue create(@RequestBody Venue v) {
        return venueService.create(v);
    }



    @GetMapping
    public List<Venue> list() {
        return venueService.getAll();
    }

    @GetMapping("/available")
    public List<AvailableVenueResponse> availableVenues(

    ) {
        return venueService.getAvailableVenues();
    }

}
