package Report.Packages.Menus.Customer;

import java.util.InputMismatchException;

/**
 * Displays the available customer reports and allows users
 * to select a specific report from the console menu.
 */
public class CustomerReportsMenu {

    /**
     * Prints the customer reports menu to the console,
     * including options to display all customers, search for a customer
     * by ID, view customer bookings, and filter customers
     * based on whether they have bookings.
     */
    public void displayMenu() {
        System.out.println("\n========== CUSTOMER REPORTS ==========");
        System.out.println("1. All Customers");
        System.out.println("2. Customer By ID");
        System.out.println("3. Customer Bookings");
        System.out.println("4. Customers With Bookings");
        System.out.println("5. Customers Without Bookings");
        System.out.println("0. Back");
        System.out.println("======================================");
        System.out.print("Enter your choice: ");
    }

}