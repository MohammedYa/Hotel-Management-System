import Rooms.pakages.ManagementRooms;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelSystem {
ManagementRooms  managementRooms  = new ManagementRooms();
Scanner scanner =new Scanner(System.in);
    public void Start(){
        int countOfChoice=0;
        boolean flag=true;
        while (flag){
            try {
                System.out.print("\n========== HOTEL MANAGEMENT SYSTEM ==========\n" +
                        "\n" +
                        "1 -----> Room Management\n" +
                        "2 -----> Customer Management\n" +
                        "3 -----> Booking Management\n" +
                        "4 -----> Check-in / Check-out\n" +
                        "5 -----> Payment Management\n" +
                        "6 -----> Reports\n" +
                        "0 -----> Exit\n"  );
                System.out.print("Choose an option:");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        countOfChoice=0;
                        System.out.println("Thank you for your time see you later ............ \n");
                        flag=false;
                        break;

                    case 1:
                        countOfChoice=0;
                        managementRooms.start();
                        break;
                    case 2:
                        countOfChoice=0;
                        System.out.print("\n========== Customer Management ==========\n");
                        break;
                    case 3:
                        countOfChoice=0;
                        System.out.print("\n========== Booking Management ==========\n");
                        break;
                    case 4:
                        countOfChoice=0;
                        System.out.print("\n========== Check-in / Check-out ==========\n");
                        break;
                    case 5:
                        countOfChoice=0;
                        System.out.print("\n========== Payment Management ==========\n");
                        break;
                    case 6:
                        countOfChoice=0;
                        System.out.print("\n========== Report Management ==========\n");
                        break;
                    default:
                        countOfChoice++;

                        if (countOfChoice >= 3) {
                            System.out.println("Too many invalid attempts. Goodbye!");
                            flag = false;
                        } else {
                            System.out.println("Invalid choice. Enter a number between 0 and 6.");
                            System.out.println("You have " + (3 - countOfChoice) + " chances left.");
                        }

                }
            }
            catch (InputMismatchException e) {
                scanner.nextLine(); // Remove invalid input

                countOfChoice++;

                if (countOfChoice >= 3) {
                    System.out.println("Too many invalid attempts. Goodbye!");
                    flag = false;
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    System.out.println("You have " + (3 - countOfChoice) + " chances left.");
                }
            }
        }



    }
}
