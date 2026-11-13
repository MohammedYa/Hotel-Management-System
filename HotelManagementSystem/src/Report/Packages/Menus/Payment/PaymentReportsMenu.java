package Report.Packages.Menus.Payment;

import java.util.InputMismatchException;

/**
 * Displays the available payment reports and allows users
 * to select a specific report from the console menu.
 */
public class PaymentReportsMenu {

    /**
     * Prints the payment reports menu to the console,
     * including options to display all payments, filter payments
     * by booking ID, transaction status, transaction type,
     * or date range.
     */
    public void displayMenu() {
        System.out.println("\n========== PAYMENT REPORTS ==========");
        System.out.println("1. All Payments");
        System.out.println("2. Payments By Booking ID");
        System.out.println("3. SUCCESS Payments ");
        System.out.println("4. FAILED Payments ");
        System.out.println("5. PAYMENT Payments");
        System.out.println("6. REFUND Payments");
        System.out.println("7. Payments By Date Range");
        System.out.println("0. Back");
        System.out.println("=====================================");
        System.out.print("Enter your choice: ");
    }

}