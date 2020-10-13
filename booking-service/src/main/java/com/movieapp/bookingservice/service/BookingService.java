package com.movieapp.bookingservice.service;

import java.util.List;

import com.movieapp.bookingservice.dto.BookingDTO;
import com.movieapp.bookingservice.entity.Booking;
import com.movieapp.bookingservice.exception.ServiceException;

public interface BookingService {

	Booking addBooking(Booking booking) throws ServiceException;

	List<BookingDTO> getAllBooking() throws ServiceException;

	BookingDTO getBookingById(int bookingId) throws ServiceException;

	void deleteBooking(int bookingId) throws ServiceException;

}
