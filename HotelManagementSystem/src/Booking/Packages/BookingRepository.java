package Booking.Packages;

import Booking.Entite.Booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingRepository {
    private List<Booking> bookings = new ArrayList<>();


    public BookingRepository() {
    }

    public boolean addBooking(Booking booking){
        if(this.bookings.contains(booking)){
            return false;
        }
        this.bookings.add(booking);
        return true;
    }
    public Booking searchBooking(int bookingId){


            return this.bookings.stream().filter(b -> b.getBookingId() == bookingId).findFirst().orElse(null);

    }
    public boolean deleteBooking(int bookingId){
        Booking booking = searchBooking(bookingId);

        if (booking != null) {
            return this.bookings.remove(booking);
        }

        return false;
    }
    public boolean updateBooking(int bookingId, Booking updatedBooking){
        Booking booking = searchBooking(bookingId);
        if (booking != null) {
            booking.setRoomNumber(updatedBooking.getRoomNumber());
            booking.setCustomerId(updatedBooking.getCustomerId());
            booking.setCheckInDate(updatedBooking.getCheckInDate());
            booking.setCheckOutDate(updatedBooking.getCheckOutDate());
            return true;
        }
        return  false;
    }
    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }
}
