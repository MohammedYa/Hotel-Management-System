package Booking.Packages;

import Booking.Entite.Booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repository responsible for managing booking data in memory.
 * Provides operations to add, search, update, delete, and retrieve bookings.
 */
public class BookingRepository {

    private List<Booking> bookings = new ArrayList<>();

    /**
     * Constructs a new BookingRepository with an empty booking list.
     */
    public BookingRepository() {
    }

    /**
     * Adds a new booking to the repository.
     *
     * @param booking the booking to be added
     * @return true if the booking was added successfully;
     *         false if the booking already exists
     */
    public boolean addBooking(Booking booking) {
        if (this.bookings.contains(booking)) {
            return false;
        }
        this.bookings.add(booking);
        return true;
    }

    /**
     * Searches for a booking using its unique ID.
     *
     * @param bookingId the ID of the booking to search for
     * @return the matching booking if found; otherwise, null
     */
    public Booking searchBooking(int bookingId) {
        return this.bookings.stream()
                .filter(b -> b.getBookingId() == bookingId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Deletes a booking from the repository using its ID.
     *
     * @param bookingId the ID of the booking to delete
     * @return true if the booking was found and deleted;
     *         false if no matching booking exists
     */
    public boolean deleteBooking(int bookingId) {
        Booking booking = searchBooking(bookingId);

        if (booking != null) {
            return this.bookings.remove(booking);
        }

        return false;
    }

    /**
     * Updates the room number, customer ID, check-in date,
     * and check-out date of an existing booking.
     *
     * @param bookingId the ID of the booking to update
     * @param updatedBooking the booking containing the updated information
     * @return true if the booking was found and updated;
     *         false if no matching booking exists
     */
    public boolean updateBooking(int bookingId, Booking updatedBooking) {
        Booking booking = searchBooking(bookingId);
        if (booking != null) {
            booking.setRoomNumber(updatedBooking.getRoomNumber());
            booking.setCustomerId(updatedBooking.getCustomerId());
            booking.setCheckInDate(updatedBooking.getCheckInDate());
            booking.setCheckOutDate(updatedBooking.getCheckOutDate());
            return true;
        }
        return false;
    }

    /**
     * Retrieves an unmodifiable view of all bookings in the repository.
     *
     * @return an unmodifiable list containing all bookings
     */
    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }
}