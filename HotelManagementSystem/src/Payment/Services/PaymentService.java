package Payment.Services;

import Payment.Entites.Payment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Defines the business operations for managing payment transactions.
 * Provides methods for processing payments, tracking paid and remaining
 * amounts, checking payment requirements, and retrieving payment records.
 */
public interface PaymentService {

    /**
     * Processes a payment for a specific booking.
     *
     * @param bookingId the ID of the booking associated with the payment
     * @param paymentAmount the amount to be paid
     * @return true if the payment is processed successfully; otherwise, false
     */
    boolean addPayment(int bookingId, BigDecimal paymentAmount);

    /**
     * Calculates the total amount paid for a specific booking.
     *
     * @param bookingId the ID of the booking
     * @return the total amount paid for the specified booking
     */
    BigDecimal getTotalPaidByBookingId(int bookingId);

    /**
     * Calculates the remaining amount required to complete the booking payment.
     *
     * @param bookingId the ID of the booking
     * @return the remaining amount to be paid
     */
    BigDecimal getRemainingAmount(int bookingId);

    /**
     * Checks whether the minimum payment required for check-in has been paid.
     *
     * @param bookingId the ID of the booking
     * @return true if the minimum payment requirement is satisfied; otherwise, false
     */
    boolean hasPaidMinimumForCheckIn(int bookingId);

    /**
     * Checks whether the full booking amount has been paid.
     *
     * @param bookingId the ID of the booking
     * @return true if the full amount has been paid; otherwise, false
     */
    boolean hasPaidFullAmount(int bookingId);

    /**
     * Searches for a payment using its unique ID.
     *
     * @param paymentId the ID of the payment to search for
     * @return the matching payment if found; otherwise, null
     */
    Payment searchPayment(int paymentId);

    /**
     * Retrieves all payment records.
     *
     * @return a list containing all payments
     */
    List<Payment> getAllPayments();

    /**
     * Retrieves all payments associated with a specific booking.
     *
     * @param bookingId the ID of the booking
     * @return a list containing the payments associated with the booking
     */
    List<Payment> getPaymentsByBookingId(int bookingId);

    /**
     * Checks whether a booking exists in the system.
     *
     * @param bookingId the ID of the booking to check
     * @return true if the booking exists; otherwise, false
     */
    boolean bookingExists(int bookingId);
}