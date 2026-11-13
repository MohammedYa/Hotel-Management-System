package CheckInOut.Services;

/**
 * Defines the business operations for managing booking check-in and check-out.
 */
public interface CheckInOutService {

    /**
     * Checks in a booking.
     *
     * @param bookingId the ID of the booking to check in
     * @return true if the check-in operation is successful; otherwise, false
     */
    boolean checkIn(int bookingId);

    /**
     * Checks out a booking.
     *
     * @param bookingId the ID of the booking to check out
     * @return true if the check-out operation is successful; otherwise, false
     */
    boolean checkOut(int bookingId);

}