package Payment.Packages;

import Payment.Entites.Payment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PaymentRepository {
    private ArrayList<Payment> payments = new ArrayList<>();



   public boolean addPayment(Payment payment){
        if (payment==null){
            return false;
        }
        if (payments.contains(payment)){
            return false;
        }
        payments.add(payment);
        return true;
    }

   public Payment searchPayment(int paymentId){
        Payment payment = payments.stream().filter(p -> p.getPaymentId() == paymentId).findFirst().orElse(null);
        return payment;

    }

    public boolean deletePayment(int paymentId) {
        Payment payment = searchPayment(paymentId);

        if (payment == null) {
            return false;
        }

        payments.remove(payment);
        return true;
    }

    public List<Payment> getPayments(){


        return Collections.unmodifiableList(payments);
    }

    public List<Payment> getPaymentsByBookingId(int bookingId){
        List<Payment> paymentsByBookingId=payments.stream().filter(p->p.getBookingId()==bookingId).toList();
        return Collections.unmodifiableList (paymentsByBookingId);
    }
}
