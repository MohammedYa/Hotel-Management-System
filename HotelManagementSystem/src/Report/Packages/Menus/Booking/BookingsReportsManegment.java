package Report.Packages.Menus.Booking;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Report.Services.ReportService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookingsReportsManegment {
    private final ReportService reportService;
    private final Scanner scanner = new Scanner(System.in);
    BookingReportsMenu bookingReportsMenu = new BookingReportsMenu();
    public BookingsReportsManegment(ReportService reportService ) {
        this.reportService=reportService;
    }

    public void Start() {
        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {
            try {
                bookingReportsMenu.displayMenu();

                System.out.print("Enter Your Choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        flag = false;
                        break;

                    case 1:
                        tryChoiceCount = 0;
                        System.out.println("All Bookings Report");
                        displayAllBookingsReport();
                        break;

                    case 2:
                        tryChoiceCount = 0;
                        System.out.println("2. CONFIRMED Bookings");
                        getBookingsByStatusReport(BookingStatus.CONFIRMED);
                        break;

                    case 3:
                        tryChoiceCount = 0;
                        System.out.println("3. CANCELLED Bookings");
                        getBookingsByStatusReport(BookingStatus.CANCELLED);
                        break;

                    case 4:
                        tryChoiceCount = 0;
                        System.out.println("4. COMPLETED Bookings");
                        getBookingsByStatusReport(BookingStatus.COMPLETED);
                        break;
                    case 5:
                        tryChoiceCount = 0;
                        System.out.println("5. NO_SHOW Bookings");
                        getBookingsByStatusReport(BookingStatus.NO_SHOW);
                        break;
                    case 6:
                        tryChoiceCount = 0;
                        System.out.println("6. Bookings By Customer ID");
                        getBookingsByCustomerIdReport();
                        break;

                    case 7:
                        tryChoiceCount = 0;
                        System.out.println("7. Bookings By Room Number");
                        getBookingsByRoomNumberReport();
                        break;
                    case 8:
                        tryChoiceCount = 0;
                        System.out.println("8. Bookings By Date Range");
                        getBookingsByDateRangeReport();
                        break;
                    default:
                        tryChoiceCount++;
                        System.out.println("Invalid Choice! Choose a number between 0-5.");
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



    // ================= BOOKING REPORTS =================

    private void displayAllBookingsReport() {
        List<Booking> bookings = reportService.getAllBookingsReport();

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings in the system");
            return;
        }

        bookings.forEach(System.out::println);
    }

    private void getBookingsByStatusReport(BookingStatus status) {


        List<Booking> bookings = reportService.getBookingsByStatusReport(status);

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings with this status");
            return;
        }

        bookings.forEach(System.out::println);
    }

    private void getBookingsByCustomerIdReport() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();

        List<Booking> bookings =
                reportService.getBookingsByCustomerIdReport(customerId);

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings for this customer");
            return;
        }

        bookings.forEach(System.out::println);
    }

    private void getBookingsByRoomNumberReport() {
        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();

        List<Booking> bookings =
                reportService.getBookingsByRoomNumberReport(roomNumber);

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings for this room");
            return;
        }

        bookings.forEach(System.out::println);
    }

    private void getBookingsByDateRangeReport() {
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.next());

        System.out.print("Enter End Date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.next());

        List<Booking> bookings =
                reportService.getBookingsByDateRangeReport(startDate, endDate);

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings in this date range");
            return;
        }

        bookings.forEach(System.out::println);
    }

}
