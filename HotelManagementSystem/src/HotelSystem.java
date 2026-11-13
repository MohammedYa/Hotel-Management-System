import Booking.Packages.BookingRepository;
import Booking.Packages.ManagementBooking;
import Booking.services.BookingService;
import Booking.services.BookingServiceImpl;
import CheckInOut.Packages.ManagementCheckInOut;
import CheckInOut.Services.CheckInOutService;
import CheckInOut.Services.CheckInOutServiceImpl;
import Customers.Packages.CustomerRepository;
import Customers.Packages.ManagementCustomers;
import Customers.Services.CustomerService;
import Customers.Services.CustomerServiceImpl;
import Payment.Packages.PaymentManagement;
import Payment.Packages.PaymentRepository;
import Payment.Services.PaymentService;
import Payment.Services.PaymentServiceImpl;
import Report.Packages.Menus.Booking.BookingsReportsManegment;
import Report.Packages.Menus.Customer.CustomerReportsManegment;
import Report.Packages.Menus.Payment.PaymentReportsManegment;
import Report.Packages.Menus.Room.RoomReportsManegment;
import Report.Packages.ReportsMangement;
import Report.Services.ReportService;
import Report.Services.ReportServiceImpl;
import Rooms.pakages.ManagementRooms;
import Rooms.pakages.RoomRepository;
import Rooms.service.RoomService;
import Rooms.service.RoomServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents the main entry point for the Hotel Management System.
 * Initializes repositories, services, and management components,
 * and provides the main menu for accessing the system's features.
 */
public class HotelSystem {

    /**
     * Scanner used to read user input from the standard input stream.
     */
    Scanner scanner = new Scanner(System.in);

    // 1. Repositories

    /** Repository responsible for storing and managing room data. */
    RoomRepository roomRepository = new RoomRepository();

    /** Repository responsible for storing and managing customer data. */
    CustomerRepository customerRepository = new CustomerRepository();

    /** Repository responsible for storing and managing booking data. */
    BookingRepository bookingRepository = new BookingRepository();

    /** Repository responsible for storing and managing payment data. */
    PaymentRepository paymentRepository = new PaymentRepository();

    // 2. Booking Service

    /**
     * Service responsible for handling booking operations
     * using the required repositories.
     */
    BookingService bookingService = new BookingServiceImpl(
            bookingRepository,
            customerRepository,
            roomRepository
    );

    // 3. Other Services

    /** Service responsible for handling payment operations. */
    PaymentService paymentService = new PaymentServiceImpl(paymentRepository, bookingRepository);

    /** Service responsible for handling room management operations. */
    RoomService roomService = new RoomServiceImpl(roomRepository, bookingService);

    /** Service responsible for handling customer management operations. */
    CustomerService customerService = new CustomerServiceImpl(customerRepository, bookingService);

    /** Service responsible for handling check-in and check-out operations. */
    CheckInOutService checkInOutService = new CheckInOutServiceImpl(
            bookingRepository, roomRepository, paymentService
    );

    /** Service responsible for generating hotel management reports. */
    ReportService reportService = new ReportServiceImpl(
            roomRepository, bookingRepository, paymentRepository, customerRepository
    );

    // 4. Management

    /** Management component responsible for room operations and user interaction. */
    ManagementRooms managementRooms = new ManagementRooms(roomService);

    /** Management component responsible for customer operations and user interaction. */
    ManagementCustomers managementCustomers = new ManagementCustomers(customerService);

    /** Management component responsible for booking operations and user interaction. */
    ManagementBooking managementBooking = new ManagementBooking(bookingService, roomRepository);

    /** Management component responsible for check-in and check-out operations. */
    ManagementCheckInOut managementCheckInOut = new ManagementCheckInOut(checkInOutService);

    /** Management component responsible for payment operations and user interaction. */
    PaymentManagement paymentManagement = new PaymentManagement(paymentService);

    /** Management component responsible for displaying payment reports. */
    PaymentReportsManegment paymentReportsManegment = new PaymentReportsManegment(reportService);

    /** Management component responsible for displaying booking reports. */
    BookingsReportsManegment bookingsReportsManegment = new BookingsReportsManegment(reportService);

    /** Management component responsible for displaying customer reports. */
    CustomerReportsManegment customerReportsManegment = new CustomerReportsManegment(reportService);

    /** Management component responsible for displaying room reports. */
    RoomReportsManegment roomReportsManegment = new RoomReportsManegment(reportService);

    /**
     * Main reports management component that combines
     * the different report management sections.
     */
    ReportsMangement reportsMangement = new ReportsMangement(
            bookingsReportsManegment,
            paymentReportsManegment,
            customerReportsManegment,
            roomReportsManegment
    );

    /**
     * Starts the Hotel Management System and displays the main menu.
     * Allows the user to navigate between the system's management modules
     * and handles invalid menu selections and input mismatches.
     *
     * The system exits after the user selects option 0 or reaches
     * three invalid attempts.
     */
    public void Start() {
        int countOfChoice = 0;
        boolean flag = true;

        while (flag) {
            try {
                System.out.print("\n========== HOTEL MANAGEMENT SYSTEM ==========\n" +
                        "\n" +
                        "1 -----> Room Management\n" +
                        "2 -----> Customer Management\n" +
                        "3 -----> Booking Management\n" +
                        "4 -----> Check-in / Check-out\n" +
                        "5 -----> Payment Management\n" +
                        "6 -----> Reports\n" +
                        "0 -----> Exit\n");

                System.out.print("Choose an option:");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        countOfChoice = 0;
                        System.out.println("Thank you for your time see you later ............ \n");
                        flag = false;
                        break;

                    case 1:
                        countOfChoice = 0;
                        managementRooms.start();
                        break;

                    case 2:
                        countOfChoice = 0;
                        managementCustomers.Start();
                        break;

                    case 3:
                        countOfChoice = 0;
                        managementBooking.Start();
                        break;

                    case 4:
                        countOfChoice = 0;
                        managementCheckInOut.Start();
                        break;

                    case 5:
                        countOfChoice = 0;
                        paymentManagement.Start();
                        break;

                    case 6:
                        countOfChoice = 0;
                        System.out.print("\n========== Report Management ==========\n");
                        reportsMangement.Start();
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
}