
package Booking.services;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Booking.Packages.BookingRepository;
import Customers.Packages.CustomerRepository;
import Rooms.pakages.RoomRepository;
import Validations.ValidationUtils;

import java.util.List;

/**
 * Implements booking management operations.
 *
 * Handles booking creation, searching, updating, cancellation,
 * and validation of booking conflicts.
 */
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    /**
     * Constructs BookingServiceImpl with the required repositories.
     *
     * @param bookingRepository repository used to manage bookings
     * @param customerRepository repository used to verify customers
     * @param roomRepository repository used to verify rooms
     * @throws IllegalArgumentException if any repository is null
     */
    public BookingServiceImpl(
            BookingRepository bookingRepository,
            CustomerRepository customerRepository,
            RoomRepository roomRepository) {

        ValidationUtils.requireNotNull(bookingRepository, "Booking Repository");
        ValidationUtils.requireNotNull(customerRepository, "Customer Repository");
        ValidationUtils.requireNotNull(roomRepository, "Room Repository");

        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Adds a new booking after validating its customer, room,
     * dates, and possible conflicts with existing bookings.
     *
     * @param booking booking to be added
     * @return true if the booking is added successfully, false otherwise
     * @throws IllegalArgumentException if booking is null
     */
    @Override
    public boolean addBooking(Booking booking) {

        ValidationUtils.requireNotNull(booking, "Booking");

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

        // 5. Check Duplicate Booking ID
        if (bookingRepository.searchBooking(booking.getBookingId()) != null) {
            return false;
        }

        // 6. Save Booking
        return bookingRepository.addBooking(booking);
    }

    /**
     * Searches for a booking using its ID.
     *
     * @param bookingId unique booking identifier
     * @return matching booking, or null if not found
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public Booking searchBooking(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

        return bookingRepository.searchBooking(bookingId);
    }

    /**
     * Cancels an existing confirmed booking.
     *
     * @param bookingId ID of the booking to cancel
     * @return true if the booking is cancelled, false otherwise
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public boolean cancelBooking(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

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

    /**
     * Updates an existing confirmed booking.
     *
     * Validates the customer, room, dates, booking ID consistency,
     * and conflicts with other bookings for the same room.
     *
     * @param bookingId ID of the booking to update
     * @param updatedBooking updated booking information
     * @return true if the booking is updated successfully, false otherwise
     * @throws IllegalArgumentException if bookingId is not positive
     *         or updatedBooking is null
     */
    @Override
    public boolean updateBooking(int bookingId, Booking updatedBooking) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");
        ValidationUtils.requireNotNull(updatedBooking, "Updated Booking");

        // 1. Check Booking
        Booking booking = searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        // 2. Check Booking Status
        if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {
            return false;
        }

        // 3. Ensure Booking ID remains unchanged
        if (updatedBooking.getBookingId() != bookingId) {
            return false;
        }

        // 4. Check Customer
        if (customerRepository.searchCustomer(updatedBooking.getCustomerId()) == null) {
            return false;
        }

        // 5. Check Room
        if (roomRepository.searchRoom(updatedBooking.getRoomNumber()) == null) {
            return false;
        }

        // 6. Validate Dates
        if (!updatedBooking.getCheckOutDate()
                .isAfter(updatedBooking.getCheckInDate())) {
            return false;
        }

        // 7. Check Booking Conflicts
        boolean hasConflict = bookingRepository.getBookings().stream()
                .filter(oldBooking -> oldBooking.getBookingId() != bookingId)
                .filter(oldBooking ->
                        oldBooking.getRoomNumber() == updatedBooking.getRoomNumber())
                .filter(oldBooking ->
                        oldBooking.getBookingStatus() != BookingStatus.CANCELLED)
                .anyMatch(oldBooking ->
                        updatedBooking.getCheckInDate()
                                .isBefore(oldBooking.getCheckOutDate())
                                &&
                                updatedBooking.getCheckOutDate()
                                        .isAfter(oldBooking.getCheckInDate())
                );

        if (hasConflict) {
            return false;
        }

        // 8. Update Booking
        return bookingRepository.updateBooking(bookingId, updatedBooking);
    }

    /**
     * Retrieves all bookings.
     *
     * @return list of all bookings
     */
    @Override
    public List<Booking> getBookings() {
        return bookingRepository.getBookings();
    }

    /**
     * Cancels all confirmed bookings belonging to a specific customer.
     *
     * @param customerId customer identifier
     * @return true if at least one booking was cancelled, false otherwise
     * @throws IllegalArgumentException if customerId is not positive
     */
    @Override
    public boolean cancelBookingsByCustomerId(int customerId) {

        ValidationUtils.requirePositive(customerId, "Customer ID");

        List<Booking> bookingsCustomerById = bookingRepository.getBookings().stream()
                .filter(booking ->
                        booking.getCustomerId() == customerId &&
                                booking.getBookingStatus() == BookingStatus.CONFIRMED)
                .toList();

        if (bookingsCustomerById.isEmpty()) {
            return false;
        }

        bookingsCustomerById.forEach(booking ->
                booking.setBookingStatus(BookingStatus.CANCELLED));

        return true;
    }

    /**
     * Cancels all confirmed bookings associated with a specific room.
     *
     * @param roomNumber room number
     * @return true if at least one booking was cancelled, false otherwise
     * @throws IllegalArgumentException if roomNumber is not positive
     */
    @Override
    public boolean cancelBookingsByRoomNumber(int roomNumber) {

        ValidationUtils.requirePositive(roomNumber, "Room Number");

        List<Booking> bookingsRoomNumber = bookingRepository.getBookings().stream()
                .filter(booking ->
                        booking.getRoomNumber() == roomNumber &&
                                booking.getBookingStatus() == BookingStatus.CONFIRMED)
                .toList();

        if (bookingsRoomNumber.isEmpty()) {
            return false;
        }

        bookingsRoomNumber.forEach(booking ->
                booking.setBookingStatus(BookingStatus.CANCELLED));

        return true;
    }
}
