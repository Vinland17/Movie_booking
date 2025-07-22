package com.example.movie_booking_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    private String showtimeId;
    private String customerName;
    private List<String> seatsBooked;
    private String paymentStatus;

    // Constructors
    public Booking() {}

    public Booking(String showtimeId, String customerName, List<String> seatsBooked, String paymentStatus) {
        this.showtimeId = showtimeId;
        this.customerName = customerName;
        this.seatsBooked = seatsBooked;
        this.paymentStatus = paymentStatus;
    }

    // ALL Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(List<String> seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
