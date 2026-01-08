package com.stapubox.turfBooking.controller;
import com.stapubox.turfBooking.dto.responseDTO.AvailableVenueResponse;
import com.stapubox.turfBooking.entity.Venue;
import com.stapubox.turfBooking.exception.DuplicateVenueException;
import com.stapubox.turfBooking.service.VenueService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VenueControllerTest {

    @Mock
    private VenueService venueService;

//    @InjectMocks
    private VenueController venueController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        venueController = new VenueController(venueService);
    }

    @Test
    public void createVenue_success_shouldReturn201() {
        // Arrange
        Venue venue = new Venue();
        venue.setName("ABC Turf");
        venue.setLocation("Mumbai");

        when(venueService.create(any(Venue.class)))
                .thenReturn(venue);

        // Act
        ResponseEntity<Venue> response =
                venueController.create(venue);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("ABC Turf", response.getBody().getName());
        assertEquals("Mumbai", response.getBody().getLocation());

        verify(venueService).create(venue);
    }

    @Test(expected = DuplicateVenueException.class)
    public void createVenue_duplicate_shouldThrowException() {
        // Arrange
        Venue venue = new Venue();
        venue.setName("ABC Turf");
        venue.setLocation("Mumbai");

        when(venueService.create(any(Venue.class)))
                .thenThrow(new DuplicateVenueException("Venue already present"));

        venueController.create(venue);
    }


    @Test
    public void listVenues_success_shouldReturn200() {
        // Arrange
        Venue v1 = new Venue();
        v1.setName("ABC Turf");

        Venue v2 = new Venue();
        v2.setName("XYZ Turf");

        when(venueService.getAll())
                .thenReturn(List.of(v1, v2));

        // Act
        ResponseEntity<List<Venue>> response =
                venueController.list();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("ABC Turf", response.getBody().get(0).getName());
        assertEquals("XYZ Turf", response.getBody().get(1).getName());

        verify(venueService).getAll();
    }

    @Test
    public void listVenues_empty_shouldReturn200WithEmptyList() {
        // Arrange
        when(venueService.getAll())
                .thenReturn(List.of());

        // Act
        ResponseEntity<List<Venue>> response =
                venueController.list();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(venueService).getAll();
    }

    @Test
    public void availableVenues_success_shouldReturn200() {
        // Arrange
        AvailableVenueResponse dto =
                new AvailableVenueResponse(
                        1L,
                        "ABC Turf",
                        10L,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(1)
                );

        when(venueService.getAvailableVenues())
                .thenReturn(List.of(dto));

        // Act
        ResponseEntity<List<AvailableVenueResponse>> response =
                venueController.availableVenues();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("ABC Turf",
                response.getBody().get(0).venueName);
        assertEquals(Long.valueOf(1L),
                response.getBody().get(0).venueId);

        verify(venueService).getAvailableVenues();
    }

    @Test
    public void availableVenues_empty_shouldReturn200() {
        // Arrange
        when(venueService.getAvailableVenues())
                .thenReturn(List.of());

        // Act
        ResponseEntity<List<AvailableVenueResponse>> response =
                venueController.availableVenues();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(venueService).getAvailableVenues();
    }


}