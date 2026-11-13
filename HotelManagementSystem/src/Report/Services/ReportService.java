package Report.Services;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Customers.Entite.Customer;
import Payment.Entites.Payment;
import Payment.Enums.PaymentStatus;
import Payment.Enums.PaymentType;
import Rooms.Enums.RoomStatus;
import Rooms.Enums.RoomTybe;
import Rooms.entite.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Defines the reporting operations available in the Hotel Management System.
 * Provides methods for generating reports related to rooms, customers,
 * bookings, and payments.
 */
public interface ReportService {

    // ========== ROOM REPORTS ==========

    /**
     * Retrieves a report containing all rooms.
     *
     * @return a list containing all rooms
     */
    List<Room> getAllRoomsReport();

    /**
     * Retrieves rooms filtered by their current status.
     *
     * @param roomStatus the status used to filter rooms
     * @return a list of rooms matching the specified status
     */
    List<Room> getRoomsByStatusReport(RoomStatus roomStatus);

    /**
     * Retrieves rooms filtered by their type.
     *
     * @param roomTybe the type used to filter rooms
     * @return a list of rooms matching the specified type
     */
    List<Room> getRoomsByTypeReport(RoomTybe roomTybe);

    /**
     * Retrieves rooms matching a specific capacity.
     *
     * @param capacity the required room capacity
     * @return a list of rooms matching the specified capacity
     */
    List<Room> getRoomsByCapacityReport(int capacity);

    /**
     * Retrieves rooms whose prices fall within the specified range.
     *
     * @param minPrice the minimum room price
     * @param maxPrice the maximum room price
     * @return a list of rooms within the specified price range
     */
    List<Room> getRoomsByPriceRangeReport(
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    // ========== CUSTOMER REPORTS ==========

    /**
     * Retrieves a report containing all customers.
     *
     * @return a list containing all customers
     */
    List<Customer> getAllCustomersReport();

    /**
     * Retrieves a customer using their unique ID.
     *
     * @param customerId the ID of the customer to retrieve
     * @return the matching customer if found; otherwise, null
     */
    Customer getCustomerByIdReport(int customerId);

    /**
     * Retrieves all bookings associated with a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a list containing the customer's bookings
     */
    List<Booking> getCustomerBookingsReport(int customerId);

    /**
     * Retrieves customers who have at least one booking.
     *
     * @return a list of customers with bookings
     */
    List<Customer> getCustomersWithBookingsReport();

    /**
     * Retrieves customers who do not have any bookings.
     *
     * @return a list of customers without bookings
     */
    List<Customer> getCustomersWithoutBookingsReport();

    // ========== BOOKING REPORTS ==========

    /**
     * Retrieves a report containing all bookings.
     *
     * @return a list containing all bookings
     */
    List<Booking> getAllBookingsReport();

    /**
     * Retrieves bookings filtered by their status.
     *
     * @param status the booking status used for filtering
     * @return a list of bookings matching the specified status
     */
    List<Booking> getBookingsByStatusReport(BookingStatus status);

    /**
     * Retrieves all bookings associated with a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a list containing the customer's bookings
     */
    List<Booking> getBookingsByCustomerIdReport(int customerId);

    /**
     * Retrieves all bookings associated with a specific room.
     *
     * @param roomNumber the number of the room
     * @return a list containing the room's bookings
     */
    List<Booking> getBookingsByRoomNumberReport(int roomNumber);

    /**
     * Retrieves bookings created or scheduled within the specified date range.
     *
     * @param startDate the beginning of the date range
     * @param endDate the end of the date range
     * @return a list of bookings matching the specified date range
     */
    List<Booking> getBookingsByDateRangeReport(
            LocalDate startDate,
            LocalDate endDate
    );

    // ========== PAYMENT REPORTS ==========

    /**
     * Retrieves a report containing all payments.
     *
     * @return a list containing all payments
     */
    List<Payment> getAllPaymentsReport();

    /**
     * Retrieves all payments associated with a specific booking.
     *
     * @param bookingId the ID of the booking
     * @return a list containing the payments associated with the booking
     */
    List<Payment> getPaymentsByBookingIdReport(int bookingId);

    /**
     * Retrieves payments filtered by their transaction status.
     *
     * @param status the payment status used for filtering
     * @return a list of payments matching the specified status
     */
    List<Payment> getPaymentsByStatusReport(PaymentStatus status);

    /**
     * Retrieves payments filtered by their transaction type.
     *
     * @param type the payment type used for filtering
     * @return a list of payments matching the specified type
     */
    List<Payment> getPaymentsByTypeReport(PaymentType type);

    /**
     * Retrieves payments within the specified date range.
     *
     * @param startDate the beginning of the date range
     * @param endDate the end of the date range
     * @return a list of payments matching the specified date range
     */
    List<Payment> getPaymentsByDateRangeReport(
            LocalDate startDate,
            LocalDate endDate
    );
}