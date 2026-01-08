package com.stapubox.turfBooking.dto.responseDTO;

import com.stapubox.turfBooking.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingResponse {

//    public Long bookingId;
//    public Long slotId;
//    public BookingStatus status;
//    public String userName;
//    public LocalDateTime startTime;
//    public LocalDateTime endTime;
//
//    public BookingResponse(
//            Long bookingId,
//            Long slotId,
//            BookingStatus status,
//            String userName,
//            LocalDateTime startTime,
//            LocalDateTime endTime
//    ) {
//        this.bookingId = bookingId;
//        this.slotId = slotId;
//        this.status = status;
//        this.userName = userName;
//        this.startTime = startTime;
//        this.endTime = endTime;
//    }

    private Long bookingId;
    private Long slotId;
    private BookingStatus status;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;



}

