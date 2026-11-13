package Report.Packages;

import Report.Packages.Menus.*;
import Report.Packages.Menus.Booking.BookingsReportsManegment;
import Report.Packages.Menus.Customer.CustomerReportsManegment;
import Report.Packages.Menus.Payment.PaymentReportsManegment;
import Report.Packages.Menus.Room.RoomReportsManegment;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReportsMangement {

   private  final BookingsReportsManegment bookingsMangment;
   private  final PaymentReportsManegment paymentMangment;
   private  final CustomerReportsManegment customerMangment;
   private  final RoomReportsManegment roomReportsManegment;

   ReportsMenu  reportsMenu=new ReportsMenu();
   private final Scanner scanner = new Scanner(System.in);
  public ReportsMangement(
           BookingsReportsManegment bookingsMangment,
           PaymentReportsManegment paymentMangment,
           CustomerReportsManegment customerMangment,
           RoomReportsManegment roomReportsManegment
   ) {
      this.bookingsMangment = bookingsMangment;
      this.paymentMangment = paymentMangment;
      this.customerMangment = customerMangment;
      this.roomReportsManegment = roomReportsManegment;
   }

   public void Start() {
      boolean flag = true;
      int tryChoiceCount = 0;

      while (flag) {
         try {
            reportsMenu.displayMenu();

            System.out.print("Enter Your Choice: ");
            int choice = scanner.nextInt();

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
                  System.out.println("Invalid Choice! Choose a number between 0-4.");
                  System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");

                  if (tryChoiceCount >= 3) {
                     System.out.println("Too many invalid attempts. Try again later.");
                     flag = false;
                  }
            }

         } catch (InputMismatchException e) {
            scanner.nextLine();

            tryChoiceCount++;

            System.out.println("Invalid input! Please enter a number.");
            System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");

            if (tryChoiceCount >= 3) {
               System.out.println("Too many invalid attempts. Try again later.");
               flag = false;
            }
         }
      }
   }










}
