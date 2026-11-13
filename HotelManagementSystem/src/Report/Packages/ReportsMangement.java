
package Report.Packages;

import Report.Packages.Menus.*;
import Report.Packages.Menus.Booking.BookingsReportsManegment;
import Report.Packages.Menus.Customer.CustomerReportsManegment;
import Report.Packages.Menus.Payment.PaymentReportsManegment;
import Report.Packages.Menus.Room.RoomReportsManegment;
import Validations.ValidationUtils;

import java.util.Scanner;

/**
 * Main management class for the reports module.
 *
 * Provides access to room, customer, booking, and payment reports.
 */
public class ReportsMangement {

   private final BookingsReportsManegment bookingsMangment;
   private final PaymentReportsManegment paymentMangment;
   private final CustomerReportsManegment customerMangment;
   private final RoomReportsManegment roomReportsManegment;

   private final ReportsMenu reportsMenu = new ReportsMenu();

   private final Scanner scanner = new Scanner(System.in);

   /**
    * Constructs ReportsMangement with all required report managers.
    *
    * @param bookingsMangment manager responsible for booking reports
    * @param paymentMangment manager responsible for payment reports
    * @param customerMangment manager responsible for customer reports
    * @param roomReportsManegment manager responsible for room reports
    * @throws IllegalArgumentException if any dependency is null
    */
   public ReportsMangement(
           BookingsReportsManegment bookingsMangment,
           PaymentReportsManegment paymentMangment,
           CustomerReportsManegment customerMangment,
           RoomReportsManegment roomReportsManegment
   ) {

      ValidationUtils.requireNotNull(
              bookingsMangment, "Bookings Reports Management"
      );

      ValidationUtils.requireNotNull(
              paymentMangment, "Payment Reports Management"
      );

      ValidationUtils.requireNotNull(
              customerMangment, "Customer Reports Management"
      );

      ValidationUtils.requireNotNull(
              roomReportsManegment, "Room Reports Management"
      );

      this.bookingsMangment = bookingsMangment;
      this.paymentMangment = paymentMangment;
      this.customerMangment = customerMangment;
      this.roomReportsManegment = roomReportsManegment;
   }

   /**
    * Starts the main reports menu.
    *
    * Allows the user three invalid menu attempts before exiting.
    */
   public void Start() {

      boolean flag = true;
      int tryChoiceCount = 0;

      while (flag) {

         reportsMenu.displayMenu();

         System.out.print("Enter Your Choice: ");

         String input = scanner.nextLine();

         int choice;

         try {
            choice = Integer.parseInt(input.trim());

         } catch (NumberFormatException e) {

            tryChoiceCount++;

            System.out.println(
                    "Invalid input! Please enter a number."
            );

            System.out.println(
                    "You have " + (3 - tryChoiceCount) + " chances left."
            );

            if (tryChoiceCount >= 3) {
               System.out.println(
                       "Too many invalid attempts. Try again later."
               );
               flag = false;
            }

            continue;
         }

         switch (choice) {

            case 0:
               flag = false;
               break;

            case 1:
               tryChoiceCount = 0;
               roomReportsManegment.Start();
               break;

            case 2:
               tryChoiceCount = 0;
               customerMangment.Start();
               break;

            case 3:
               tryChoiceCount = 0;
               bookingsMangment.Start();
               break;

            case 4:
               tryChoiceCount = 0;
               paymentMangment.Start();
               break;

            default:
               tryChoiceCount++;

               System.out.println(
                       "Invalid Choice! Choose a number between 0-4."
               );

               System.out.println(
                       "You have " + (3 - tryChoiceCount) + " chances left."
               );

               if (tryChoiceCount >= 3) {
                  System.out.println(
                          "Too many invalid attempts. Try again later."
                  );
                  flag = false;
               }
         }
      }
   }
}
