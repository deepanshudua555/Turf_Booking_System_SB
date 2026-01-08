package com.stapubox.turfBooking.controller;

import com.stapubox.turfBooking.entity.Venue;
import com.stapubox.turfBooking.dto.responseDTO.AvailableVenueResponse;
import com.stapubox.turfBooking.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<Venue> create(@RequestBody Venue v) {
        Venue saved = venueService.create(v);
        return new ResponseEntity<>(saved, HttpStatus.CREATED); // 201
    }



    @GetMapping
    public ResponseEntity<List<Venue>> list() {
        return ResponseEntity.ok(venueService.getAll()); // 200
    }

    @GetMapping("/available")
    public ResponseEntity<List<AvailableVenueResponse>> availableVenues() {
        List<AvailableVenueResponse> result =
                venueService.getAvailableVenues();

        return ResponseEntity.ok(result); // 200
    }



}
