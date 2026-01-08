package com.stapubox.turfBooking.controller;

import com.stapubox.turfBooking.dto.requestDTO.BookingRequest;
import com.stapubox.turfBooking.dto.responseDTO.BookingResponse;
import com.stapubox.turfBooking.entity.Booking;
import com.stapubox.turfBooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/bookings")
    public BookingResponse book(@RequestBody BookingRequest request) {
        return bookingService.book(
                request.getSlotId(),
                request.getUserName()
        );
    }


    @PutMapping("/bookings/{id}/cancel")
    public BookingResponse cancel(@PathVariable Long id) {
        return bookingService.cancel(id);
    }
}