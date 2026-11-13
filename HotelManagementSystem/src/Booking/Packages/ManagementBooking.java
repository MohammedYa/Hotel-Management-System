
package Booking.Packages;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Booking.services.BookingService;
import Rooms.entite.Room;
import Rooms.pakages.RoomRepository;
import Validations.ValidationUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

/**
 * Handles user interactions related to booking management.
 *
 * Provides operations for adding, searching, updating,
 * displaying, and cancelling bookings.
 */
public class ManagementBooking {

    private final BookingService bookingService;
    private final Scanner scanner = new Scanner(System.in);
    private final RoomRepository roomRepository;
    private final BookingMenu bookingMenu = new BookingMenu();

    /**
     * Constructs ManagementBooking with the required dependencies.
     *
     * @param bookingService service used to manage bookings
     * @param roomRepository repository used to retrieve room information
     * @throws IllegalArgumentException if any dependency is null
     */
    public ManagementBooking(
            BookingService bookingService,
            RoomRepository roomRepository) {

        ValidationUtils.requireNotNull(bookingService, "Booking Service");
        ValidationUtils.requireNotNull(roomRepository, "Room Repository");

        this.bookingService = bookingService;
        this.roomRepository = roomRepository;
    }

    /**
     * Starts the booking management menu.
     *
     * Allows the user three invalid menu attempts before exiting.
     */
    public void Start() {

        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {

            bookingMenu.displayMenu();
            System.out.print("Enter Your Choice: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {

                tryChoiceCount++;

                System.out.println("Invalid input! Please enter a number between 0-5.");
                System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");

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
                    addBooking();
                    break;

                case 2:
                    tryChoiceCount = 0;
                    displayBookings();
                    break;

                case 3:
                    tryChoiceCount = 0;
                    searchBooking();
                    break;

                case 4:
                    tryChoiceCount = 0;
                    updateBooking();
                    break;

                case 5:
                    tryChoiceCount = 0;
                    cancelBooking();
                    break;

                default:
                    tryChoiceCount++;

                    System.out.println("Invalid Choice! Enter a number between 0-5.");
                    System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");

                    if (tryChoiceCount >= 3) {
                        System.out.println("Invalid Choice. Try again later.");
                        flag = false;
                    }
            }
        }
    }

    /**
     * Reads an integer from the user.
     *
     * Repeats the input operation until a valid integer is entered.
     *
     * @param message prompt displayed to the user
     * @return valid integer entered by the user
     */
    private int readInt(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
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
     * Reads a date from the user using the YYYY-MM-DD format.
     *
     * Repeats the input operation if the date format is invalid.
     *
     * @param message prompt displayed to the user
     * @return valid LocalDate entered by the user
     */
    private LocalDate readDate(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return LocalDate.parse(input.trim());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Please use YYYY-MM-DD format.");
            }
        }
    }

    /**
     * Calculates the total booking price based on the room price
     * and the number of nights.
     *
     * @param room room associated with the booking
     * @param checkInDate booking check-in date
     * @param checkOutDate booking check-out date
     * @return total booking price
     */
    private BigDecimal calculateTotalPrice(
            Room room,
            LocalDate checkInDate,
            LocalDate checkOutDate) {

        long numberOfNights = ChronoUnit.DAYS.between(
                checkInDate,
                checkOutDate
        );

        return room.getRoomPrice()
                .multiply(BigDecimal.valueOf(numberOfNights));
    }

    /**
     * Adds a new booking after collecting the required information.
     *
     * Validates the booking ID, room existence, and date range
     * before creating the booking.
     */
    private void addBooking() {

        System.out.println("Add Booking Page:");

        int bookingId = readPositiveInt("Enter Booking ID: ");

        if (bookingService.searchBooking(bookingId) != null) {
            System.out.println("Booking already exists!");
            return;
        }

        int roomNumber = readPositiveInt("Enter Room Number: ");

        Room room = roomRepository.searchRoom(roomNumber);

        if (room == null) {
            System.out.println("Room Not Found!");
            return;
        }

        int customerId = readPositiveInt("Enter Customer ID: ");

        LocalDate checkInDate = readDate(
                "Enter Check-in Date (YYYY-MM-DD): "
        );

        LocalDate checkOutDate = readDate(
                "Enter Check-out Date (YYYY-MM-DD): "
        );

        if (!checkOutDate.isAfter(checkInDate)) {
            System.out.println("Check-out date must be after check-in date!");
            return;
        }

        BigDecimal totalPrice = calculateTotalPrice(
                room,
                checkInDate,
                checkOutDate
        );

        Booking booking = new Booking(
                bookingId,
                customerId,
                roomNumber,
                checkInDate,
                checkOutDate,
                BookingStatus.CONFIRMED,
                totalPrice
        );

        if (bookingService.addBooking(booking)) {
            System.out.println("Booking Added Successfully!");
        } else {
            System.out.println("Failed to Add Booking. Check your data!");
        }
    }

    /**
     * Searches for a booking using its ID and displays its information.
     */
    private void searchBooking() {

        System.out.println("Search Booking Page:");

        int bookingId = readPositiveInt("Enter Booking ID: ");

        Booking booking = bookingService.searchBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking Not Found!");
            return;
        }

        System.out.println(booking);
    }

    /**
     * Cancels a booking using its ID.
     *
     * Only bookings that satisfy the service cancellation rules
     * can be cancelled.
     */
    private void cancelBooking() {

        System.out.println("Cancel Booking Page:");

        int bookingId = readPositiveInt("Enter Booking ID: ");

        boolean bookingCanceled = bookingService.cancelBooking(bookingId);

        if (bookingCanceled) {
            System.out.println("Booking Canceled Successfully!");
        } else {
            System.out.println(
                    "Failed to Cancel Booking. Booking may not exist, or cannot be canceled!"
            );
        }
    }

    /**
     * Displays all existing bookings.
     *
     * Displays a message if no bookings are available.
     */
    private void displayBookings() {

        System.out.println("Display Booking Page:");

        List<Booking> bookings = bookingService.getBookings();

        if (bookings.isEmpty()) {
            System.out.println("No bookings found!");
            return;
        }

        bookings.forEach(System.out::println);
    }

    /**
     * Updates an existing booking after collecting the new information.
     *
     * Recalculates the total price using the selected room's price
     * and the new booking date range.
     */
    private void updateBooking() {

        System.out.println("Update Booking Page:");

        int bookingId = readPositiveInt("Enter Booking ID: ");

        Booking booking = bookingService.searchBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking Not Found!");
            return;
        }

        int roomNumber = readPositiveInt("Enter Room Number: ");

        Room room = roomRepository.searchRoom(roomNumber);

        if (room == null) {
            System.out.println("Room Not Found!");
            return;
        }

        int customerId = readPositiveInt("Enter Customer ID: ");

        LocalDate checkInDate = readDate(
                "Enter Check-in Date (YYYY-MM-DD): "
        );

        LocalDate checkOutDate = readDate(
                "Enter Check-out Date (YYYY-MM-DD): "
        );

        if (!checkOutDate.isAfter(checkInDate)) {
            System.out.println("Check-out date must be after check-in date!");
            return;
        }

        BigDecimal totalPrice = calculateTotalPrice(
                room,
                checkInDate,
                checkOutDate
        );

        Booking bookingUpdate = new Booking(
                bookingId,
                customerId,
                roomNumber,
                checkInDate,
                checkOutDate,
                booking.getBookingStatus(),
                totalPrice
        );

        boolean bookingUpdated =
                bookingService.updateBooking(bookingId, bookingUpdate);

        if (bookingUpdated) {
            System.out.println("Booking Updated Successfully!");
        } else {
            System.out.println("Failed to Update Booking. Check your data!");
        }
    }
}
