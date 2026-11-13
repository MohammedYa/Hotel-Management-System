
package Report.Packages.Menus.Payment;

import Payment.Entites.Payment;
import Payment.Enums.PaymentStatus;
import Payment.Enums.PaymentType;
import Report.Services.ReportService;
import Validations.ValidationUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Handles user interactions related to payment reports.
 *
 * Provides reports for all payments, payments by booking ID,
 * payment status, payment type, and date range.
 */
public class PaymentReportsManegment {

    private final ReportService reportService;

    private final PaymentReportsMenu paymentReportsMenu =
            new PaymentReportsMenu();

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs PaymentReportsManegment with the required service.
     *
     * @param reportService service responsible for generating reports
     * @throws IllegalArgumentException if reportService is null
     */
    public PaymentReportsManegment(ReportService reportService) {

        ValidationUtils.requireNotNull(reportService, "Report Service");

        this.reportService = reportService;
    }

    /**
     * Starts the payment reports menu.
     *
     * Allows the user three invalid menu attempts before exiting.
     */
    public void Start() {

        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {

            paymentReportsMenu.displayMenu();

            System.out.print("Enter Your Choice: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input.trim());

            } catch (NumberFormatException e) {

                tryChoiceCount++;

                System.out.println(
                        "Invalid input! Please enter a number."
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
                    System.out.println("All Payments");
                    displayAllPaymentsReport();
                    break;

                case 2:
                    tryChoiceCount = 0;
                    System.out.println("Payments By Booking ID");
                    getPaymentsByBookingIdReport();
                    break;

                case 3:
                    tryChoiceCount = 0;
                    System.out.println("3. SUCCESS Payments");
                    getPaymentsByStatusReport(PaymentStatus.SUCCESS);
                    break;

                case 4:
                    tryChoiceCount = 0;
                    System.out.println("4. FAILED Payments");
                    getPaymentsByStatusReport(PaymentStatus.FAILED);
                    break;

                case 5:
                    tryChoiceCount = 0;
                    System.out.println("5. PAYMENT Payments");
                    getPaymentsByTypeReport(PaymentType.PAYMENT);
                    break;

                case 6:
                    tryChoiceCount = 0;
                    System.out.println("6. REFUND Payments");
                    getPaymentsByTypeReport(PaymentType.REFUND);
                    break;

                case 7:
                    tryChoiceCount = 0;
                    System.out.println("7. Payments By Date Range");
                    getPaymentsByDateRangeReport();
                    break;

                default:
                    tryChoiceCount++;

                    System.out.println(
                            "Invalid Choice! Choose a number between 0-7."
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
     * Repeats until a valid positive integer is entered.
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
     * Reads a date from the user using the YYYY-MM-DD format.
     *
     * @param message prompt displayed to the user
     * @return parsed LocalDate
     */
    private LocalDate readDate(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {
                return LocalDate.parse(input.trim());

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Invalid date! Use the format YYYY-MM-DD."
                );
            }
        }
    }

    /**
     * Displays all payments recorded in the system.
     */
    private void displayAllPaymentsReport() {

        List<Payment> payments =
                reportService.getAllPaymentsReport();

        if (payments.isEmpty()) {
            System.out.println("There are no payments in the system");
            return;
        }

        payments.forEach(System.out::println);
    }

    /**
     * Displays payments associated with a specific booking.
     */
    private void getPaymentsByBookingIdReport() {

        int bookingId = readPositiveInt("Enter Booking ID: ");

        List<Payment> payments =
                reportService.getPaymentsByBookingIdReport(bookingId);

        if (payments.isEmpty()) {
            System.out.println("There are no payments for this booking");
            return;
        }

        payments.forEach(System.out::println);
    }

    /**
     * Displays payments filtered by their status.
     *
     * @param status payment status used as the report filter
     */
    private void getPaymentsByStatusReport(PaymentStatus status) {

        List<Payment> payments =
                reportService.getPaymentsByStatusReport(status);

        if (payments.isEmpty()) {
            System.out.println("There are no payments with this status");
            return;
        }

        payments.forEach(System.out::println);
    }

    /**
     * Displays payments filtered by their type.
     *
     * @param type payment type used as the report filter
     */
    private void getPaymentsByTypeReport(PaymentType type) {

        List<Payment> payments =
                reportService.getPaymentsByTypeReport(type);

        if (payments.isEmpty()) {
            System.out.println("There are no payments with this type");
            return;
        }

        payments.forEach(System.out::println);
    }

    /**
     * Displays payments within a specified date range.
     *
     * Reads the start and end dates and delegates the filtering
     * operation to ReportService.
     */
    private void getPaymentsByDateRangeReport() {

        LocalDate startDate =
                readDate("Enter Start Date (YYYY-MM-DD): ");

        LocalDate endDate =
                readDate("Enter End Date (YYYY-MM-DD): ");

        if (endDate.isBefore(startDate)) {
            System.out.println(
                    "End date must be after or equal to start date."
            );
            return;
        }

        List<Payment> payments =
                reportService.getPaymentsByDateRangeReport(
                        startDate,
                        endDate
                );

        if (payments.isEmpty()) {
            System.out.println(
                    "There are no payments in this date range"
            );
            return;
        }

        payments.forEach(System.out::println);
    }
}
