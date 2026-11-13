package Booking.services;

import Booking.Entite.Booking;

import java.util.Collections;
import java.util.List;

/**
 * Defines the business operations available for managing bookings.
 * Provides methods for creating, searching, updating, and cancelling bookings.
 */
public interface BookingService {

    /**
     * Adds a new booking.
     *
     * @param booking the booking to be added
     * @return true if the booking is added successfully; otherwise, false
     */
    public boolean addBooking(Booking booking);

    /**
     * Searches for a booking using its unique ID.
     *
     * @param bookingId the ID of the booking to search for
     * @return the matching booking if found; otherwise, null
     */
    public Booking searchBooking(int bookingId);

    /**
     * Cancels a booking using its unique ID.
     *
     * @param bookingId the ID of the booking to cancel
     * @return true if the booking is cancelled successfully; otherwise, false
     */
    public boolean cancelBooking(int bookingId);

    /**
     * Updates the information of an existing booking.
     *
     * @param bookingId the ID of the booking to update
     * @param updatedBooking the booking containing the updated information
     * @return true if the booking is updated successfully; otherwise, false
     */
    public boolean updateBooking(int bookingId, Booking updatedBooking);

    /**
     * Retrieves all bookings.
     *
     * @return a list containing all bookings
     */
    public List<Booking> getBookings();

    /**
     * Cancels all bookings associated with a specific customer.
     *
     * @param customerId the ID of the customer whose bookings should be cancelled
     * @return true if the cancellation operation succeeds; otherwise, false
     */
    boolean cancelBookingsByCustomerId(int customerId);

    /**
     * Cancels all bookings associated with a specific room.
     *
     * @param roomNumber the number of the room whose bookings should be cancelled
     * @return true if the cancellation operation succeeds; otherwise, false
     */
    public boolean cancelBookingsByRoomNumber(int roomNumber);

}