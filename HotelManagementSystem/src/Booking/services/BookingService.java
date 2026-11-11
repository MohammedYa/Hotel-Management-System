package Booking.services;

import Booking.Entite.Booking;

import java.util.Collections;
import java.util.List;

public interface BookingService {
    public boolean addBooking(Booking booking);
    public Booking searchBooking(int bookingId);
    public boolean cancelBooking(int bookingId);
    public boolean updateBooking(int bookingId, Booking updatedBooking);
    public List<Booking> getBookings() ;
    boolean cancelBookingsByCustomerId(int customerId);
    public boolean cancelBookingsByRoomNumber(int roomNumber) ;

    }
