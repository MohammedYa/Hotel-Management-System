
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReportServiceImpl implements ReportService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;

    public ReportServiceImpl(
            RoomRepository roomRepository,
            BookingRepository bookingRepository,
            PaymentRepository paymentRepository,
            CustomerRepository customerRepository) {

        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
    }

    // ================= ROOM REPORTS =================

    @Override
    public List<Room> getAllRoomsReport() {
        return roomRepository.getAllRooms().stream().toList();
    }

    @Override
    public List<Room> getRoomsByStatusReport(RoomStatus roomStatus) {
        return roomRepository.getAllRooms().stream()
                .filter(r -> r.getRoomStatus() == roomStatus)
                .toList();
    }

    @Override
    public List<Room> getRoomsByTypeReport(RoomTybe roomTybe) {
        return roomRepository.getAllRooms().stream()
                .filter(r -> r.getRoomTybe() == roomTybe)
                .toList();
    }

    @Override
    public List<Room> getRoomsByCapacityReport(int capacity) {
        return roomRepository.getAllRooms().stream()
                .filter(r -> r.getRoomCapacity() == capacity)
                .toList();
    }

    @Override
    public List<Room> getRoomsByPriceRangeReport(
            BigDecimal minPrice, BigDecimal maxPrice) {

        return roomRepository.getAllRooms().stream()
                .filter(r -> r.getRoomPrice().compareTo(minPrice) >= 0
                        && r.getRoomPrice().compareTo(maxPrice) <= 0)
                .toList();
    }

    // ================= CUSTOMER REPORTS =================

    @Override
    public List<Customer> getAllCustomersReport() {
        return customerRepository.getCustomers().stream().toList();
    }

    @Override
    public Customer getCustomerByIdReport(int customerId) {
        return customerRepository.searchCustomer(customerId);
    }

    @Override
    public List<Booking> getCustomerBookingsReport(int customerId) {
        return bookingRepository.getBookings().stream()
                .filter(b -> b.getCustomerId() == customerId)
                .toList();
    }

    @Override
    public List<Customer> getCustomersWithBookingsReport() {
        return customerRepository.getCustomers().stream()
                .filter(c -> bookingRepository.getBookings().stream()
                        .anyMatch(b -> b.getCustomerId() == c.getCustomerId()))
                .toList();
    }

    @Override
    public List<Customer> getCustomersWithoutBookingsReport() {
        return customerRepository.getCustomers().stream()
                .filter(c -> bookingRepository.getBookings().stream()
                        .noneMatch(b -> b.getCustomerId() == c.getCustomerId()))
                .toList();
    }

    // ================= BOOKING REPORTS =================

    @Override
    public List<Booking> getAllBookingsReport() {
        return bookingRepository.getBookings().stream().toList();
    }

    @Override
    public List<Booking> getBookingsByStatusReport(BookingStatus status) {
        return bookingRepository.getBookings().stream()
                .filter(b -> b.getBookingStatus() == status)
                .toList();
    }

    @Override
    public List<Booking> getBookingsByCustomerIdReport(int customerId) {
        return bookingRepository.getBookings().stream()
                .filter(b -> b.getCustomerId() == customerId)
                .toList();
    }

    @Override
    public List<Booking> getBookingsByRoomNumberReport(int roomNumber) {
        return bookingRepository.getBookings().stream()
                .filter(b -> b.getRoomNumber() == roomNumber)
                .toList();
    }

    @Override
    public List<Booking> getBookingsByDateRangeReport(
            LocalDate startDate, LocalDate endDate) {

        return bookingRepository.getBookings().stream()
                .filter(b -> !b.getCheckInDate().isBefore(startDate)
                        && !b.getCheckInDate().isAfter(endDate))
                .toList();
    }

    // ================= PAYMENT REPORTS =================

    @Override
    public List<Payment> getAllPaymentsReport() {
        return paymentRepository.getPayments().stream().toList();
    }

    @Override
    public List<Payment> getPaymentsByBookingIdReport(int bookingId) {
        return paymentRepository.getPayments().stream()
                .filter(p -> p.getBookingId() == bookingId)
                .toList();
    }

    @Override
    public List<Payment> getPaymentsByStatusReport(PaymentStatus status) {
        return paymentRepository.getPayments().stream()
                .filter(p -> p.getPaymentStatus() == status)
                .toList();
    }

    @Override
    public List<Payment> getPaymentsByTypeReport(PaymentType type) {
        return paymentRepository.getPayments().stream()
                .filter(p -> p.getPaymentType() == type)
                .toList();
    }

    @Override
    public List<Payment> getPaymentsByDateRangeReport(
            LocalDate startDate, LocalDate endDate) {

        return paymentRepository.getPayments().stream()
                .filter(p -> !p.getPaymentDate().isBefore(startDate)
                        && !p.getPaymentDate().isAfter(endDate))
                .toList();
    }
}
