package com.example.movie_booking_system.repository;

import com.example.movie_booking_system.model.Showtime;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ShowtimeRepository extends MongoRepository<Showtime, String> {
    List<Showtime> findByMovieId(String movieId);
}
