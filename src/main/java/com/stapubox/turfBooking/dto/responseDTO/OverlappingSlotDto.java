package com.stapubox.turfBooking.dto.responseDTO;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class OverlappingSlotDto {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public OverlappingSlotDto(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OverlappingSlotDto)) return false;
        OverlappingSlotDto that = (OverlappingSlotDto) o;
        return startTime.equals(that.startTime)
                && endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

}
