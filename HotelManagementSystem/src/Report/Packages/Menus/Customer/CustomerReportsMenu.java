package Report.Packages.Menus.Customer;

import java.util.InputMismatchException;

public class CustomerReportsMenu {

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