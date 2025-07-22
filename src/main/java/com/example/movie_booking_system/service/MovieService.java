package com.example.movie_booking_system.service;

import com.example.movie_booking_system.model.Movie;
import com.example.movie_booking_system.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> searchMovies(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void removeMovie(String id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getTrendingMovies() {
        return movieRepository.findAll(Sort.by(Sort.Direction.DESC, "bookingCount"));
    }

    public List<Movie> getRecommendedMovies() {
        return movieRepository.findAll().stream().limit(3).toList();
    }

    public void incrementBookingCount(String movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            movie.setBookingCount(movie.getBookingCount() + 1);
            movieRepository.save(movie);
        }
    }
}
