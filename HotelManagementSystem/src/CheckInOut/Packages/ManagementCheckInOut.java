
package CheckInOut.Packages;

import CheckInOut.Services.CheckInOutService;
import Validations.ValidationUtils;

import java.util.Scanner;

/**
 * Handles user interactions for check-in and check-out operations.
 *
 * Provides a menu for checking guests into and out of their bookings.
 */
public class ManagementCheckInOut {

    private final CheckInOutMenu checkInOutMenu = new CheckInOutMenu();
    private final CheckInOutService checkInOutService;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs ManagementCheckInOut with the required service.
     *
     * @param checkInOutService service responsible for check-in and check-out
     * @throws IllegalArgumentException if the service is null
     */
    public ManagementCheckInOut(CheckInOutService checkInOutService) {

        ValidationUtils.requireNotNull(
                checkInOutService,
                "Check In Out Service"
        );

        this.checkInOutService = checkInOutService;
    }

    /**
     * Starts the check-in/check-out management menu.
     *
     * Allows the user three invalid menu attempts before exiting.
     */
    public void Start() {

        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {

            checkInOutMenu.displayMenu();
            System.out.print("Enter Your Choice: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {

                tryChoiceCount++;

                System.out.println(
                        "Invalid input! Please enter a number between 0-2."
                );
                System.out.println(
                        "You have " + (3 - tryChoiceCount) + " chances left."
                );

                if (tryChoiceCount >= 3) {
                    System.out.println("Invalid Choice. Try again later.");
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
                    checkIn();
                    break;

                case 2:
                    tryChoiceCount = 0;
                    checkOut();
                    break;

                default:
                    tryChoiceCount++;

                    System.out.println(
                            "Invalid Choice! Enter a number between 0-2."
                    );
                    System.out.println(
                            "You have " + (3 - tryChoiceCount) + " chances left."
                    );

                    if (tryChoiceCount >= 3) {
                        System.out.println("Invalid Choice. Try again later.");
                        flag = false;
                    }
            }
        }
    }

    /**
     * Reads a positive integer from the user.
     *
     * Repeats the input operation until a valid positive integer is entered.
     *
     * @param message prompt displayed to the user
     * @return positive integer entered by the user
     */
    private int readPositiveInt(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                int value = Integer.parseInt(input.trim());

                if (value > 0) {
                    return value;
                }

                System.out.println("Booking Number must be greater than zero!");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Checks a guest into an existing booking.
     *
     * Requests the booking number and delegates the operation
     * to CheckInOutService.
     */
    public void checkIn() {

        System.out.println("Checking In Booking Page:");

        int bookingNumber = readPositiveInt("Enter Booking Number: ");

        if (checkInOutService.checkIn(bookingNumber)) {
            System.out.println("Checking In Booking Successfully!");
        } else {
            System.out.println("Checking In Booking Failed!");
        }
    }

    /**
     * Checks a guest out of an existing booking.
     *
     * Requests the booking number and delegates the operation
     * to CheckInOutService.
     */
    public void checkOut() {

        System.out.println("Checking Out Booking Page:");

        int bookingNumber = readPositiveInt("Enter Booking Number: ");

        if (checkInOutService.checkOut(bookingNumber)) {
            System.out.println("Checking Out Booking Successfully!");
        } else {
            System.out.println("Checking Out Booking Failed!");
        }
    }
}
