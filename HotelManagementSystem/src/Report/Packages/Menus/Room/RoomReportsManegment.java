
package Report.Packages.Menus.Room;

import Report.Services.ReportService;
import Rooms.Enums.RoomStatus;
import Rooms.Enums.RoomTybe;
import Rooms.entite.Room;
import Validations.ValidationUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Handles user interactions related to room reports.
 *
 * Provides reports for all rooms, room status, room type,
 * room capacity, and room price range.
 */
public class RoomReportsManegment {

    private final RoomReportsMenu roomReportsMenu =
            new RoomReportsMenu();

    private final Scanner scanner = new Scanner(System.in);

    private final ReportService reportService;

    /**
     * Constructs RoomReportsManegment with the required service.
     *
     * @param reportService service responsible for generating reports
     * @throws IllegalArgumentException if reportService is null
     */
    public RoomReportsManegment(ReportService reportService) {

        ValidationUtils.requireNotNull(reportService, "Report Service");

        this.reportService = reportService;
    }

    /**
     * Starts the room reports menu.
     *
     * Allows the user three invalid menu attempts before exiting.
     */
    public void Start() {

        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {

            roomReportsMenu.displayMenu();

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
                    displayAllRoomsReport();
                    break;

                case 2:
                    tryChoiceCount = 0;
                    System.out.println("All AVAILABLE Rooms");
                    getRoomsByStatusReport(RoomStatus.AVAILABLE);
                    break;

                case 3:
                    tryChoiceCount = 0;
                    System.out.println("All OCCUPIED Rooms");
                    getRoomsByStatusReport(RoomStatus.OCCUPIED);
                    break;

                case 4:
                    tryChoiceCount = 0;
                    System.out.println("All MAINTENANCE Rooms");
                    getRoomsByStatusReport(RoomStatus.MAINTENANCE);
                    break;

                case 5:
                    tryChoiceCount = 0;
                    System.out.println("All CLEANING Rooms");
                    getRoomsByStatusReport(RoomStatus.CLEANING);
                    break;

                case 6:
                    tryChoiceCount = 0;
                    System.out.println("All SINGLE Rooms");
                    getRoomsByTypeReport(RoomTybe.SINGLE);
                    break;

                case 7:
                    tryChoiceCount = 0;
                    System.out.println("All DOUBLE Rooms");
                    getRoomsByTypeReport(RoomTybe.DOUBLE);
                    break;

                case 8:
                    tryChoiceCount = 0;
                    System.out.println("All SUITE Rooms");
                    getRoomsByTypeReport(RoomTybe.SUITE);
                    break;

                case 9:
                    tryChoiceCount = 0;
                    System.out.println("All Rooms By Capacity");
                    getRoomsByCapacityReport();
                    break;

                case 10:
                    tryChoiceCount = 0;
                    System.out.println("Rooms By Price Range");
                    getRoomsByPriceRangeReport();
                    break;

                default:
                    tryChoiceCount++;

                    System.out.println(
                            "Invalid Choice! Choose a number between 0-10."
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

    /**
     * Reads a positive integer from the user.
     *
     * Repeats until a valid positive integer is entered.
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

                System.out.println("Value must be greater than zero!");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input! Please enter a valid integer."
                );
            }
        }
    }

    /**
     * Reads a positive monetary amount from the user.
     *
     * Repeats until a valid positive BigDecimal is entered.
     *
     * @param message prompt displayed to the user
     * @return positive monetary amount
     */
    private BigDecimal readPositiveAmount(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {
                BigDecimal amount = new BigDecimal(input.trim());

                if (amount.compareTo(BigDecimal.ZERO) > 0) {
                    return amount;
                }

                System.out.println("Amount must be greater than zero!");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid amount! Please enter a valid number."
                );
            }
        }
    }

    /**
     * Displays all rooms registered in the system.
     */
    private void displayAllRoomsReport() {

        List<Room> allRoomsReport =
                reportService.getAllRoomsReport();

        if (allRoomsReport.isEmpty()) {
            System.out.println("There are no rooms in the system");
            return;
        }

        allRoomsReport.forEach(System.out::println);
    }

    /**
     * Displays rooms filtered by their current status.
     *
     * @param roomStatus room status used as the report filter
     */
    private void getRoomsByStatusReport(RoomStatus roomStatus) {

        List<Room> rooms =
                reportService.getRoomsByStatusReport(roomStatus);

        if (rooms.isEmpty()) {
            System.out.println("There are no rooms in the system");
            return;
        }

        rooms.forEach(System.out::println);
    }

    /**
     * Displays rooms filtered by their type.
     *
     * @param roomTybe room type used as the report filter
     */
    private void getRoomsByTypeReport(RoomTybe roomTybe) {

        List<Room> rooms =
                reportService.getRoomsByTypeReport(roomTybe);

        if (rooms.isEmpty()) {
            System.out.println("There are no rooms in the system");
            return;
        }

        rooms.forEach(System.out::println);
    }

    /**
     * Displays rooms that match the specified capacity.
     */
    private void getRoomsByCapacityReport() {

        int capacity =
                readPositiveInt("Enter Room Capacity: ");

        List<Room> rooms =
                reportService.getRoomsByCapacityReport(capacity);

        if (rooms.isEmpty()) {
            System.out.println("There are no rooms in the system");
            return;
        }

        rooms.forEach(System.out::println);
    }

    /**
     * Displays rooms whose prices fall within the specified range.
     *
     * Validates that the minimum price does not exceed the maximum price.
     */
    private void getRoomsByPriceRangeReport() {

        BigDecimal minPrice =
                readPositiveAmount("Enter Minimum Price: ");

        BigDecimal maxPrice =
                readPositiveAmount("Enter Maximum Price: ");

        if (minPrice.compareTo(maxPrice) > 0) {
            System.out.println(
                    "Minimum price cannot be greater than maximum price."
            );
            return;
        }

        List<Room> rooms =
                reportService.getRoomsByPriceRangeReport(
                        minPrice,
                        maxPrice
                );

        if (rooms.isEmpty()) {
            System.out.println("There are no rooms in the system");
            return;
        }

        rooms.forEach(System.out::println);
    }
}
