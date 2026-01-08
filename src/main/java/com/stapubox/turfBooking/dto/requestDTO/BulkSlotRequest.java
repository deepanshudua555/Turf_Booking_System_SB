package com.stapubox.turfBooking.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class BulkSlotRequest {

    private LocalDate date;
    private List<SlotRequest> slots;


}
