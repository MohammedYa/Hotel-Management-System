package Booking.Packages;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Booking.services.BookingService;
import Rooms.entite.Room;
import Rooms.pakages.RoomRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManagementBooking {
    final  private BookingService bookingService;
    private final Scanner scanner = new Scanner(System.in);
    private  final RoomRepository roomRepository;
    private final BookingMenu bookingMenu = new BookingMenu();

  public   ManagementBooking(BookingService bookingService, RoomRepository roomRepository) {
        this.bookingService = bookingService;
        this.roomRepository = roomRepository;
    }

    public  void Start(){
        boolean  flag=true;
        int tryChoiceCount=0;
        while (flag) {
            try {
                bookingMenu.displayMenu();
                System.out.print("  Enter Your Choice: ");
                int choice = scanner.nextInt();
                switch(choice){
                    case 0:
                        flag=false;
                        break;
                    case 1:
                        tryChoiceCount=0;
                        addBooking();
                        break;
                    case 2:
                        tryChoiceCount=0;
                        displayBookings();
                        break;
                    case 3:
                        tryChoiceCount=0;
                        searchBooking();
                        break;
                    case 4:
                        tryChoiceCount=0;
                        updateBooking();
                        break;
                    case 5:
                        tryChoiceCount=0;
                        cancelBooking();
                        break;
                    default:
                        System.out.println("Invalid Choice You have Chosen  Number between 0-5 ");
                        System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");
                        tryChoiceCount++;
                        if (tryChoiceCount >=3) {

                            System.out.println("Invalid Choice Try again later ");
                            flag=false;

                        }

                }
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                if (tryChoiceCount>=3){
                    System.out.println("Invalid Choice try again later");
                    flag=false;
                }
                else{
                    System.out.println("You have entered the correct choice 0-5");
                    tryChoiceCount++;
                    System.out.println("You have " + (3 - tryChoiceCount) + " chances left.");

                }
            }

        }

    }

    private void addBooking() {
        System.out.println("Add Booking Page:");

        System.out.print("Enter Booking ID: ");
        int bookingId = scanner.nextInt();

        if (bookingService.searchBooking(bookingId) != null) {
            System.out.println("Booking is already exist!");
            return;
        }

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();

        System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
        LocalDate checkInDate = LocalDate.parse(scanner.next());

        System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
        LocalDate checkOutDate = LocalDate.parse(scanner.next());
        long numberOfNights = ChronoUnit.DAYS.between(
                checkInDate,
                checkOutDate
        );
        Room room =roomRepository.searchRoom(roomNumber);
        BigDecimal totalPrice = room.getRoomPrice()
                .multiply(BigDecimal.valueOf(numberOfNights));

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

    private void searchBooking() {
        System.out.println("Search Booking Page:");

        System.out.print("Enter Booking ID: ");
        int bookingId = scanner.nextInt();

        Booking booking = bookingService.searchBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking Not Found!");
            return;
        }

        System.out.println(booking);
    }

    private void cancelBooking() {
        System.out.println("Cancel Booking Page:");

        System.out.print("Enter Booking ID: ");
        int bookingId = scanner.nextInt();

        boolean bookingCanceled = bookingService.cancelBooking(bookingId);

        if (bookingCanceled) {
            System.out.println("Booking Canceled Successfully!");
        } else {
            System.out.println("Failed to Cancel Booking. Booking may not exist, or cannot be canceled!");
        }
    }

    private void displayBookings() {
        System.out.println("Display Booking Page:");

        List<Booking> bookings = bookingService.getBookings();

        if (bookings.isEmpty()) {
            System.out.println("No bookings found!");
            return;
        }

        bookings.forEach(System.out::println);
    }

    private void updateBooking() {
        System.out.println("Update Booking Page:");

        System.out.print("Enter Booking ID: ");
        int bookingId = scanner.nextInt();

        Booking booking = bookingService.searchBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking Not Found!");
            return;
        }

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();

        System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
        LocalDate checkInDate = LocalDate.parse(scanner.next());

        System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
        LocalDate checkOutDate = LocalDate.parse(scanner.next());

        long numberOfNights = ChronoUnit.DAYS.between(
                checkInDate,
                checkOutDate
        );
        Room room =roomRepository.searchRoom(roomNumber);
        BigDecimal totalPrice = room.getRoomPrice()
                .multiply(BigDecimal.valueOf(numberOfNights));

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
