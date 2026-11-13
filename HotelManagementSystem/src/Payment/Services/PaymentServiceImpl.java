
package Payment.Services;

import Booking.Entite.Booking;
import Booking.Packages.BookingRepository;
import Payment.Entites.Payment;
import Payment.Enums.PaymentStatus;
import Payment.Enums.PaymentType;
import Payment.Packages.PaymentRepository;
import Validations.ValidationUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implements payment operations and payment-related business rules.
 *
 * Handles payments, refunds, remaining balances, and payment
 * requirements for check-in.
 */
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final AtomicInteger paymentIdGenerator = new AtomicInteger(1);

    /**
     * Constructs the payment service with its required repositories.
     *
     * @param paymentRepository repository used to manage payments
     * @param bookingRepository repository used to retrieve bookings
     * @throws IllegalArgumentException if either repository is null
     */
    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository) {

        ValidationUtils.requireNotNull(paymentRepository, "Payment Repository");
        ValidationUtils.requireNotNull(bookingRepository, "Booking Repository");

        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Adds a payment for an existing booking.
     *
     * If the payment amount exceeds the remaining balance,
     * an additional refund transaction is created automatically.
     *
     * If saving the refund fails, the original payment is deleted.
     *
     * @param bookingId ID of the booking
     * @param paymentAmount amount submitted for payment
     * @return true if the payment process succeeds, false otherwise
     */
    @Override
    public boolean addPayment(int bookingId, BigDecimal paymentAmount) {

        // Validate input while preserving the existing false-return behavior.
        if (bookingId <= 0 || paymentAmount == null ||
                paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        // Check if booking exists.
        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        // Calculate remaining amount.
        BigDecimal remainingAmount = getRemainingAmount(bookingId);

        // Prevent additional payments when the booking is already fully paid.
        if (remainingAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        // Generate Payment ID.
        int paymentId = paymentIdGenerator.getAndIncrement();

        // Create successful payment.
        Payment payment = new Payment(
                bookingId,
                paymentId,
                LocalDate.now(),
                paymentAmount,
                PaymentStatus.SUCCESS,
                PaymentType.PAYMENT
        );

        // Save payment.
        if (!paymentRepository.addPayment(payment)) {
            return false;
        }

        // Check if there is an excess amount.
        if (paymentAmount.compareTo(remainingAmount) > 0) {

            BigDecimal refundAmount = paymentAmount.subtract(remainingAmount);

            int refundId = paymentIdGenerator.getAndIncrement();

            // Create refund transaction.
            Payment refund = new Payment(
                    bookingId,
                    refundId,
                    LocalDate.now(),
                    refundAmount,
                    PaymentStatus.SUCCESS,
                    PaymentType.REFUND
            );

            // Save refund transaction.
            if (!paymentRepository.addPayment(refund)) {
                paymentRepository.deletePayment(paymentId);
                return false;
            }
        }

        return true;
    }

    /**
     * Calculates the total successful payments for a booking.
     *
     * Payment transactions are added to the total,
     * while refund transactions are subtracted.
     *
     * @param bookingId ID of the booking
     * @return total amount paid after deducting successful refunds,
     *         or zero if the booking does not exist
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public BigDecimal getTotalPaidByBookingId(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return BigDecimal.ZERO;
        }

        return paymentRepository.getPayments().stream()
                .filter(payment ->
                        payment.getBookingId() == bookingId &&
                                payment.getPaymentStatus() == PaymentStatus.SUCCESS)
                .map(payment -> {
                    if (payment.getPaymentType() == PaymentType.PAYMENT) {
                        return payment.getPaymentAmount();
                    }

                    return payment.getPaymentAmount().negate();
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calculates the remaining amount required to pay for a booking.
     *
     * @param bookingId ID of the booking
     * @return remaining amount, or zero if the booking does not exist
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public BigDecimal getRemainingAmount(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return BigDecimal.ZERO;
        }

        return booking.getTotalPrice()
                .subtract(getTotalPaidByBookingId(bookingId));
    }

    /**
     * Checks whether a booking has received at least 50% of its total price.
     *
     * This method is used to determine whether the minimum payment
     * requirement for check-in has been satisfied.
     *
     * @param bookingId ID of the booking
     * @return true if at least 50% has been paid, false otherwise
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public boolean hasPaidMinimumForCheckIn(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        BigDecimal totalPrice = booking.getTotalPrice();
        BigDecimal totalPaid = getTotalPaidByBookingId(bookingId);

        BigDecimal minimumAmount = totalPrice.multiply(new BigDecimal("0.5"));

        return totalPaid.compareTo(minimumAmount) >= 0;
    }

    /**
     * Checks whether a booking has paid its full amount.
     *
     * @param bookingId ID of the booking
     * @return true if the total paid amount is equal to or greater
     *         than the booking total price, false otherwise
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public boolean hasPaidFullAmount(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        BigDecimal totalPrice = booking.getTotalPrice();
        BigDecimal totalPaid = getTotalPaidByBookingId(bookingId);

        return totalPaid.compareTo(totalPrice) >= 0;
    }

    /**
     * Searches for a payment using its ID.
     *
     * @param paymentId unique payment identifier
     * @return matching payment, or null if not found
     * @throws IllegalArgumentException if paymentId is not positive
     */
    @Override
    public Payment searchPayment(int paymentId) {

        ValidationUtils.requirePositive(paymentId, "Payment ID");

        return paymentRepository.searchPayment(paymentId);
    }

    /**
     * Retrieves all payment transactions.
     *
     * @return list of all payments
     */
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getPayments();
    }

    /**
     * Retrieves all payment transactions associated with a booking.
     *
     * @param bookingId ID of the booking
     * @return list of payments associated with the booking
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public List<Payment> getPaymentsByBookingId(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

        return paymentRepository.getPaymentsByBookingId(bookingId);
    }

    /**
     * Checks whether a booking exists.
     *
     * @param bookingId ID of the booking
     * @return true if the booking exists, false otherwise
     * @throws IllegalArgumentException if bookingId is not positive
     */
    @Override
    public boolean bookingExists(int bookingId) {

        ValidationUtils.requirePositive(bookingId, "Booking ID");

        return bookingRepository.searchBooking(bookingId) != null;
    }
}
