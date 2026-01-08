package com.stapubox.turfBooking.controller;

import com.stapubox.turfBooking.dto.requestDTO.BulkSlotRequest;
import com.stapubox.turfBooking.entity.Slot;
import com.stapubox.turfBooking.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @PostMapping("/venues/{venueId}/slots")
    public ResponseEntity<List<Slot>> addSlots(
            @PathVariable Long venueId,
            @RequestBody BulkSlotRequest request
    ) {
        List<Slot> slots = slotService.addSlots(venueId, request);
        return new ResponseEntity<>(slots, HttpStatus.CREATED);
    }


    @DeleteMapping("/slots/{slotId}")
    public ResponseEntity<Map<String, String>> deleteSlot(@PathVariable Long slotId) {
        slotService.deleteSlot(slotId);
        return new ResponseEntity<>(
                Map.of("message", "Slot deleted successfully"),
                HttpStatus.OK
        );
    }
}
