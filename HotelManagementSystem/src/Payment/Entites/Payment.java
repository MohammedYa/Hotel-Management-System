
package Payment.Entites;

import Payment.Enums.PaymentStatus;
import Payment.Enums.PaymentType;
import Validations.ValidationUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a payment associated with a booking.
 * Stores payment information including amount, date, status, and type.
 */
public class Payment {

    private int bookingId;
    private int paymentId;
    private LocalDate paymentDate;
    private BigDecimal paymentAmount;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;

    /**
     * Constructs a Payment object with the specified details.
     *
     * @param bookingId associated booking ID
     * @param paymentId unique payment ID
     * @param paymentDate date of payment
     * @param paymentAmount payment amount
     * @param paymentStatus current payment status
     * @param paymentType payment method or type
     * @throws IllegalArgumentException if any value violates validation rules
     */
    public Payment(int bookingId, int paymentId, LocalDate paymentDate,
                   BigDecimal paymentAmount, PaymentStatus paymentStatus,
                   PaymentType paymentType) {

        setPaymentId(paymentId);
        setBookingId(bookingId);
        setPaymentDate(paymentDate);
        setPaymentAmount(paymentAmount);
        setPaymentStatus(paymentStatus);
        setPaymentType(paymentType);
    }

    /**
     * Gets the associated booking ID.
     *
     * @return booking ID
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Sets the associated booking ID.
     *
     * @param bookingId booking identifier
     * @throws IllegalArgumentException if bookingId is not positive
     */
    public void setBookingId(int bookingId) {
        ValidationUtils.requirePositive(bookingId, "Booking ID");
        this.bookingId = bookingId;
    }

    /**
     * Gets the payment ID.
     *
     * @return payment ID
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the payment ID.
     *
     * @param paymentId unique payment identifier
     * @throws IllegalArgumentException if paymentId is not positive
     */
    public void setPaymentId(int paymentId) {
        ValidationUtils.requirePositive(paymentId, "Payment ID");
        this.paymentId = paymentId;
    }

    /**
     * Gets the payment date.
     *
     * @return payment date
     */
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the payment date.
     *
     * @param paymentDate date of payment
     * @throws IllegalArgumentException if paymentDate is null
     */
    public void setPaymentDate(LocalDate paymentDate) {
        ValidationUtils.requireNotNull(paymentDate, "Payment Date");
        this.paymentDate = paymentDate;
    }

    /**
     * Gets the payment amount.
     *
     * @return payment amount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the payment amount.
     *
     * @param paymentAmount amount paid
     * @throws IllegalArgumentException if paymentAmount is null or not positive
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        ValidationUtils.requirePositive(paymentAmount, "Payment Amount");
        this.paymentAmount = paymentAmount;
    }

    /**
     * Gets the payment status.
     *
     * @return payment status
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the payment status.
     *
     * @param paymentStatus current payment status
     * @throws IllegalArgumentException if paymentStatus is null
     */
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        ValidationUtils.requireNotNull(paymentStatus, "Payment Status");
        this.paymentStatus = paymentStatus;
    }

    /**
     * Gets the payment type.
     *
     * @return payment type
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type.
     *
     * @param paymentType payment type
     * @throws IllegalArgumentException if paymentType is null
     */
    public void setPaymentType(PaymentType paymentType) {
        ValidationUtils.requireNotNull(paymentType, "Payment Type");
        this.paymentType = paymentType;
    }

    /**
     * Compares this payment with another object based on payment ID.
     *
     * @param obj object to compare
     * @return true if both objects represent the same payment
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Payment other = (Payment) obj;
        return paymentId == other.paymentId;
    }

    /**
     * Generates a hash code based on payment ID.
     *
     * @return hash code of this payment
     */
    @Override
    public int hashCode() {
        return this.paymentId;
    }

    /**
     * Returns a formatted representation of payment details.
     *
     * @return formatted payment information
     */
    @Override
    public String toString() {
        return "========== PAYMENT DETAILS ==========\n" +
                "Payment ID     : " + this.paymentId + "\n" +
                "Booking ID     : " + this.bookingId + "\n" +
                "Amount         : " + this.paymentAmount + "\n" +
                "Payment Type   : " + this.paymentType + "\n" +
                "Payment Status : " + this.paymentStatus + "\n" +
                "Payment Date   : " + this.paymentDate + "\n" +
                "======================================";
    }
}
