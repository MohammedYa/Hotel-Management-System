package Rooms.pakages;

import Rooms.Enums.RoomStatus;
import Rooms.Enums.RoomTybe;
import Rooms.entite.Room;
import Rooms.service.RoomServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagementRooms {
RoomMenu  roomMenu = new RoomMenu();
RoomServiceImpl  roomService = new RoomServiceImpl();
Scanner scanner = new Scanner(System.in);

    public void start(){

        int countOfChoice=0;
        boolean flag=true;
        while (flag){
            try {
                roomMenu.displayMenu();
                System.out.print("Enter your choice:     ");
                int choice =scanner.nextInt();

                switch (choice) {
                    case 0:
                        countOfChoice=0;
                        System.out.println("Back to Main Menu............ \n");
                        flag=false;
                        break;

                    case 1:
                        countOfChoice=0;
                        addRoom();
                        break;
                    case 2:
                        countOfChoice=0;
                        updateRoom();

                        break;
                    case 3:
                        countOfChoice=0;
                        searchRoom();
                        break;
                    case 4:
                        countOfChoice=0;
                        roomService.displayAllRooms();
                        break;
                    case 5:
                        countOfChoice=0;
                        changeRoomStatus();
                        break;
                    case 6:
                        countOfChoice=0;
                        deleteRoom();

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





    private void addRoom() {
        System.out.print("\nAdd Room Page\n");

        System.out.print("Enter the Room Number: ");
        int roomNumber = scanner.nextInt();

        int countOfChoice = 0;
        boolean flag = true;

        RoomTybe roomTybe = null;

        // Select Room Type
        while (flag) {
            try {
                System.out.print(
                        "Enter Room Type: 1 -> SINGLE, 2 -> DOUBLE, 3 -> SUITE: "
                );

                int roomTypeNum = scanner.nextInt();

                switch (roomTypeNum) {
                    case 1:
                        roomTybe = RoomTybe.SINGLE;
                        flag = false;
                        break;

                    case 2:
                        roomTybe = RoomTybe.DOUBLE;
                        flag = false;
                        break;

                    case 3:
                        roomTybe = RoomTybe.SUITE;
                        flag = false;
                        break;

                    default:
                        countOfChoice++;

                        if (countOfChoice >= 3) {
                            System.out.println("Too many invalid attempts.");
                            return;
                        }

                        System.out.println("Invalid choice.");
                        System.out.println("You have " + (3 - countOfChoice) + " chances left.");
                }

            } catch (InputMismatchException e) {
                scanner.nextLine();
                countOfChoice++;

                if (countOfChoice >= 3) {
                    System.out.println("Too many invalid attempts.");
                    return;
                }

                System.out.println("Invalid input. Please enter a number.");
                System.out.println("You have " + (3 - countOfChoice) + " chances left.");
            }
        }

        // Select Room Status
        countOfChoice = 0;
        flag = true;

        RoomStatus roomStatus = null;

        while (flag) {
            try {
                System.out.print(
                        "Enter Room Status: 1 -> AVAILABLE, 2 -> OCCUPIED, 3 -> MAINTENANCE, 4 -> CLEANING: "
                );

                int roomStatusNum = scanner.nextInt();

                switch (roomStatusNum) {
                    case 1:
                        roomStatus = RoomStatus.AVAILABLE;
                        flag = false;
                        break;

                    case 2:
                        roomStatus = RoomStatus.OCCUPIED;
                        flag = false;
                        break;

                    case 3:
                        roomStatus = RoomStatus.MAINTENANCE;
                        flag = false;
                        break;

                    case 4:
                        roomStatus = RoomStatus.CLEANING;
                        flag = false;
                        break;

                    default:
                        countOfChoice++;

                        if (countOfChoice >= 3) {
                            System.out.println("Too many invalid attempts.");
                            return;
                        }

                        System.out.println("Invalid choice.");
                        System.out.println("You have " + (3 - countOfChoice) + " chances left.");
                }

            } catch (InputMismatchException e) {
                scanner.nextLine();
                countOfChoice++;

                if (countOfChoice >= 3) {
                    System.out.println("Too many invalid attempts.");
                    return;
                }

                System.out.println("Invalid input. Please enter a number.");
                System.out.println("You have " + (3 - countOfChoice) + " chances left.");
            }
        }

        System.out.print("Enter the Room Price: ");
        double roomPrice = scanner.nextDouble();

        System.out.print("Enter the Room Capacity: ");
        int roomCapacity = scanner.nextInt();

        Room room = new Room(
                roomNumber,
                roomTybe,
                roomStatus,
                roomPrice,
                roomCapacity
        );
        roomService.addRoom(room);
        System.out.print("\nRoom Created Successfully!\n");
    }


    private void searchRoom() {
        System.out.println("\nSearch Room Page\n");

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();

        Room room = roomService.searchRoom(roomNumber);

        if (room != null) {
            System.out.println(room);
        } else {
            System.out.println("Room Not Found!");
        }
    }
    private  void deleteRoom() {
        System.out.println("\nDelete Room Page\n");

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();

        roomService.deleteRoom(roomNumber);


    }
    private void updateRoom() {

        System.out.println("\nUpdate Room Page");

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();

        Room existingRoom = roomService.searchRoom(roomNumber);

        if (existingRoom == null) {
            System.out.println("Room Not Found!");
            return;
        }

        int countOfChoice = 0;
        boolean flag = true;

        RoomTybe roomTybe = null;

        // Select Room Type
        while (flag) {
            try {
                System.out.print(
                        "Enter Room Type: 1 -> SINGLE, 2 -> DOUBLE, 3 -> SUITE: "
                );

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        roomTybe = RoomTybe.SINGLE;
                        flag = false;
                        break;

                    case 2:
                        roomTybe = RoomTybe.DOUBLE;
                        flag = false;
                        break;

                    case 3:
                        roomTybe = RoomTybe.SUITE;
                        flag = false;
                        break;

                    default:
                        countOfChoice++;

                        if (countOfChoice >= 3) {
                            System.out.println("Too many invalid attempts.");
                            return;
                        }

                        System.out.println("Invalid choice.");
                        System.out.println("You have " + (3 - countOfChoice) + " chances left.");
                }

            } catch (InputMismatchException e) {
                scanner.nextLine();
                countOfChoice++;

                if (countOfChoice >= 3) {
                    System.out.println("Too many invalid attempts.");
                    return;
                }

                System.out.println("Invalid input. Please enter a number.");
                System.out.println("You have " + (3 - countOfChoice) + " chances left.");
            }
        }

        // Select Room Status
        countOfChoice = 0;
        flag = true;

        RoomStatus roomStatus = null;

        while (flag) {
            try {
                System.out.print(
                        "Enter Room Status: 1 -> AVAILABLE, 2 -> OCCUPIED, 3 -> MAINTENANCE, 4 -> CLEANING: "
                );

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        roomStatus = RoomStatus.AVAILABLE;
                        flag = false;
                        break;

                    case 2:
                        roomStatus = RoomStatus.OCCUPIED;
                        flag = false;
                        break;

                    case 3:
                        roomStatus = RoomStatus.MAINTENANCE;
                        flag = false;
                        break;

                    case 4:
                        roomStatus = RoomStatus.CLEANING;
                        flag = false;
                        break;

                    default:
                        countOfChoice++;

                        if (countOfChoice >= 3) {
                            System.out.println("Too many invalid attempts.");
                            return;
                        }

                        System.out.println("Invalid choice.");
                        System.out.println("You have " + (3 - countOfChoice) + " chances left.");
                }

            } catch (InputMismatchException e) {
                scanner.nextLine();
                countOfChoice++;

                if (countOfChoice >= 3) {
                    System.out.println("Too many invalid attempts.");
                    return;
                }

                System.out.println("Invalid input. Please enter a number.");
                System.out.println("You have " + (3 - countOfChoice) + " chances left.");
            }
        }

        System.out.print("Enter Room Price: ");
        double roomPrice = scanner.nextDouble();

        System.out.print("Enter Room Capacity: ");
        int roomCapacity = scanner.nextInt();

        Room updatedRoom = new Room(
                roomNumber,
                roomTybe,
                roomStatus,
                roomPrice,
                roomCapacity
        );

        roomService.updateRoom(updatedRoom);
    }
    private void changeRoomStatus() {

        System.out.println("\nChange Room Status Page");

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();

        Room existingRoom = roomService.searchRoom(roomNumber);

        if (existingRoom == null) {
            System.out.println("Room Not Found!");
            return;
        }

        int countOfChoice = 0;
        boolean flag = true;

        RoomStatus roomStatus = null;

        while (flag) {
            try {
                System.out.print(
                        "Enter Room Status: 1 -> AVAILABLE, 2 -> OCCUPIED, 3 -> MAINTENANCE, 4 -> CLEANING: "
                );

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        roomStatus = RoomStatus.AVAILABLE;
                        flag = false;
                        break;

                    case 2:
                        roomStatus = RoomStatus.OCCUPIED;
                        flag = false;
                        break;

                    case 3:
                        roomStatus = RoomStatus.MAINTENANCE;
                        flag = false;
                        break;

                    case 4:
                        roomStatus = RoomStatus.CLEANING;
                        flag = false;
                        break;

                    default:
                        countOfChoice++;

                        if (countOfChoice >= 3) {
                            System.out.println("Too many invalid attempts.");
                            return;
                        }

                        System.out.println("Invalid choice.");
                        System.out.println("You have " + (3 - countOfChoice) + " chances left.");
                }

            } catch (InputMismatchException e) {
                scanner.nextLine();
                countOfChoice++;

                if (countOfChoice >= 3) {
                    System.out.println("Too many invalid attempts.");
                    return;
                }

                System.out.println("Invalid input. Please enter a number.");
                System.out.println("You have " + (3 - countOfChoice) + " chances left.");
            }
        }

        roomService.changeRoomStatus(roomNumber, roomStatus);
    }


}
