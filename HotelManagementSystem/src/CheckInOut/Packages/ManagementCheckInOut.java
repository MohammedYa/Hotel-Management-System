package CheckInOut.Packages;

import CheckInOut.Services.CheckInOutService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagementCheckInOut {
    CheckInOutMenu checkInOutMenu = new CheckInOutMenu();
    private  final CheckInOutService checkInOutService;
    Scanner scanner = new Scanner(System.in);


    public ManagementCheckInOut(CheckInOutService checkInOutService) {
        this.checkInOutService = checkInOutService;
    }
    public  void Start(){
        boolean  flag=true;
        int tryChoiceCount=0;
        while (flag) {
            try {
                checkInOutMenu.displayMenu();
                System.out.print("  Enter Your Choice: ");
                int choice = scanner.nextInt();
                switch(choice){
                    case 0:
                        flag=false;
                        break;
                    case 1:
                        tryChoiceCount=0;
                        checkIn();
                        break;
                    case 2:
                        tryChoiceCount=0;
                        checkOut();
                        break;

                    default:
                        System.out.println("Invalid Choice You have Chosen  Number between 0-2 ");
                        System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");
                        tryChoiceCount++;
                        if (tryChoiceCount >=3) {

                            System.out.println("Invalid Choice Try again later ");
                            flag=false;

                        }

                }
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                if (tryChoiceCount>=3){
                    System.out.println("Invalid Choice try again later");
                    flag=false;
                }
                else{
                    System.out.println("You have entered the correct choice 0-2");
                    tryChoiceCount++;
                    System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");

                }
            }

        }

    }


    public  void checkIn(){
        System.out.println("Checking In Booking Page:");
        System.out.println("  Enter Booking Number:     ");
        int bookingNumber = scanner.nextInt();

        if (checkInOutService.checkIn(bookingNumber)){
            System.out.println("Checking In Booking Successfully");
        }
        else{
            System.out.println("Checking In Booking Failed");
        }
    }
    public  void checkOut(){
        System.out.println("Checking Out Booking Page:");
        System.out.println("  Enter Booking Number:     ");
        int bookingNumber = scanner.nextInt();

        if (checkInOutService.checkOut(bookingNumber)){
            System.out.println("Checking Out Booking Successfully");
        }
        else{
            System.out.println("Checking Out Booking Failed");
        }
    }
}
