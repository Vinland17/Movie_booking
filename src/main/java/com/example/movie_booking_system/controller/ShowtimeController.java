package com.example.movie_booking_system.controller;

import com.example.movie_booking_system.model.Showtime;
import com.example.movie_booking_system.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
@CrossOrigin(origins = "http://localhost:3000")
public class ShowtimeController {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    // Get showtimes for a given movie
    @GetMapping("/movie/{movieId}")
    public List<Showtime> getShowtimesByMovie(@PathVariable String movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }

    // Add showtime (Admin feature)
    @PostMapping("/admin")
    public Showtime addShowtime(@RequestBody Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    // Get seat map for a showtime
    @GetMapping("/{id}/seats")
    public List<String> getSeats(@PathVariable String id) {
        return showtimeRepository.findById(id)
                .map(Showtime::getSeatMap)
                .orElse(null);
    }
}
