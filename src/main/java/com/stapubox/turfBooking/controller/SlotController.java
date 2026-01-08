package com.stapubox.turfBooking.controller;

import com.stapubox.turfBooking.dto.requestDTO.BulkSlotRequest;
import com.stapubox.turfBooking.entity.Slot;
import com.stapubox.turfBooking.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @PostMapping("/venues/{venueId}/slots")
    public List<Slot> addSlots(
            @PathVariable Long venueId,
            @RequestBody BulkSlotRequest request
    ) {
        return slotService.addSlots(venueId, request);
    }
}
