package com.example.movie_booking_system.repository;

import com.example.movie_booking_system.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByCustomerName(String customerName);
}
