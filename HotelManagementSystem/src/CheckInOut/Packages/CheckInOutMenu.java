package CheckInOut.Packages;

/**
 * Displays the menu options for managing booking check-in and check-out operations.
 */
public class CheckInOutMenu {

    /**
     * Prints the check-in and check-out menu to the console,
     * allowing users to check in bookings, check out bookings,
     * or return to the main menu.
     */
    public void displayMenu() {
        System.out.println("\n");
        System.out.println("==========================================");
        System.out.println("          HOTEL MANAGEMENT SYSTEM         ");
        System.out.println("          check ( In && Out ) MENU     ");
        System.out.println("==========================================");
        System.out.println("\n  1. check In Booking");
        System.out.println("  2. check Out Bookings");

        System.out.println("------------------------------------------");
        System.out.println("  0. Back To Main Menu");
        System.out.println("==========================================");

    }
}