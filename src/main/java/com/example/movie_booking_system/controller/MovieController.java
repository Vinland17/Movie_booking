package com.example.movie_booking_system.controller;

import com.example.movie_booking_system.model.Movie;
import com.example.movie_booking_system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return movieService.searchMovies(search);
        } else {
            return movieService.getAllMovies();
        }
    }

    @GetMapping("/trending")
    public List<Movie> getTrendingMovies() {
        return movieService.getTrendingMovies();
    }

    @GetMapping("/recommended")
    public List<Movie> getRecommendedMovies() {
        return movieService.getRecommendedMovies();
    }

    @PostMapping("/admin")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteMovie(@PathVariable String id) {
        movieService.removeMovie(id);
    }
}
