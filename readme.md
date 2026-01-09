# Turf Booking System (Spring Boot + MySQL + Docker)

A Spring Boot based Turf Booking System that allows users to:
- Create venues
- Add slots to venues
- Book and cancel slots
- View available venues
- Test APIs using Swagger
- Run the complete system using Docker


--------------------------------------------------
TECH STACK
--------------------------------------------------
Java              : 21
Spring Boot       : 4.0.1

Spring Data JPA

MySQL             : 8

Swagger

Docker & Docker Compose

JUnit 5, Mockito

--------------------------------------------------
PROJECT STRUCTURE
--------------------------------------------------
```text
turfBooking/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── README.md
└── src
    └── main
        └── java
            └── com
                └── stapubox
                    └── turfBooking
                        ├── TurfBookingApplication.java
                        ├── controller
                        │   ├── HealthController.java
                        │   ├── VenueController.java
                        │   ├── SlotController.java
                        │   └── BookingController.java
                        ├── service
                        │   ├── VenueService.java
                        │   ├── SlotService.java
                        │   └── BookingService.java
                        ├── repository
                        │   ├── VenueRepository.java
                        │   ├── SlotRepository.java
                        │   └── BookingRepository.java
                        ├── exception
                        │   ├── BookingAlreadyCancelledException.java
                        │   ├── BookingSlotException.java
                        │   ├── DuplicateVenueException.java
                        │   ├── GlobalExceptionHandler.java
                        │   ├── InvalidSlotTimeException.java
                        │   ├── ResourceNotFoundException.java
                        │   └── SlotOverlapException.java
                        ├── dto
                        │   ├── requestDTO
                        │   │   ├── BookingRequest.java
                        │   │   ├── BulkSlotRequest.java
                        │   │   └── SlotRequest.java
                        │   └── responseDTO
                        │       ├── AvailableVenueResponse.java
                        │       ├── BookingResponse.java
                        │       └── OverlappingSlotDto.java
                        ├── enums
                        │   └── BookingStatus.java
                        └── entity
                            ├── Venue.java
                            ├── Slot.java
                            └── Booking.java
```
--------------------------------------------------
PREREQUISITES
--------------------------------------------------
Make sure the following are installed:

- Java 21
- Maven
- Docker Desktop
- Git

Check Java version:
java -version


--------------------------------------------------
RUN APPLICATION USING DOCKER
--------------------------------------------------

STEP 1: CLONE PROJECT
---------------------
git clone https://github.com/deepanshudua555/Turf_Booking_System_SB.git

cd turfBooking


STEP 2: REQUIRED FILES
---------------------
Ensure these files exist in project root:
- Dockerfile
- docker-compose.yml
- pom.xml
- README.txt


STEP 3: DATABASE CONFIGURATION
------------------------------
Dummy credentials are used:

Database Name : stapubox
Username      : root
Password      : root

These are defined inside docker-compose.yml.


STEP 4: STOP OLD CONTAINERS (IMPORTANT)
--------------------------------------
docker-compose down -v

This removes old MySQL volumes and avoids password conflicts.


STEP 5: BUILD & START APPLICATION
---------------------------------
docker-compose up --build

Wait 15–20 seconds for MySQL and Spring Boot to start.

Successful logs:
- Tomcat started on port 8080
- Started TurfBookingApplication


STEP 6: VERIFY APPLICATION
--------------------------
Health Check API:
GET http://localhost:8080/

Response:
Server is running


--------------------------------------------------
SWAGGER API DOCUMENTATION
--------------------------------------------------
Swagger UI is enabled.

Open in browser:
http://localhost:8080/swagger-ui/index.html

You can test:
- Venue APIs
- Slot APIs
- Booking APIs


--------------------------------------------------
API ENDPOINTS
--------------------------------------------------

VENUE APIs
----------
POST   /venues
GET    /venues
GET    /venues/available


SLOT APIs
---------
POST    /venues/{venueId}/slots
DELETE  /slots/{id}


BOOKING APIs
------------
POST    /bookings
PUT     /bookings/{id}/cancel
GET     /bookings


--------------------------------------------------
SAMPLE REQUEST PAYLOADS
--------------------------------------------------

CREATE VENUE
------------
```{
"name": "Rohini Turf ",
"location": "Rohini",
"sportId": 7020112    -- from the public API
}
```

ADD SLOTS (24-HOUR FORMAT)
-------------------------
```{
"date": "2026-01-10",
"slots": [
{ "startTime": "06:00:00", "endTime": "07:00:00" },
{ "startTime": "08:00:00", "endTime": "09:00:00" }
]
}
```
BOOK SLOT
---------
```{
"slotId": 102,
"userName": "Deepanshu"
}
```




--------------------------------------------------
ERROR HANDLING
--------------------------------------------------
Centralized exception handling using @RestControllerAdvice.

Scenario                     HTTP STATUS
------------------------------------------------
Slot already booked           409 CONFLICT
Booking already cancelled     409 CONFLICT
Booking not found             404 NOT FOUND
Slot overlap                  409 CONFLICT
Invalid slot time             400 BAD REQUEST


--------------------------------------------------
RUN UNIT TESTS
--------------------------------------------------
mvn test

Uses:
- JUnit 5
- Mockito


--------------------------------------------------
STOP APPLICATION
--------------------------------------------------
docker-compose down

Clean restart:
docker-compose down -v


--------------------------------------------------
CREDENTIALS NOTE
--------------------------------------------------
Dummy credentials are used.
No real passwords exposed.
Evaluator can run project directly using Docker.


--------------------------------------------------
AUTHOR
--------------------------------------------------
Deepanshu Dua
---
Turf Booking System – Backend Assignment
Java 21 | Spring Boot 4.0.1

--------------------------------------------------
