package Payment.Services;

import Booking.Entite.Booking;
import Booking.Packages.BookingRepository;
import Payment.Entites.Payment;
import Payment.Enums.PaymentStatus;
import Payment.Enums.PaymentType;
import Payment.Packages.PaymentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.math.BigDecimal;

public class PaymentServiceImpl  implements  PaymentService{
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final AtomicInteger paymentIdGenerator = new AtomicInteger(1);
    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public boolean addPayment(int bookingId, BigDecimal paymentAmount) {

        // 1. Check if booking exists
        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null || paymentAmount == null ||
                paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        // 2. Calculate remaining amount
        BigDecimal remainingAmount = getRemainingAmount(bookingId);

        // 3. Generate Payment ID
        int paymentId = paymentIdGenerator.getAndIncrement();

        // 4. Create successful payment
        Payment payment = new Payment(
                bookingId,
                paymentId,
                LocalDate.now(),
                paymentAmount,
                PaymentStatus.SUCCESS,
                PaymentType.PAYMENT
        );

        // 5. Save payment
        if (!paymentRepository.addPayment(payment)) {
            return false;
        }

        // 6. Check if there is an excess amount
        if (paymentAmount.compareTo(remainingAmount) > 0) {

            BigDecimal refundAmount = paymentAmount.subtract(remainingAmount);

            int refundId = paymentIdGenerator.getAndIncrement();

            // 7. Create refund transaction
            Payment refund = new Payment(
                    bookingId,
                    refundId,
                    LocalDate.now(),
                    refundAmount,
                    PaymentStatus.SUCCESS,
                    PaymentType.REFUND
            );

            // 8. Save refund
            if (!paymentRepository.addPayment(refund)) {
                paymentRepository.deletePayment(paymentId);
                return false;
            }
        }

        return true;
    }

    @Override
    public BigDecimal getTotalPaidByBookingId(int bookingId) {
        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal totalPaidAmount = paymentRepository.getPayments().stream()
                .filter(payment -> payment.getBookingId() == bookingId &&
                        payment.getPaymentStatus() == PaymentStatus.SUCCESS)
                .map(payment -> {
                    if (payment.getPaymentType() == PaymentType.PAYMENT) {
                        return payment.getPaymentAmount();
                    }
                    return payment.getPaymentAmount().negate();
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPaidAmount;
    }

    @Override
    public BigDecimal getRemainingAmount(int bookingId) {
        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return BigDecimal.ZERO;
        }

        return booking.getTotalPrice()
                .subtract(getTotalPaidByBookingId(bookingId));
    }

    @Override
    public boolean hasPaidMinimumForCheckIn(int bookingId) {

        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        BigDecimal totalPrice = booking.getTotalPrice();
        BigDecimal totalPaid = getTotalPaidByBookingId(bookingId);

        BigDecimal minimumAmount = totalPrice.multiply(new BigDecimal("0.5"));

        return totalPaid.compareTo(minimumAmount) >= 0;
    }

    @Override
    public boolean hasPaidFullAmount(int bookingId) {
        Booking booking = bookingRepository.searchBooking(bookingId);

        if (booking == null) {
            return false;
        }

        BigDecimal totalPrice = booking.getTotalPrice();
        BigDecimal totalPaid = getTotalPaidByBookingId(bookingId);



        return totalPaid.compareTo(totalPrice) >= 0;
    }

    @Override
    public Payment searchPayment(int paymentId) {
        return paymentRepository.searchPayment(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getPayments();
    }

    @Override
    public List<Payment> getPaymentsByBookingId(int bookingId) {
        return paymentRepository.getPaymentsByBookingId(bookingId);
    }
    @Override
    public boolean bookingExists(int bookingId) {
        return bookingRepository.searchBooking(bookingId) != null;
    }
}
