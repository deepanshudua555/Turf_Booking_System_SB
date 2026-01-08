package com.stapubox.turfBooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Slot slot;

    private String userName;

    private String status; // BOOKED / CANCELLED

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
