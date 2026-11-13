
package Rooms.pakages;

import Rooms.Enums.RoomStatus;
import Rooms.Enums.RoomTybe;
import Rooms.entite.Room;
import Rooms.service.RoomService;
import Validations.ValidationUtils;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Handles user interactions related to room management.
 *
 * Provides operations for adding, updating, searching, displaying,
 * deleting rooms, and changing room statuses.
 */
public class ManagementRooms {

    private final RoomMenu roomMenu = new RoomMenu();
    private final RoomService roomService;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs ManagementRooms with the required service.
     *
     * @param roomService service responsible for room operations
     * @throws IllegalArgumentException if roomService is null
     */
    public ManagementRooms(RoomService roomService) {
        ValidationUtils.requireNotNull(roomService, "Room Service");
        this.roomService = roomService;
    }

    /**
     * Starts the room management menu.
     *
     * Allows the user three invalid menu attempts before exiting.
     */
    public void start() {

        int countOfChoice = 0;
        boolean flag = true;

        while (flag) {

            roomMenu.displayMenu();

            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {

                countOfChoice++;

                System.out.println("Invalid input. Please enter a number.");
                System.out.println(
                        "You have " + (3 - countOfChoice) + " chances left."
                );

                if (countOfChoice >= 3) {
                    System.out.println("Too many invalid attempts. Goodbye!");
                    flag = false;
                }

                continue;
            }

            switch (choice) {

                case 0:
                    countOfChoice = 0;
                    System.out.println("Back to Main Menu............ \n");
                    flag = false;
                    break;

                case 1:
                    countOfChoice = 0;
                    addRoom();
                    break;

                case 2:
                    countOfChoice = 0;
                    updateRoom();
                    break;

                case 3:
                    countOfChoice = 0;
                    searchRoom();
                    break;

                case 4:
                    countOfChoice = 0;
                    displayAllRooms();
                    break;

                case 5:
                    countOfChoice = 0;
                    changeRoomStatus();
                    break;

                case 6:
                    countOfChoice = 0;
                    deleteRoom();
                    break;

                default:
                    countOfChoice++;

                    System.out.println(
                            "Invalid choice. Enter a number between 0 and 6."
                    );
                    System.out.println(
                            "You have " + (3 - countOfChoice) + " chances left."
                    );

                    if (countOfChoice >= 3) {
                        System.out.println("Too many invalid attempts. Goodbye!");
                        flag = false;
                    }
            }
        }
    }

    /**
     * Reads an integer from the user.
     *
     * Repeats until the user enters a valid integer.
     *
     * @param message prompt displayed to the user
     * @return integer entered by the user
     */
    private int readInt(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }

    /**
     * Reads a positive integer from the user.
     *
     * @param message prompt displayed to the user
     * @return positive integer entered by the user
     */
    private int readPositiveInt(String message) {

        while (true) {

            int value = readInt(message);

            if (value > 0) {
                return value;
            }

            System.out.println("Value must be greater than zero!");
        }
    }

    /**
     * Reads a positive monetary amount from the user.
     *
     * @param message prompt displayed to the user
     * @return positive BigDecimal value
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

                System.out.println("Room price must be greater than zero!");

            } catch (NumberFormatException e) {
                System.out.println("Invalid price! Please enter a valid amount.");
            }
        }
    }

    /**
     * Reads the room type selected by the user.
     *
     * Allows three invalid attempts before returning null.
     *
     * @return selected RoomTybe or null if attempts are exhausted
     */
    private RoomTybe readRoomType() {

        int countOfChoice = 0;

        while (countOfChoice < 3) {

            System.out.print(
                    "Enter Room Type: 1 -> SINGLE, 2 -> DOUBLE, 3 -> SUITE: "
            );

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                countOfChoice++;
                System.out.println("Invalid input. Please enter a number.");
                System.out.println(
                        "You have " + (3 - countOfChoice) + " chances left."
                );
                continue;
            }

            switch (choice) {
                case 1:
                    return RoomTybe.SINGLE;
                case 2:
                    return RoomTybe.DOUBLE;
                case 3:
                    return RoomTybe.SUITE;
                default:
                    countOfChoice++;
                    System.out.println("Invalid choice.");
                    System.out.println(
                            "You have " + (3 - countOfChoice) + " chances left."
                    );
            }
        }

        System.out.println("Too many invalid attempts.");
        return null;
    }

    /**
     * Reads the room status selected by the user.
     *
     * Allows three invalid attempts before returning null.
     *
     * @return selected RoomStatus or null if attempts are exhausted
     */
    private RoomStatus readRoomStatus() {

        int countOfChoice = 0;

        while (countOfChoice < 3) {

            System.out.print(
                    "Enter Room Status: 1 -> AVAILABLE, 2 -> OCCUPIED, " +
                            "3 -> MAINTENANCE, 4 -> CLEANING: "
            );

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                countOfChoice++;
                System.out.println("Invalid input. Please enter a number.");
                System.out.println(
                        "You have " + (3 - countOfChoice) + " chances left."
                );
                continue;
            }

            switch (choice) {
                case 1:
                    return RoomStatus.AVAILABLE;
                case 2:
                    return RoomStatus.OCCUPIED;
                case 3:
                    return RoomStatus.MAINTENANCE;
                case 4:
                    return RoomStatus.CLEANING;
                default:
                    countOfChoice++;
                    System.out.println("Invalid choice.");
                    System.out.println(
                            "You have " + (3 - countOfChoice) + " chances left."
                    );
            }
        }

        System.out.println("Too many invalid attempts.");
        return null;
    }

    /**
     * Adds a new room using the information entered by the user.
     *
     * Stops the operation if room type or status selection fails.
     */
    private void addRoom() {

        System.out.println("\nAdd Room Page\n");

        int roomNumber = readPositiveInt("Enter the Room Number: ");

        RoomTybe roomTybe = readRoomType();

        if (roomTybe == null) {
            return;
        }

        RoomStatus roomStatus = readRoomStatus();

        if (roomStatus == null) {
            return;
        }

        BigDecimal roomPrice = readPositiveAmount("Enter Room Price: ");

        int roomCapacity = readPositiveInt("Enter the Room Capacity: ");

        Room room = new Room(
                roomNumber,
                roomTybe,
                roomStatus,
                roomPrice,
                roomCapacity
        );

        if (roomService.addRoom(room)) {
            System.out.println("\nRoom Created Successfully!\n");
        } else {
            System.out.println("Room Number Already Exists!");
        }
    }

    /**
     * Displays all rooms.
     *
     * Displays a message if no rooms are available.
     */
    private void displayAllRooms() {

        System.out.println("\nDisplay All Rooms Page\n");

        if (roomService.getAllRooms().isEmpty()) {
            System.out.println("No Rooms Found");
            return;
        }

        roomService.getAllRooms().forEach(System.out::println);
    }

    /**
     * Searches for a room using its room number.
     */
    private void searchRoom() {

        System.out.println("\nSearch Room Page\n");

        int roomNumber = readPositiveInt("Enter Room Number: ");

        Room room = roomService.searchRoom(roomNumber);

        if (room != null) {
            System.out.println(room);
        } else {
            System.out.println("Room Not Found!");
        }
    }

    /**
     * Deletes a room using its room number.
     */
    private void deleteRoom() {

        System.out.println("\nDelete Room Page\n");

        int roomNumber = readPositiveInt("Enter Room Number: ");

        if (roomService.deleteRoom(roomNumber)) {
            System.out.println("Room Deleted Successfully");
        } else {
            System.out.println("Room Not Found");
        }
    }

    /**
     * Updates an existing room.
     *
     * Searches for the room first, then collects its updated information.
     */
    private void updateRoom() {

        System.out.println("\nUpdate Room Page");

        int roomNumber = readPositiveInt("Enter Room Number: ");

        Room existingRoom = roomService.searchRoom(roomNumber);

        if (existingRoom == null) {
            System.out.println("Room Not Found!");
            return;
        }

        RoomTybe roomTybe = readRoomType();

        if (roomTybe == null) {
            return;
        }

        RoomStatus roomStatus = readRoomStatus();

        if (roomStatus == null) {
            return;
        }

        BigDecimal roomPrice = readPositiveAmount("Enter Room Price: ");

        int roomCapacity = readPositiveInt("Enter Room Capacity: ");

        Room updatedRoom = new Room(
                roomNumber,
                roomTybe,
                roomStatus,
                roomPrice,
                roomCapacity
        );

        if (roomService.updateRoom(updatedRoom)) {
            System.out.println("Room Updated Successfully");
        } else {
            System.out.println("Room Not Found");
        }
    }

    /**
     * Changes the status of an existing room.
     *
     * Searches for the room before requesting its new status.
     */
    private void changeRoomStatus() {

        System.out.println("\nChange Room Status Page");

        int roomNumber = readPositiveInt("Enter Room Number: ");

        Room existingRoom = roomService.searchRoom(roomNumber);

        if (existingRoom == null) {
            System.out.println("Room Not Found!");
            return;
        }

        RoomStatus roomStatus = readRoomStatus();

        if (roomStatus == null) {
            return;
        }

        if (roomService.changeRoomStatus(roomNumber, roomStatus)) {
            System.out.println("Room Status Updated Successfully");
        } else {
            System.out.println("Room Not Found");
        }
    }
}
