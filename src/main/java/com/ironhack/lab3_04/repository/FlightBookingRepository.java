package com.ironhack.lab3_04.repository;

import com.ironhack.lab3_04.model.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, String> {
}
