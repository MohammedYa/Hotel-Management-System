package Payment.Entites;

import Payment.Enums.PaymentStatus;
import Payment.Enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment {
    private int bookingId;
    private int paymentId;
    private LocalDate paymentDate;
    private BigDecimal paymentAmount;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;

    public Payment(int bookingId, int paymentId, LocalDate paymentDate, BigDecimal paymentAmount, PaymentStatus paymentStatus, PaymentType paymentType) {
       setPaymentId(paymentId);
       setBookingId(bookingId);
       setPaymentDate(paymentDate);
       setPaymentAmount(paymentAmount);
       setPaymentStatus(paymentStatus);
       setPaymentType(paymentType);

    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        if (bookingId <= 0) {
            throw new IllegalArgumentException("Invalid Booking ID");
        }
        this.bookingId = bookingId;
    }
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        if (paymentId <= 0) {
            throw new IllegalArgumentException("Invalid Payment ID");
        }
        this.paymentId = paymentId;
    }
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        if (paymentDate == null) {
            throw new IllegalArgumentException("Payment date cannot be null");
        }
        this.paymentDate = paymentDate;
    }
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        if (paymentAmount == null || paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        this.paymentAmount = paymentAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        if (paymentStatus == null) {
            throw new IllegalArgumentException("Payment status cannot be null");
        }
        this.paymentStatus = paymentStatus;
    }
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("Payment Type cannot be null");
        }
        this.paymentType = paymentType;
    }








    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        if (paymentId != other.paymentId)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return this.paymentId;
    }

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
