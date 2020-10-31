package com.movieapp.bookingservice.service;

import java.util.List;

import com.movieapp.bookingservice.dto.BookingDTO;
import com.movieapp.bookingservice.entity.Booking;
import com.movieapp.bookingservice.exception.ApplicationException;

public interface BookingService {

	Booking addBooking(Booking booking) throws ApplicationException;

	List<BookingDTO> getAllBooking() throws ApplicationException;

	BookingDTO getBookingById(int bookingId) throws ApplicationException;

	void deleteBooking(int bookingId) throws ApplicationException;

}
