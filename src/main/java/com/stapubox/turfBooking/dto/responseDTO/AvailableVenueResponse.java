package com.stapubox.turfBooking.dto.responseDTO;


import java.time.LocalDateTime;

public class AvailableVenueResponse {

    public Long venueId;
    public String venueName;
    public Long slotId;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public AvailableVenueResponse(
            Long venueId,
            String venueName,
            Long slotId,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
