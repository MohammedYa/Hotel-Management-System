
package Payment.Packages;

import Payment.Entites.Payment;
import Payment.Services.PaymentService;
import Validations.ValidationUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Handles user interactions related to payment management.
 *
 * Provides operations for adding payments, searching for payments,
 * displaying payment records, and viewing booking payment summaries.
 */
public class PaymentManagement {

    private final PaymentMenu paymentMenu = new PaymentMenu();
    private final PaymentService paymentService;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs PaymentManagement with the required service.
     *
     * @param paymentService service responsible for payment operations
     * @throws IllegalArgumentException if paymentService is null
     */
    public PaymentManagement(PaymentService paymentService) {

        ValidationUtils.requireNotNull(paymentService, "Payment Service");

        this.paymentService = paymentService;
    }

    /**
     * Starts the payment management menu.
     *
     * Allows the user three invalid menu attempts before exiting.
     */
    public void Start() {

        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {

            paymentMenu.displayMenu();

            System.out.print("Enter Your Choice: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {

                tryChoiceCount++;

                System.out.println(
                        "Invalid input! Please enter a number between 0-5."
                );
                System.out.println(
                        "You have " + (3 - tryChoiceCount) + " chances left."
                );

                if (tryChoiceCount >= 3) {
                    System.out.println(
                            "Too many invalid attempts. Try again later."
                    );
                    flag = false;
                }

                continue;
            }

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

                    System.out.println(
                            "Invalid Choice! Choose a number between 0-5."
                    );
                    System.out.println(
                            "You have " + (3 - tryChoiceCount) + " chances left."
                    );

                    if (tryChoiceCount >= 3) {
                        System.out.println(
                                "Too many invalid attempts. Try again later."
                        );
                        flag = false;
                    }
            }
        }
    }

    /**
     * Reads a positive integer from the user.
     *
     * Repeats the input operation until a valid positive integer is entered.
     *
     * @param message prompt displayed to the user
     * @return positive integer entered by the user
     */
    private int readPositiveInt(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                int value = Integer.parseInt(input.trim());

                if (value > 0) {
                    return value;
                }

                System.out.println("Value must be greater than zero!");

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input! Please enter a valid integer."
                );
            }
        }
    }

    /**
     * Reads a positive BigDecimal value from the user.
     *
     * @param message prompt displayed to the user
     * @return positive decimal value entered by the user
     */
    private BigDecimal readPositiveAmount(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                BigDecimal amount = new BigDecimal(input.trim());

                if (amount.compareTo(BigDecimal.ZERO) > 0) {
                    return amount;
                }

                System.out.println("Amount must be greater than zero!");

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid amount! Please enter a valid decimal number."
                );
            }
        }
    }

    /**
     * Adds a payment to an existing booking.
     *
     * Collects the booking ID and payment amount,
     * then delegates the operation to PaymentService.
     */
    private void addPayment() {

        System.out.println("\n========== Add Payment ==========");

        int bookingId = readPositiveInt("Enter Booking ID: ");

        BigDecimal paymentAmount = readPositiveAmount(
                "Enter Payment Amount: "
        );

        boolean result = paymentService.addPayment(
                bookingId,
                paymentAmount
        );

        if (result) {
            System.out.println("Payment added successfully.");
        } else {
            System.out.println(
                    "Payment failed. Check booking ID and amount."
            );
        }
    }

    /**
     * Searches for a payment using its payment ID.
     *
     * Displays the payment details if the payment exists.
     */
    private void searchPayment() {

        System.out.println("\n========== Search Payment ==========");

        int paymentId = readPositiveInt("Enter Payment ID: ");

        Payment payment = paymentService.searchPayment(paymentId);

        if (payment == null) {
            System.out.println("Payment not found.");
            return;
        }

        System.out.println("Payment Details:");
        System.out.println(payment);
    }

    /**
     * Displays all payment records.
     *
     * Displays a message if no payments are available.
     */
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

    /**
     * Displays all payment records associated with a specific booking.
     *
     * Checks whether the booking exists before retrieving its payments.
     */
    private void displayPaymentsByBooking() {

        System.out.println("\n========== Booking Payments ==========");

        int bookingId = readPositiveInt("Enter Booking ID: ");

        if (!paymentService.bookingExists(bookingId)) {
            System.out.println("Booking not found.");
            return;
        }

        List<Payment> payments =
                paymentService.getPaymentsByBookingId(bookingId);

        if (payments.isEmpty()) {
            System.out.println(
                    "No payment records found for this booking."
            );
            return;
        }

        System.out.println("========== Booking Payments ==========");

        for (Payment payment : payments) {
            System.out.println(payment);
            System.out.println("--------------------------------------");
        }
    }

    /**
     * Displays the payment summary of a specific booking.
     *
     * Calculates the total booking price using the total paid amount
     * and the remaining amount.
     */
    private void displayPaymentSummary() {

        System.out.println("\n========== Payment Summary ==========");

        int bookingId = readPositiveInt("Enter Booking ID: ");

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
    }
}
