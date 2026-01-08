package com.stapubox.turfBooking.controller;

import com.stapubox.turfBooking.dto.requestDTO.BookingRequest;
import com.stapubox.turfBooking.dto.responseDTO.BookingResponse;
import com.stapubox.turfBooking.entity.Booking;
import com.stapubox.turfBooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> book(
            @RequestBody BookingRequest request
    ) {
        BookingResponse response =
                bookingService.book(request.getSlotId(), request.getUserName());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/bookings/{id}/cancel")
    public ResponseEntity<BookingResponse> cancel(
            @PathVariable Long id
    ) {
        BookingResponse response = bookingService.cancel(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

}