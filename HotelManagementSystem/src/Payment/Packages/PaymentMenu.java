package Payment.Packages;

/**
 * Displays the menu options available for payment management.
 */
public class PaymentMenu {

    /**
     * Prints the payment management menu to the console,
     * including options for adding payments, searching payments,
     * displaying all payments, viewing payments by booking,
     * and displaying a booking payment summary.
     */
    public void displayMenu() {

        System.out.println("\n");
        System.out.println("==========================================");
        System.out.println("          HOTEL MANAGEMENT SYSTEM         ");
        System.out.println("             Payment Page                ");
        System.out.println("==========================================");
        System.out.println("  1. Add Payment");
        System.out.println("  2. Search Payment");
        System.out.println("  3. Display All Payments");
        System.out.println("  4. Display Payments By Booking");
        System.out.println("  5.Display Booking Payment Summary");

        System.out.println("------------------------------------------");
        System.out.println("  0. Back To Main Menu");
        System.out.println("==========================================");

    }
}