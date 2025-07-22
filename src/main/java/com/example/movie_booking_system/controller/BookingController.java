package com.example.movie_booking_system.controller;

import com.example.movie_booking_system.model.Booking;
import com.example.movie_booking_system.repository.BookingRepository;
import com.example.movie_booking_system.repository.ShowtimeRepository;
import com.example.movie_booking_system.model.Showtime;
import com.example.movie_booking_system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private MovieService movieService;

    // Book tickets for a showtime
    @PostMapping
    public ResponseEntity<Booking> bookTickets(@RequestBody Booking booking) {
        // Check if showtime exists
        Optional<Showtime> showtimeOpt = showtimeRepository.findById(booking.getShowtimeId());
        if (showtimeOpt.isEmpty()) {
            booking.setPaymentStatus("Failed - Showtime not found");
            return ResponseEntity.badRequest().body(booking);
        }

        Showtime showtime = showtimeOpt.get();
        List<String> availableSeats = showtime.getSeatMap();

        // Check seat availability
        if (!availableSeats.containsAll(booking.getSeatsBooked())) {
            booking.setPaymentStatus("Failed - Seats unavailable");
            return ResponseEntity.badRequest().body(booking);
        }

        // Remove booked seats from showtime's seatMap
        availableSeats.removeAll(booking.getSeatsBooked());
        showtime.setSeatMap(availableSeats);
        showtimeRepository.save(showtime);

        booking.setPaymentStatus("Success");
        Booking savedBooking = bookingRepository.save(booking);

        // Add trending functionality - increment booking count for the movie
        if (booking.getPaymentStatus().equals("Success")) {
            movieService.incrementBookingCount(showtime.getMovieId());
        }

        return ResponseEntity.ok(savedBooking);
    }

    // Get all bookings (for debugging)
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get user bookings (optional)
    @GetMapping("/user/{name}")
    public List<Booking> getBookingsByUser(@PathVariable String name) {
        return bookingRepository.findByCustomerName(name);
    }
}
