package Booking.Packages;

/**
 * Displays the menu options available for booking management.
 */
public class BookingMenu {

    /**
     * Prints the booking management menu to the console,
     * including options for adding, displaying, searching,
     * updating, and cancelling bookings.
     */
    public void displayMenu() {

        System.out.println("\n");
        System.out.println("==========================================");
        System.out.println("          HOTEL MANAGEMENT SYSTEM         ");
        System.out.println("             Booking MENU                ");
        System.out.println("==========================================");
        System.out.println("  1. Add Booking");
        System.out.println("  2. Display All Bookings");
        System.out.println("  3. Search Booking By ID");
        System.out.println("  4. Update Booking");
        System.out.println("  5. Cancel Booking");
        System.out.println("------------------------------------------");
        System.out.println("  0. Back To Main Menu");
        System.out.println("==========================================");

    }
}