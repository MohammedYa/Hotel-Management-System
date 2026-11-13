package Report.Packages.Menus;

/**
 * Displays the main report management menu and allows users
 * to navigate to the different report categories.
 */
public class ReportsMenu {

    /**
     * Prints the report management menu to the console,
     * including options for room, customer, booking, and payment reports.
     */
    public void displayMenu() {
        System.out.println("\n========== REPORT MANAGEMENT ==========");
        System.out.println("1. Room Reports");
        System.out.println("2. Customer Reports");
        System.out.println("3. Booking Reports");
        System.out.println("4. Payment Reports");
        System.out.println("0. Back");
        System.out.println("=======================================");
        System.out.print("Enter your choice: ");
    }
}