package com.stapubox.turfBooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Slot {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Venue venue;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
