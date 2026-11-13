
package Report.Services;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Booking.Packages.BookingRepository;

import Customers.Entite.Customer;
import Customers.Packages.CustomerRepository;

import Payment.Entites.Payment;
import Payment.Enums.PaymentStatus;
import Payment.Enums.PaymentType;
import Payment.Packages.PaymentRepository;

import Rooms.Enums.RoomStatus;
import Rooms.Enums.RoomTybe;
import Rooms.entite.Room;
import Rooms.pakages.RoomRepository;

import Validations.ValidationUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Provides reporting operations for rooms, customers,
 * bookings, and payments.
 *
 * Uses repositories to retrieve data and Stream API
 * to filter records according to report criteria.
 */
public class ReportServiceImpl implements ReportService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;

    /**
     * Constructs ReportServiceImpl with the required repositories.
     *
     * @param roomRepository repository for room data
     * @param bookingRepository repository for booking data
     * @param paymentRepository repository for payment data
     * @param customerRepository repository for customer data
     * @throws IllegalArgumentException if any repository is null
     */
    public ReportServiceImpl(
            RoomRepository roomRepository,
            BookingRepository bookingRepository,
            PaymentRepository paymentRepository,
            CustomerRepository customerRepository) {

        ValidationUtils.requireNotNull(roomRepository, "Room Repository");
        ValidationUtils.requireNotNull(bookingRepository, "Booking Repository");
        ValidationUtils.requireNotNull(paymentRepository, "Payment Repository");
        ValidationUtils.requireNotNull(customerRepository, "Customer Repository");

        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
    }

    // ================= ROOM REPORTS =================

    /**
     * Retrieves all rooms.
     *
     * @return list of all rooms
     */
    @Override
    public List<Room> getAllRoomsReport() {
        return roomRepository.getAllRooms().stream().toList();
    }

    /**
     * Retrieves rooms filtered by status.
     *
     * @param roomStatus status used to filter rooms
     * @return list of rooms matching the given status
     * @throws IllegalArgumentException if roomStatus is null
     */
    @Override
    public List<Room> getRoomsByStatusReport(RoomStatus roomStatus) {
        ValidationUtils.requireNotNull(roomStatus, "Room Status");

        return roomRepository.getAllRooms().stream()
                .filter(r -> r.getRoomStatus() == roomStatus)
                .toList();
    }

    /**
     * Retrieves rooms filtered by type.
     *
     * @param roomTybe type used to filter rooms
     * @return list of rooms matching the given type
     * @throws IllegalArgumentException if roomTybe is null
     */
    @Override
    public List<Room> getRoomsByTypeReport(RoomTybe roomTybe) {
        ValidationUtils.requireNotNull(roomTybe, "Room Type");

        return roomRepository.getAllRooms().stream()
                .filter(r -> r.getRoomTybe() == roomTybe)
                .toList();
    }

    /**
     * Retrieves rooms with a specific capacity.
     *
     * @param capacity required room capacity
     * @return list of rooms matching the capacity
     * @throws IllegalArgumentException if capacity is not positive
     */
    @Override
    public List<Room> getRoomsByCapacityReport(int capacity) {
        ValidationUtils.requirePositive(capacity, "Room Capacity");

        return roomRepository.getAllRooms().stream()
                .filter(r -> r.getRoomCapacity() == capacity)
                .toList();
    }

    /**
     * Retrieves rooms whose prices fall within the specified range.
     *
     * @param minPrice minimum room price
     * @param maxPrice maximum room price
     * @return list of rooms within the price range
     * @throws IllegalArgumentException if either price is null,
     *         non-positive, or minPrice is greater than maxPrice
     */
    @Override
    public List<Room> getRoomsByPriceRangeReport(
            BigDecimal minPrice, BigDecimal maxPrice) {

        ValidationUtils.requirePositive(minPrice, "Minimum Price");
        ValidationUtils.requirePositive(maxPrice, "Maximum Price");

        if (minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException(
                    "Minimum Price cannot be greater than Maximum Price"
            );
        }

        return roomRepository.getAllRooms().stream()
                .filter(r -> r.getRoomPrice().compareTo(minPrice) >= 0
                        && r.getRoomPrice().compareTo(maxPrice) <= 0)
                .toList();
    }

    // ================= CUSTOMER REPORTS =================

    /**
     * Retrieves all customers.
     *
     * @return list of all customers
     */
    @Override
    public List<Customer> getAllCustomersReport() {
        return customerRepository.getCustomers().stream().toList();
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param customerId unique customer identifier
     * @return matching customer, or null if not found
     * @throws IllegalArgumentException if customerId is not positive
     */
    @Override
    public Customer getCustomerByIdReport(int customerId) {
        ValidationUtils.requirePositive(customerId, "Customer ID");

        return customerRepository.searchCustomer(customerId);
    }

    /**
     * Retrieves all bookings belonging to a specific customer.
     *
     * @param customerId customer identifier
     * @return list of customer bookings
     * @throws IllegalArgumentException if customerId is not positive
     */
    @Override
    public List<Booking> getCustomerBookingsReport(int customerId) {
        ValidationUtils.requirePositive(customerId, "Customer ID");

        return bookingRepository.getBookings().stream()
                .filter(b -> b.getCustomerId() == customerId)
                .toList();
    }

    /**
     * Retrieves customers who have at least one booking.
     *
     * @return list of customers with bookings
     */
    @Override
    public List<Customer> getCustomersWithBookingsReport() {
        return customerRepository.getCustomers().stream()
                .filter(c -> bookingRepository.getBookings().stream()
                        .anyMatch(b -> b.getCustomerId() == c.getCustomerId()))
                .toList();
    }

    /**
     * Retrieves customers who have no bookings.
     *
     * @return list of customers without bookings
     */
    @Override
    public List<Customer> getCustomersWithoutBookingsReport() {
        return customerRepository.getCustomers().stream()
                .filter(c -> bookingRepository.getBookings().stream()
                        .noneMatch(b -> b.getCustomerId() == c.getCustomerId()))
                .toList();
    }

    // ================= BOOKING REPORTS =================

    /**
     * Retrieves all bookings.
     *
     * @return list of all bookings
     */
    @Override
    public List<Booking> getAllBookingsReport() {
        return bookingRepository.getBookings().stream().toList();
    }

    /**
     * Retrieves bookings filtered by status.
     *
     * @param status booking status used for filtering
     * @return list of bookings matching the status
     * @throws IllegalArgumentException if status is null
     */
    @Override
    public List<Booking> getBookingsByStatusReport(BookingStatus status) {
        ValidationUtils.requireNotNull(status, "Booking Status");

        return bookingRepository.getBookings().stream()
                .filter(b -> b.getBookingStatus() == status)
                .toList();
    }

    /**
     * Retrieves bookings belonging to a specific customer.
     *
     * @param customerId customer identifier
     * @return list of bookings belonging to the customer
     * @throws IllegalArgumentException if customerId is not positive
     */
    @Override
    public List<Booking> getBookingsByCustomerIdReport(int customerId) {
        ValidationUtils.requirePositive(customerId, "Customer ID");

        return bookingRepository.getBookings().stream()
                .filter(b -> b.getCustomerId() == customerId)
                .toList();
    }

    /**
     * Retrieves bookings associated with a specific room.
     *
     * @param roomNumber room number used for filtering
     * @return list of bookings associated with the room
     * @throws IllegalArgumentException if roomNumber is not positive
     */
    @Override
    public List<Booking> getBookingsByRoomNumberReport(int roomNumber) {
        ValidationUtils.requirePositive(roomNumber, "Room Number");

        return bookingRepository.getBookings().stream()
                .filter(b -> b.getRoomNumber() == roomNumber)
                .toList();
    }

    /**
     * Retrieves bookings whose check-in dates fall within a date range.
     *
     * @param startDate beginning of the date range
     * @param endDate end of the date range
     * @return list of bookings within the date range
     * @throws IllegalArgumentException if either date is null
     *         or startDate is after endDate
     */
    @Override
    public List<Booking> getBookingsByDateRangeReport(
            LocalDate startDate, LocalDate endDate) {

        ValidationUtils.requireNotNull(startDate, "Start Date");
        ValidationUtils.requireNotNull(endDate, "End Date");

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Start Date cannot be after End Date"
            );
        }

        return bookingRepository.getBookings().stream()
                .filter(b -> !b.getCheckInDate().isBefore(startDate)
                        && !b.getCheckInDate().isAfter(endDate))
                .toList();
    }

    // ================= PAYMENT REPORTS =================

    /**
     * Retrieves all payments.
     *
     * @return list of all payments
     */
    @Override
    public List<Payment> getAllPaymentsReport() {
        return paymentRepository.getPayments().stream().toList();
    }

    /**
     * Retrieves payments associated with a specific booking.
     *
     * @param bookingId booking identifier
     * @return list of payments belonging to the booking
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public List<Payment> getPaymentsByBookingIdReport(int bookingId) {
        ValidationUtils.requirePositive(bookingId, "Booking ID");

        return paymentRepository.getPayments().stream()
                .filter(p -> p.getBookingId() == bookingId)
                .toList();
    }

    /**
     * Retrieves payments filtered by payment status.
     *
     * @param status payment status used for filtering
     * @return list of payments matching the status
     * @throws IllegalArgumentException if status is null
     */
    @Override
    public List<Payment> getPaymentsByStatusReport(PaymentStatus status) {
        ValidationUtils.requireNotNull(status, "Payment Status");

        return paymentRepository.getPayments().stream()
                .filter(p -> p.getPaymentStatus() == status)
                .toList();
    }

    /**
     * Retrieves payments filtered by payment type.
     *
     * @param type payment type used for filtering
     * @return list of payments matching the type
     * @throws IllegalArgumentException if type is null
     */
    @Override
    public List<Payment> getPaymentsByTypeReport(PaymentType type) {
        ValidationUtils.requireNotNull(type, "Payment Type");

        return paymentRepository.getPayments().stream()
                .filter(p -> p.getPaymentType() == type)
                .toList();
    }

    /**
     * Retrieves payments whose payment dates fall within a date range.
     *
     * @param startDate beginning of the date range
     * @param endDate end of the date range
     * @return list of payments within the date range
     * @throws IllegalArgumentException if either date is null
     *         or startDate is after endDate
     */
    @Override
    public List<Payment> getPaymentsByDateRangeReport(
            LocalDate startDate, LocalDate endDate) {

        ValidationUtils.requireNotNull(startDate, "Start Date");
        ValidationUtils.requireNotNull(endDate, "End Date");

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Start Date cannot be after End Date"
            );
        }

        return paymentRepository.getPayments().stream()
                .filter(p -> !p.getPaymentDate().isBefore(startDate)
                        && !p.getPaymentDate().isAfter(endDate))
                .toList();
    }
}
