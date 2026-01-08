package com.stapubox.turfBooking.dto.responseDTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AvailableVenueResponse {

    public Long venueId;
    public String venueName;
    public Long slotId;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

}
