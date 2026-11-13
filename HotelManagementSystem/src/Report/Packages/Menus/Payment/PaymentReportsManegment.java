package Report.Packages.Menus.Payment;

import Payment.Entites.Payment;
import Payment.Enums.PaymentStatus;
import Payment.Enums.PaymentType;
import Report.Services.ReportService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PaymentReportsManegment {
    private final ReportService reportService;
    PaymentReportsMenu paymentReportsMenu=new PaymentReportsMenu();
    private final Scanner scanner = new Scanner(System.in);

    public PaymentReportsManegment(ReportService reportService ) {
        this.reportService=reportService;
    }

    public void Start() {
        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {
            try {
                paymentReportsMenu.displayMenu();

                System.out.print("Enter Your Choice: ");
                int choice = scanner.nextInt();

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
                        System.out.println("3. SUCCESS Payments ");
                        getPaymentsByStatusReport(PaymentStatus.SUCCESS);
                        break;

                    case 4:
                        tryChoiceCount = 0;
                        System.out.println("4. FAILED Payments ");
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
                        System.out.println("Invalid Choice! Choose a number between 0-7.");
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




    // ================= PAYMENT REPORTS =================

    private void displayAllPaymentsReport() {
        List<Payment> payments = reportService.getAllPaymentsReport();

        if (payments.isEmpty()) {
            System.out.println("There are no payments in the system");
            return;
        }

        payments.forEach(System.out::println);
    }

    private void getPaymentsByBookingIdReport() {
        System.out.print("Enter Booking ID: ");
        int bookingId = scanner.nextInt();

        List<Payment> payments =
                reportService.getPaymentsByBookingIdReport(bookingId);

        if (payments.isEmpty()) {
            System.out.println("There are no payments for this booking");
            return;
        }

        payments.forEach(System.out::println);
    }

    private void getPaymentsByStatusReport( PaymentStatus status) {


        List<Payment> payments = reportService.getPaymentsByStatusReport(status);

        if (payments.isEmpty()) {
            System.out.println("There are no payments with this status");
            return;
        }

        payments.forEach(System.out::println);
    }

    private void getPaymentsByTypeReport( PaymentType type) {

        List<Payment> payments = reportService.getPaymentsByTypeReport(type);

        if (payments.isEmpty()) {
            System.out.println("There are no payments with this type");
            return;
        }

        payments.forEach(System.out::println);
    }

    private void getPaymentsByDateRangeReport() {
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.next());

        System.out.print("Enter End Date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.next());

        List<Payment> payments =
                reportService.getPaymentsByDateRangeReport(startDate, endDate);

        if (payments.isEmpty()) {
            System.out.println("There are no payments in this date range");
            return;
        }

        payments.forEach(System.out::println);
    }

}
