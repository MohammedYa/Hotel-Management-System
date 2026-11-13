package Report.Packages.Menus.Booking;

import java.util.InputMismatchException;

/**
 * Displays the available booking reports and allows users
 * to select a specific report from the console menu.
 */
public class BookingReportsMenu {

    /**
     * Prints the booking reports menu to the console,
     * including options to display all bookings, filter bookings
     * by status, customer ID, room number, or date range.
     */
    public void displayMenu() {
        System.out.println("\n========== BOOKING REPORTS ==========");
        System.out.println("1. All Bookings");

        System.out.println("2. CONFIRMED Bookings");
        System.out.println("3. CANCELLED Bookings");
        System.out.println("4. COMPLETED Bookings");
        System.out.println("5. NO_SHOW Bookings");

        System.out.println("6. Bookings By Customer ID");
        System.out.println("7. Bookings By Room Number");
        System.out.println("8. Bookings By Date Range");
        System.out.println("0. Back");
        System.out.println("=====================================");
        System.out.print("Enter your choice: ");
    }

}