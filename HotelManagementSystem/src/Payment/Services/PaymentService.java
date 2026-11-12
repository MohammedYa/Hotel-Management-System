package Payment.Services;
import Payment.Entites.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    boolean addPayment(int bookingId, BigDecimal paymentAmount);
    BigDecimal getTotalPaidByBookingId(int bookingId);
    BigDecimal getRemainingAmount(int bookingId);
    boolean hasPaidMinimumForCheckIn(int bookingId);
    boolean hasPaidFullAmount(int bookingId);
    Payment searchPayment(int paymentId);
    List<Payment> getAllPayments();
    List<Payment> getPaymentsByBookingId(int bookingId);
    boolean bookingExists(int bookingId);
}
