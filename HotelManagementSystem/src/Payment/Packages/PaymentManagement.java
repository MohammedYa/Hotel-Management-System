
package Payment.Packages;

import Payment.Entites.Payment;
import Payment.Services.PaymentService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PaymentManagement {

    private final PaymentMenu paymentMenu = new PaymentMenu();
    private final PaymentService paymentService;
    private final Scanner scanner = new Scanner(System.in);

    public PaymentManagement(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void Start() {
        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {
            try {
                paymentMenu.displayMenu();

                System.out.print("Enter Your Choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        flag = false;
                        break;

                    case 1:
                        tryChoiceCount = 0;
                        addPayment();
                        break;

                    case 2:
                        tryChoiceCount = 0;
                        searchPayment();
                        break;

                    case 3:
                        tryChoiceCount = 0;
                        displayAllPayments();
                        break;

                    case 4:
                        tryChoiceCount = 0;
                        displayPaymentsByBooking();
                        break;

                    case 5:
                        tryChoiceCount = 0;
                        displayPaymentSummary();
                        break;

                    default:
                        tryChoiceCount++;
                        System.out.println("Invalid Choice! Choose a number between 0-5.");
                        System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");

                        if (tryChoiceCount >= 3) {
                            System.out.println("Too many invalid attempts. Try again later.");
                            flag = false;
                        }
                }

            } catch (InputMismatchException e) {
                scanner.nextLine();

                tryChoiceCount++;

                System.out.println("Invalid input! Please enter a number.");
                System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");

                if (tryChoiceCount >= 3) {
                    System.out.println("Too many invalid attempts. Try again later.");
                    flag = false;
                }
            }
        }
    }

    // Add Payment
    private void addPayment() {
        try {
            System.out.print("Enter Booking ID: ");
            int bookingId = scanner.nextInt();

            System.out.print("Enter Payment Amount: ");
            BigDecimal paymentAmount = scanner.nextBigDecimal();

            boolean result = paymentService.addPayment(bookingId, paymentAmount);

            if (result) {
                System.out.println("Payment added successfully.");
            } else {
                System.out.println("Payment failed. Check booking ID and amount.");
            }

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input! Please enter valid numbers.");
        }
    }

    // Search Payment By ID
    private void searchPayment() {
        try {
            System.out.print("Enter Payment ID: ");
            int paymentId = scanner.nextInt();

            Payment payment = paymentService.searchPayment(paymentId);

            if (payment == null) {
                System.out.println("Payment not found.");
                return;
            }

            System.out.println("Payment Details:");
            System.out.println(payment);

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid Payment ID.");
        }
    }

    // Display All Payments
    private void displayAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();

        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }

        System.out.println("========== All Payments ==========");

        for (Payment payment : payments) {
            System.out.println(payment);
            System.out.println("----------------------------------");
        }
    }

    // Display Payments By Booking ID
    private void displayPaymentsByBooking() {
        try {
            System.out.print("Enter Booking ID: ");
            int bookingId = scanner.nextInt();

            if (!paymentService.bookingExists(bookingId)) {
                System.out.println("Booking not found.");
                return;
            }

            List<Payment> payments =
                    paymentService.getPaymentsByBookingId(bookingId);

            if (payments.isEmpty()) {
                System.out.println("No payment records found for this booking.");
                return;
            }

            System.out.println("========== Booking Payments ==========");

            for (Payment payment : payments) {
                System.out.println(payment);
                System.out.println("--------------------------------------");
            }

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid Booking ID.");
        }
    }

    // Display Payment Summary
    private void displayPaymentSummary() {
        try {
            System.out.print("Enter Booking ID: ");
            int bookingId = scanner.nextInt();

            if (!paymentService.bookingExists(bookingId)) {
                System.out.println("Booking not found.");
                return;
            }

            BigDecimal totalPaid =
                    paymentService.getTotalPaidByBookingId(bookingId);

            BigDecimal remainingAmount =
                    paymentService.getRemainingAmount(bookingId);

            BigDecimal totalPrice = totalPaid.add(remainingAmount);

            System.out.println("\n========== Payment Summary ==========");
            System.out.println("Booking ID: " + bookingId);
            System.out.println("Total Booking Price: " + totalPrice);
            System.out.println("Total Paid: " + totalPaid);
            System.out.println("Remaining Amount: " + remainingAmount);
            System.out.println("=====================================");

        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid Booking ID. Please enter a number.");
        }
    }
}
