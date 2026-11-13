package Report.Packages.Menus.Room;

import Report.Services.ReportService;
import Rooms.Enums.RoomStatus;
import Rooms.Enums.RoomTybe;
import Rooms.entite.Room;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RoomReportsManegment {

    RoomReportsMenu roomReportsMenu=new RoomReportsMenu();
    private final Scanner scanner = new Scanner(System.in);
    private final ReportService reportService;

public  RoomReportsManegment(ReportService reportService ) {
    this.reportService=reportService;
}





    public void Start() {
        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {
            try {
                roomReportsMenu.displayMenu();

                System.out.print("Enter Your Choice: ");
                int choice = scanner.nextInt();

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
                        System.out.println("All AVAILABLE Rooms ");
                        getRoomsByStatusReport(RoomStatus.AVAILABLE);
                        break;

                    case 3:
                        tryChoiceCount = 0;
                        System.out.println("All OCCUPIED Rooms ");
                        getRoomsByStatusReport(RoomStatus.OCCUPIED);
                        break;

                    case 4:
                        tryChoiceCount = 0;
                        System.out.println("All MAINTENANCE Rooms ");
                        getRoomsByStatusReport(RoomStatus.MAINTENANCE);
                        break;
                    case 5:
                        tryChoiceCount = 0;
                        System.out.println("All CLEANING Rooms ");
                        getRoomsByStatusReport(RoomStatus.CLEANING);
                        break;
                    case 6:
                        tryChoiceCount = 0;
                        System.out.println("All SINGLE Rooms ");
                        getRoomsByTypeReport(RoomTybe.SINGLE);
                        break;

                    case 7:
                        tryChoiceCount = 0;
                        System.out.println("All DOUBLE Rooms ");
                        getRoomsByTypeReport(RoomTybe.DOUBLE);
                        break;
                    case 8:
                        tryChoiceCount = 0;
                        System.out.println("All SUITE Rooms ");
                        getRoomsByTypeReport(RoomTybe.SUITE);
                        break;
                    case 9:
                        tryChoiceCount = 0;
                        System.out.println("2.All Rooms By Capacity  ");
                        getRoomsByCapacityReport();
                        break;
                    case 10:
                        tryChoiceCount = 0;
                        System.out.println(" Rooms By Price Range");
                        getRoomsByPriceRangeReport();
                        break;
                    default:
                        tryChoiceCount++;
                        System.out.println("Invalid Choice! Choose a number between 0-10.");
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









    private void  displayAllRoomsReport(){
        List<Room> allRoomsReport=reportService.getAllRoomsReport();
        if (allRoomsReport.isEmpty()){
            System.out.println("There are no rooms in the system");
        }
        allRoomsReport.forEach(System.out::println);
    }

    private void getRoomsByStatusReport(RoomStatus roomStatus) {
        List<Room> rooms = reportService.getRoomsByStatusReport(roomStatus);
        if (rooms.isEmpty()){
            System.out.println("There are no rooms in the system");
        }
        rooms.forEach(System.out::println);
    }

    private void getRoomsByTypeReport(RoomTybe roomTybe) {
        List<Room> rooms = reportService.getRoomsByTypeReport(roomTybe);
        if (rooms.isEmpty()){
            System.out.println("There are no rooms in the system");
        }
        rooms.forEach(System.out::println);
    }

    private void getRoomsByCapacityReport() {
        System.out.print("Enter Room Capacity: ");
        int capacity = scanner.nextInt();

        List<Room> rooms = reportService.getRoomsByCapacityReport(capacity);
        if (rooms.isEmpty()){
            System.out.println("There are no rooms in the system");
        }
        rooms.forEach(System.out::println);
    }

    private void getRoomsByPriceRangeReport() {
        System.out.print("Enter Minimum Price: ");
        BigDecimal minPrice = scanner.nextBigDecimal();

        System.out.print("Enter Maximum Price: ");
        BigDecimal maxPrice = scanner.nextBigDecimal();

        List<Room> rooms = reportService.getRoomsByPriceRangeReport(minPrice, maxPrice);
        if (rooms.isEmpty()){
            System.out.println("There are no rooms in the system");
        }
        rooms.forEach(System.out::println);
    }
}
