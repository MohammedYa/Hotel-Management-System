
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

public interface ReportService {

    // ========== ROOM REPORTS ==========

    List<Room> getAllRoomsReport();

    List<Room> getRoomsByStatusReport(RoomStatus roomStatus);

    List<Room> getRoomsByTypeReport(RoomTybe roomTybe);

    List<Room> getRoomsByCapacityReport(int capacity);

    List<Room> getRoomsByPriceRangeReport(
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    // ========== CUSTOMER REPORTS ==========

    List<Customer> getAllCustomersReport();

    Customer getCustomerByIdReport(int customerId);

    List<Booking> getCustomerBookingsReport(int customerId);

    List<Customer> getCustomersWithBookingsReport();

    List<Customer> getCustomersWithoutBookingsReport();

    // ========== BOOKING REPORTS ==========

    List<Booking> getAllBookingsReport();

    List<Booking> getBookingsByStatusReport(BookingStatus status);

    List<Booking> getBookingsByCustomerIdReport(int customerId);

    List<Booking> getBookingsByRoomNumberReport(int roomNumber);

    List<Booking> getBookingsByDateRangeReport(
            LocalDate startDate,
            LocalDate endDate
    );

    // ========== PAYMENT REPORTS ==========

    List<Payment> getAllPaymentsReport();

    List<Payment> getPaymentsByBookingIdReport(int bookingId);

    List<Payment> getPaymentsByStatusReport(PaymentStatus status);

    List<Payment> getPaymentsByTypeReport(PaymentType type);

    List<Payment> getPaymentsByDateRangeReport(
            LocalDate startDate,
            LocalDate endDate
    );
}
