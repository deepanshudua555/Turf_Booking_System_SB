package com.stapubox.turfBooking.service;


import com.stapubox.turfBooking.dto.responseDTO.AvailableVenueResponse;
import com.stapubox.turfBooking.entity.Slot;
import com.stapubox.turfBooking.entity.Venue;
import com.stapubox.turfBooking.exception.DuplicateVenueException;
import com.stapubox.turfBooking.repository.SlotRepository;
import com.stapubox.turfBooking.repository.VenueRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VenueServiceTest {

    @Mock
    private VenueRepository venueRepository;

    @Mock
    private SlotRepository slotRepository;

    @InjectMocks
    private VenueService venueService;

    @Before
    public void setup() {
        // MockitoJUnitRunner handles mock initialization
    }

    // =========================
    // CREATE VENUE TESTS
    // =========================

    @Test
    public void createVenue_success() {
        // Arrange
        Venue venue = new Venue();
        venue.setName("ABC Turf");
        venue.setLocation("Mumbai");

        when(venueRepository.existsByNameAndLocation("ABC Turf", "Mumbai"))
                .thenReturn(false);
        when(venueRepository.save(venue))
                .thenReturn(venue);

        // Act
        Venue saved = venueService.create(venue);

        // Assert
        assertNotNull(saved);
        assertEquals("ABC Turf", saved.getName());
        assertEquals("Mumbai", saved.getLocation());

        verify(venueRepository).existsByNameAndLocation("ABC Turf", "Mumbai");
        verify(venueRepository).save(venue);
    }

    @Test(expected = DuplicateVenueException.class)
    public void createVenue_duplicate_shouldThrowException() {
        // Arrange
        Venue venue = new Venue();
        venue.setName("ABC Turf");
        venue.setLocation("Mumbai");

        when(venueRepository.existsByNameAndLocation("ABC Turf", "Mumbai"))
                .thenReturn(true);

        // Act
        venueService.create(venue);

        // Assert handled by expected exception
    }

    // =========================
    // GET ALL VENUES TESTS
    // =========================

    @Test
    public void getAllVenues_success() {
        // Arrange
        Venue v1 = new Venue();
        v1.setName("ABC Turf");

        Venue v2 = new Venue();
        v2.setName("XYZ Turf");

        when(venueRepository.findAll())
                .thenReturn(List.of(v1, v2));

        // Act
        List<Venue> result = venueService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("ABC Turf", result.get(0).getName());
        assertEquals("XYZ Turf", result.get(1).getName());

        verify(venueRepository).findAll();
    }

    @Test
    public void getAllVenues_empty() {
        // Arrange
        when(venueRepository.findAll())
                .thenReturn(List.of());

        // Act
        List<Venue> result = venueService.getAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(venueRepository).findAll();
    }

    // =========================
    // GET AVAILABLE VENUES TESTS
    // =========================

    @Test
    public void getAvailableVenues_success() {
        // Arrange
        Venue venue = new Venue();
        venue.setId(1L);
        venue.setName("ABC Turf");

        Slot slot = new Slot();
        slot.setId(10L);
        slot.setVenue(venue);
        slot.setStartTime(LocalDateTime.now());
        slot.setEndTime(LocalDateTime.now().plusHours(1));

        when(slotRepository.findAvailableSlots())
                .thenReturn(List.of(slot));

        // Act
        List<AvailableVenueResponse> result =
                venueService.getAvailableVenues();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        AvailableVenueResponse dto = result.get(0);
        assertEquals(Long.valueOf(1L), dto.venueId);
        assertEquals("ABC Turf", dto.venueName);
        assertEquals(Long.valueOf(10L), dto.slotId);

        verify(slotRepository).findAvailableSlots();
    }

    @Test
    public void getAvailableVenues_empty() {
        // Arrange
        when(slotRepository.findAvailableSlots())
                .thenReturn(List.of());

        // Act
        List<AvailableVenueResponse> result =
                venueService.getAvailableVenues();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(slotRepository).findAvailableSlots();
    }
}

