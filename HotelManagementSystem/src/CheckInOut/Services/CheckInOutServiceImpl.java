
package CheckInOut.Services;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Booking.Packages.BookingRepository;
import Payment.Services.PaymentService;
import Rooms.Enums.RoomStatus;
import Rooms.entite.Room;
import Rooms.pakages.RoomRepository;
import Validations.ValidationUtils;

import java.time.LocalDate;

/**
 * Implements check-in and check-out operations.
 *
 * Coordinates booking, room, and payment services to ensure
 * that hotel check-in and check-out business rules are respected.
 */
public class CheckInOutServiceImpl implements CheckInOutService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final PaymentService paymentService;

    /**
     * Constructs the service with the required dependencies.
     *
     * @param bookingRepository repository used to retrieve bookings
     * @param roomRepository repository used to retrieve rooms
     * @param paymentService service used to verify booking payments
     * @throws IllegalArgumentException if any dependency is null
     */
    public CheckInOutServiceImpl(
            BookingRepository bookingRepository,
            RoomRepository roomRepository,
            PaymentService paymentService) {

        ValidationUtils.requireNotNull(bookingRepository, "Booking Repository");
        ValidationUtils.requireNotNull(roomRepository, "Room Repository");
        ValidationUtils.requireNotNull(paymentService, "Payment Service");

        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.paymentService = paymentService;
    }

    /**
     * Checks in a customer for a confirmed booking.
     *
     * The booking must exist, its check-in date must have arrived,
     * the room must be available, and at least 50% of the booking
     * total price must have been paid.
     *
     * @param bookingId ID of the booking
     * @return true if check-in succeeds, false otherwise
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public boolean checkIn(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {
            return false;
        }

        if (LocalDate.now().isBefore(booking.getCheckInDate())) {
            return false;
        }

        // Verify the minimum payment requirement.
        if (!paymentService.hasPaidMinimumForCheckIn(bookingId)) {
            return false;
        }

        Room room = roomRepository.searchRoom(booking.getRoomNumber());

        if (room == null) {
            return false;
        }

        if (room.getRoomStatus() != RoomStatus.AVAILABLE) {
            return false;
        }

        room.setRoomStatus(RoomStatus.OCCUPIED);

        return true;
    }

    /**
     * Checks out a customer from an occupied room.
     *
     * The booking must exist and be confirmed, the room must be occupied,
     * and the booking check-in date must have arrived.
     *
     * After successful check-out, the booking status becomes COMPLETED
     * and the room status becomes CLEANING.
     *
     * @param bookingId ID of the booking
     * @return true if check-out succeeds, false otherwise
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public boolean checkOut(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {
            return false;
        }

        Room room = roomRepository.searchRoom(booking.getRoomNumber());

        if (room == null) {
            return false;
        }

        if (room.getRoomStatus() != RoomStatus.OCCUPIED) {
            return false;
        }

        if (LocalDate.now().isBefore(booking.getCheckInDate())) {
            return false;
        }

        booking.setBookingStatus(BookingStatus.COMPLETED);
        room.setRoomStatus(RoomStatus.CLEANING);

        return true;
    }
}
