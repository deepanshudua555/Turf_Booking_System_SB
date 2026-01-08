package com.stapubox.turfBooking.exception;

import com.stapubox.turfBooking.dto.responseDTO.OverlappingSlotDto;
import lombok.Getter;

import java.util.Set;

@Getter
public class SlotOverlapException extends RuntimeException {

    private final Set<OverlappingSlotDto> overlappingSlots;

    public SlotOverlapException(Set<OverlappingSlotDto> overlappingSlots) {
        super("Slot overlaps");
        this.overlappingSlots = overlappingSlots;
    }

}
