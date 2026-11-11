package Booking.services;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Booking.Packages.BookingRepository;
import Customers.Entite.Customer;
import Customers.Packages.CustomerRepository;
import Rooms.entite.Room;
import Rooms.pakages.RoomRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private  final RoomRepository roomRepository;
    public BookingServiceImpl(BookingRepository bookingRepository, CustomerRepository customerRepository, RoomRepository roomRepository) {
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }







    @Override
    public boolean addBooking(Booking booking) {
        // 1. Check Customer
        boolean existCustomer =
                customerRepository.searchCustomer(booking.getCustomerId()) != null;

        // 2. Check Room
        boolean existRoom =
                roomRepository.searchRoom(booking.getRoomNumber()) != null;

        if (!existCustomer || !existRoom) {
            return false;
        }

        // 3. Validate Dates
        if (!booking.getCheckOutDate().isAfter(booking.getCheckInDate())) {
            return false;
        }

        // 4. Check Booking Conflicts
        boolean hasConflict = bookingRepository.getBookings().stream()
                .filter(oldBooking ->
                        oldBooking.getRoomNumber() == booking.getRoomNumber())
                .filter(oldBooking ->
                        oldBooking.getBookingStatus() != BookingStatus.CANCELLED)
                .anyMatch(oldBooking ->
                        booking.getCheckInDate().isBefore(oldBooking.getCheckOutDate())
                                &&
                                booking.getCheckOutDate().isAfter(oldBooking.getCheckInDate())
                );

        if (hasConflict) {
            return false;
        }
        // 5. Not repeat Id
        if (bookingRepository.searchBooking(booking.getBookingId()) != null) {
            return false;
        }
        // 6. Save Booking
        return bookingRepository.addBooking(booking);
    }

    @Override
    public Booking searchBooking(int bookingId) {
        return bookingRepository.getBookings().stream().
                    filter(booking -> booking.getBookingId() == bookingId).findFirst().orElse(null);
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        Booking booking = searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {
            return false;
        }

        booking.setBookingStatus(BookingStatus.CANCELLED);
        return true;
    }
    @Override
    public boolean updateBooking(int bookingId, Booking updatedBooking) {

        // 1. Check Booking
        Booking booking = searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        // 2. Check Booking Status
        if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {
            return false;
        }

        // 3. Check Customer
        Customer customer = customerRepository.searchCustomer(updatedBooking.getCustomerId());

        if (customer == null) {
            return false;
        }

        // 4. Check Room
        Room room = roomRepository.searchRoom(updatedBooking.getRoomNumber());

        if (room == null) {
            return false;
        }

        // 5. Validate Dates
        if (!updatedBooking.getCheckOutDate().isAfter(updatedBooking.getCheckInDate())) {
            return false;
        }

        // 6. Check Booking Conflicts
        boolean hasConflict = bookingRepository.getBookings().stream()
                .filter(oldBooking -> oldBooking.getBookingId() != bookingId)
                .filter(oldBooking -> oldBooking.getRoomNumber() == updatedBooking.getRoomNumber())
                .filter(oldBooking -> oldBooking.getBookingStatus() != BookingStatus.CANCELLED)
                .anyMatch(oldBooking ->
                        updatedBooking.getCheckInDate().isBefore(oldBooking.getCheckOutDate())
                                &&
                                updatedBooking.getCheckOutDate().isAfter(oldBooking.getCheckInDate())
                );

        if (hasConflict) {
            return false;
        }

        // 7. Update Booking
        return bookingRepository.updateBooking(bookingId, updatedBooking);
    }
    @Override
    public List<Booking> getBookings() {
        return bookingRepository.getBookings();
    }

    @Override
    public boolean cancelBookingsByCustomerId(int customerId) {
        List<Booking> bookingsCustomerById = bookingRepository.getBookings().stream()
                .filter(booking -> booking.getCustomerId() == customerId && booking.getBookingStatus() == BookingStatus.CONFIRMED)
                .toList();
        if (bookingsCustomerById.isEmpty()) {
            return false;
        }
        bookingsCustomerById.forEach(booking -> booking.setBookingStatus(BookingStatus.CANCELLED));
        return true;
    }
    @Override
    public boolean cancelBookingsByRoomNumber(int roomNumber) {
        List<Booking> bookingsRoomNumber = bookingRepository.getBookings().stream()
                .filter(booking -> booking.getRoomNumber() == roomNumber && booking.getBookingStatus() == BookingStatus.CONFIRMED)
                .toList();
        if (bookingsRoomNumber.isEmpty()) {
            return false;
        }
        bookingsRoomNumber.forEach(booking -> booking.setBookingStatus(BookingStatus.CANCELLED));
        return true;
    }
}
