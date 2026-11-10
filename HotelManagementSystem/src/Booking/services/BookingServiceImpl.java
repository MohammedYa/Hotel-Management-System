package Booking.services;

import Booking.Entite.Booking;
import Booking.Packages.BookingRepository;
import Customers.Packages.CustomerRepository;

import java.util.List;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    public BookingServiceImpl(BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }







    @Override
    public boolean addBooking(Booking booking) {
        return
    }

    @Override
    public Booking searchBooking(int bookingId) {
        return null;
    }

    @Override
    public boolean deleteBooking(int bookingId) {
        return bookingRepository.deleteBooking(bookingId);
    }

    @Override
    public boolean updateBooking(int bookingId, Booking updatedBooking) {
        return false;
    }

    @Override
    public List<Booking> getBookings() {
        return List.of();
    }
}
