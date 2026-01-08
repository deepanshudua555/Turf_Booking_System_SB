package com.stapubox.turfBooking.dto.requestDTO;

import java.time.LocalDate;
import java.util.List;

public class BulkSlotRequest {

    private LocalDate date;
    private List<SlotRequest> slots;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<SlotRequest> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotRequest> slots) {
        this.slots = slots;
    }
}
