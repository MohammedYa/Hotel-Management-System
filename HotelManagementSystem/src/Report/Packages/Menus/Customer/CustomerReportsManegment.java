
package Report.Packages.Menus.Customer;

import Booking.Entite.Booking;
import Customers.Entite.Customer;
import Report.Services.ReportService;
import Validations.ValidationUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Handles user interactions related to customer reports.
 *
 * Provides reports for all customers, individual customers,
 * customer bookings, and customers with or without bookings.
 */
public class CustomerReportsManegment {

    private final ReportService reportService;

    private final CustomerReportsMenu customerReportsMenu =
            new CustomerReportsMenu();

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs CustomerReportsManegment with the required service.
     *
     * @param reportService service responsible for generating reports
     * @throws IllegalArgumentException if reportService is null
     */
    public CustomerReportsManegment(ReportService reportService) {

        ValidationUtils.requireNotNull(reportService, "Report Service");

        this.reportService = reportService;
    }

    /**
     * Starts the customer reports menu.
     *
     * Allows the user three invalid menu attempts before exiting.
     */
    public void Start() {

        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {

            customerReportsMenu.displayMenu();

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
                    System.out.println("All Customers");
                    displayAllCustomersReport();
                    break;

                case 2:
                    tryChoiceCount = 0;
                    System.out.println("Customer By ID");
                    getCustomerByIdReport();
                    break;

                case 3:
                    tryChoiceCount = 0;
                    System.out.println("Customer Bookings");
                    getCustomerBookingsReport();
                    break;

                case 4:
                    tryChoiceCount = 0;
                    System.out.println("Customers With Bookings");
                    displayCustomersWithBookingsReport();
                    break;

                case 5:
                    tryChoiceCount = 0;
                    System.out.println("Customers Without Bookings");
                    displayCustomersWithoutBookingsReport();
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
     * Displays all customers registered in the system.
     */
    private void displayAllCustomersReport() {

        List<Customer> customers =
                reportService.getAllCustomersReport();

        if (customers.isEmpty()) {
            System.out.println("There are no customers in the system");
            return;
        }

        customers.forEach(System.out::println);
    }

    /**
     * Displays a customer using their ID.
     */
    private void getCustomerByIdReport() {

        int customerId = readPositiveInt("Enter Customer ID: ");

        Customer customer =
                reportService.getCustomerByIdReport(customerId);

        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }

        System.out.println(customer);
    }

    /**
     * Displays all bookings associated with a specific customer.
     */
    private void getCustomerBookingsReport() {

        int customerId = readPositiveInt("Enter Customer ID: ");

        List<Booking> bookings =
                reportService.getCustomerBookingsReport(customerId);

        if (bookings.isEmpty()) {
            System.out.println("This customer has no bookings");
            return;
        }

        bookings.forEach(System.out::println);
    }

    /**
     * Displays customers who have at least one booking.
     */
    private void displayCustomersWithBookingsReport() {

        List<Customer> customers =
                reportService.getCustomersWithBookingsReport();

        if (customers.isEmpty()) {
            System.out.println("There are no customers with bookings");
            return;
        }

        customers.forEach(System.out::println);
    }

    /**
     * Displays customers who do not have any bookings.
     */
    private void displayCustomersWithoutBookingsReport() {

        List<Customer> customers =
                reportService.getCustomersWithoutBookingsReport();

        if (customers.isEmpty()) {
            System.out.println("There are no customers without bookings");
            return;
        }

        customers.forEach(System.out::println);
    }
}
