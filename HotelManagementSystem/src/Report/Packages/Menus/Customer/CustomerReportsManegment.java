package Report.Packages.Menus.Customer;

import Booking.Entite.Booking;
import Customers.Entite.Customer;
import Report.Services.ReportService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerReportsManegment {
    private final ReportService reportService;
    CustomerReportsMenu customerReportsMenu=new CustomerReportsMenu();
    private final Scanner scanner = new Scanner(System.in);

    public CustomerReportsManegment(ReportService reportService ) {
        this.reportService=reportService;
    }

    public void Start() {
        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {
            try {
                customerReportsMenu.displayMenu();

                System.out.print("Enter Your Choice: ");
                int choice = scanner.nextInt();

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
                        System.out.println(" Customer Bookings");
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



    // ================= CUSTOMER REPORTS =================

    private void displayAllCustomersReport() {
        List<Customer> customers = reportService.getAllCustomersReport();

        if (customers.isEmpty()) {
            System.out.println("There are no customers in the system");
            return;
        }

        customers.forEach(System.out::println);
    }

    private void getCustomerByIdReport() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();

        Customer customer = reportService.getCustomerByIdReport(customerId);

        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }

        System.out.println(customer);
    }

    private void getCustomerBookingsReport() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();

        List<Booking> bookings =
                reportService.getCustomerBookingsReport(customerId);

        if (bookings.isEmpty()) {
            System.out.println("This customer has no bookings");
            return;
        }

        bookings.forEach(System.out::println);
    }

    private void displayCustomersWithBookingsReport() {
        List<Customer> customers =
                reportService.getCustomersWithBookingsReport();

        if (customers.isEmpty()) {
            System.out.println("There are no customers with bookings");
            return;
        }

        customers.forEach(System.out::println);
    }

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
