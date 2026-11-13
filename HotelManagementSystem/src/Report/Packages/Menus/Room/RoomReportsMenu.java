package Report.Packages.Menus.Room;

import java.util.InputMismatchException;

/**
 * Displays the available room reports and allows users
 * to select a specific report from the console menu.
 */
public class RoomReportsMenu {

    /**
     * Prints the room reports menu to the console,
     * including options to display all rooms, filter rooms
     * by status, room type, capacity, or price range.
     */
    public void displayMenu() {
        System.out.println("\n========== ROOM REPORTS ==========");
        System.out.println("1. All Rooms");

        System.out.println("2.All AVAILABLE Rooms ");
        System.out.println("3.All OCCUPIED Rooms ");
        System.out.println("4.All MAINTENANCE Rooms ");
        System.out.println("5.All CLEANING Rooms ");

        System.out.println("6.All SINGLE Rooms ");
        System.out.println("7.All DOUBLE Rooms ");
        System.out.println("8.All SUITE Rooms ");

        System.out.println("9. Rooms By Capacity");
        System.out.println("10. Rooms By Price Range");
        System.out.println("0. Back");
        System.out.println("==================================");
        System.out.print("Enter your choice: ");
    }

}