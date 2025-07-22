package com.example.movie_booking_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "showtimes")
public class Showtime {
    @Id
    private String id;
    private String movieId;
    private String theaterName;
    private LocalDateTime dateTime;
    private List<String> seatMap;

    // Constructors
    public Showtime() {}

    public Showtime(String movieId, String theaterName, LocalDateTime dateTime, List<String> seatMap) {
        this.movieId = movieId;
        this.theaterName = theaterName;
        this.dateTime = dateTime;
        this.seatMap = seatMap;
    }

    // ALL Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(List<String> seatMap) {
        this.seatMap = seatMap;
    }
}
