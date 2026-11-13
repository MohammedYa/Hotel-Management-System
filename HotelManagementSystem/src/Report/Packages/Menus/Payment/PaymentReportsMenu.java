package Report.Packages.Menus.Payment;

import java.util.InputMismatchException;

public class PaymentReportsMenu {

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