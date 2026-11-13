
package Report.Packages.Menus.Booking;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Report.Services.ReportService;
import Validations.ValidationUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Handles user interactions related to booking reports.
 *
 * Provides reports for all bookings, booking statuses,
 * customer bookings, room bookings, and date ranges.
 */
public class BookingsReportsManegment {

    private final ReportService reportService;
    private final Scanner scanner = new Scanner(System.in);
    private final BookingReportsMenu bookingReportsMenu =
            new BookingReportsMenu();

    /**
     * Constructs BookingsReportsManegment with the required service.
     *
     * @param reportService service responsible for generating reports
     * @throws IllegalArgumentException if reportService is null
     */
    public BookingsReportsManegment(ReportService reportService) {

        ValidationUtils.requireNotNull(reportService, "Report Service");

        this.reportService = reportService;
    }

    /**
     * Starts the booking reports menu.
     *
     * Allows the user three invalid menu attempts before exiting.
     */
    public void Start() {

        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {

            bookingReportsMenu.displayMenu();

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

                    System.out.println(
                            "Invalid Choice! Choose a number between 0-8."
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
     * Reads a date from the user using the YYYY-MM-DD format.
     *
     * @param message prompt displayed to the user
     * @return parsed LocalDate
     */
    private LocalDate readDate(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return LocalDate.parse(input.trim());

            } catch (DateTimeParseException e) {
                System.out.println(
                        "Invalid date! Use the format YYYY-MM-DD."
                );
            }
        }
    }

    /**
     * Displays all bookings in the system.
     */
    private void displayAllBookingsReport() {

        List<Booking> bookings =
                reportService.getAllBookingsReport();

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings in the system");
            return;
        }

        bookings.forEach(System.out::println);
    }

    /**
     * Displays bookings filtered by their status.
     *
     * @param status booking status used as the report filter
     */
    private void getBookingsByStatusReport(BookingStatus status) {

        List<Booking> bookings =
                reportService.getBookingsByStatusReport(status);

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings with this status");
            return;
        }

        bookings.forEach(System.out::println);
    }

    /**
     * Displays all bookings associated with a specific customer.
     */
    private void getBookingsByCustomerIdReport() {

        int customerId = readPositiveInt("Enter Customer ID: ");

        List<Booking> bookings =
                reportService.getBookingsByCustomerIdReport(customerId);

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings for this customer");
            return;
        }

        bookings.forEach(System.out::println);
    }

    /**
     * Displays all bookings associated with a specific room.
     */
    private void getBookingsByRoomNumberReport() {

        int roomNumber = readPositiveInt("Enter Room Number: ");

        List<Booking> bookings =
                reportService.getBookingsByRoomNumberReport(roomNumber);

        if (bookings.isEmpty()) {
            System.out.println("There are no bookings for this room");
            return;
        }

        bookings.forEach(System.out::println);
    }

    /**
     * Displays bookings within a specified date range.
     *
     * Reads the start and end dates and delegates the filtering
     * operation to ReportService.
     */
    private void getBookingsByDateRangeReport() {

        LocalDate startDate =
                readDate("Enter Start Date (YYYY-MM-DD): ");

        LocalDate endDate =
                readDate("Enter End Date (YYYY-MM-DD): ");

        if (!endDate.isAfter(startDate)
                && !endDate.isEqual(startDate)) {

            System.out.println(
                    "End date must be after or equal to start date."
            );
            return;
        }

        List<Booking> bookings =
                reportService.getBookingsByDateRangeReport(
                        startDate,
                        endDate
                );

        if (bookings.isEmpty()) {
            System.out.println(
                    "There are no bookings in this date range"
            );
            return;
        }

        bookings.forEach(System.out::println);
    }
}
