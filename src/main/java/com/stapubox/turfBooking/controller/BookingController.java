package com.stapubox.turfBooking.controller;

import com.stapubox.turfBooking.entity.Booking;
import com.stapubox.turfBooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;

    @PostMapping("/bookings")
    public Booking book(@RequestParam Long slotId,
                        @RequestParam String userName) {
        return service.book(slotId, userName);
    }

    @PutMapping("/bookings/{id}/cancel")
    public Booking cancel(@PathVariable Long id) {
        return service.cancel(id);
    }
}
