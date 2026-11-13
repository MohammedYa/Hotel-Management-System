package Payment.Packages;

import Payment.Entites.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Repository responsible for managing payment records in memory.
 * Provides operations to add, search, delete, and retrieve payments,
 * including retrieving payments associated with a specific booking.
 */
public class PaymentRepository {

    private ArrayList<Payment> payments = new ArrayList<>();

    /**
     * Adds a new payment to the repository.
     *
     * @param payment the payment to be added
     * @return true if the payment is added successfully;
     *         false if the payment is null or already exists
     */
    public boolean addPayment(Payment payment) {
        if (payment == null) {
            return false;
        }
        if (payments.contains(payment)) {
            return false;
        }
        payments.add(payment);
        return true;
    }

    /**
     * Searches for a payment using its unique ID.
     *
     * @param paymentId the ID of the payment to search for
     * @return the matching payment if found; otherwise, null
     */
    public Payment searchPayment(int paymentId) {
        Payment payment = payments.stream()
                .filter(p -> p.getPaymentId() == paymentId)
                .findFirst()
                .orElse(null);
        return payment;
    }

    /**
     * Deletes a payment from the repository using its ID.
     *
     * @param paymentId the ID of the payment to delete
     * @return true if the payment is found and deleted;
     *         false if no matching payment exists
     */
    public boolean deletePayment(int paymentId) {
        Payment payment = searchPayment(paymentId);

        if (payment == null) {
            return false;
        }

        payments.remove(payment);
        return true;
    }

    /**
     * Retrieves an unmodifiable view of all payments in the repository.
     *
     * @return an unmodifiable list containing all payments
     */
    public List<Payment> getPayments() {
        return Collections.unmodifiableList(payments);
    }

    /**
     * Retrieves all payments associated with a specific booking.
     *
     * @param bookingId the ID of the booking whose payments are requested
     * @return an unmodifiable list containing the payments associated
     *         with the specified booking
     */
    public List<Payment> getPaymentsByBookingId(int bookingId) {
        List<Payment> paymentsByBookingId = payments.stream()
                .filter(p -> p.getBookingId() == bookingId)
                .toList();
        return Collections.unmodifiableList(paymentsByBookingId);
    }

}