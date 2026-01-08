package com.stapubox.turfBooking.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingRequest {

    private Long slotId;
    private String userName;

}
