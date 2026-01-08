package com.stapubox.turfBooking.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class SlotRequest {

    private LocalTime startTime;
    private LocalTime endTime;

}
