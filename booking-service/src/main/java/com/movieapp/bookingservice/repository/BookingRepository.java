package com.movieapp.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieapp.bookingservice.entity.Booking;


public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
