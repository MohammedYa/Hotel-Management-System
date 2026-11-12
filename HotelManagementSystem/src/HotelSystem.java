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
import Rooms.pakages.ManagementRooms;
import Rooms.pakages.RoomRepository;
import Rooms.service.RoomService;
import Rooms.service.RoomServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelSystem {

    Scanner scanner =new Scanner(System.in);

    // 1. Repositories
    RoomRepository roomRepository = new RoomRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    BookingRepository bookingRepository = new BookingRepository();
    PaymentRepository paymentRepository = new PaymentRepository();
    // 2. Booking Service
    BookingService bookingService = new BookingServiceImpl(
            bookingRepository,
            customerRepository,
            roomRepository
    );


    // 3. Other Services
    RoomService roomService = new RoomServiceImpl(roomRepository,bookingService);
    CustomerService customerService =new CustomerServiceImpl(customerRepository, bookingService);
    CheckInOutService checkInOutService = new CheckInOutServiceImpl(bookingRepository, roomRepository);
    PaymentService  paymentService = new PaymentServiceImpl(paymentRepository,bookingRepository);
    // 4. Management
    ManagementRooms managementRooms = new ManagementRooms(roomService);
    ManagementCustomers managementCustomers = new ManagementCustomers(customerService);
    ManagementBooking managementBooking = new ManagementBooking(bookingService,roomRepository);
    ManagementCheckInOut managementCheckInOut = new ManagementCheckInOut(checkInOutService);
    PaymentManagement paymentManagement= new PaymentManagement(paymentService);
    public void Start(){
        int countOfChoice=0;
        boolean flag=true;
        while (flag){
            try {
                System.out.print("\n========== HOTEL MANAGEMENT SYSTEM ==========\n" +
                        "\n" +
                        "1 -----> Room Management\n" +
                        "2 -----> Customer Management\n" +
                        "3 -----> Booking Management\n" +
                        "4 -----> Check-in / Check-out\n" +
                        "5 -----> Payment Management\n" +
                        "6 -----> Reports\n" +
                        "0 -----> Exit\n"  );
                System.out.print("Choose an option:");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        countOfChoice=0;
                        System.out.println("Thank you for your time see you later ............ \n");
                        flag=false;
                        break;

                    case 1:
                        countOfChoice=0;
                        managementRooms.start();
                        break;
                    case 2:
                        countOfChoice=0;
                        managementCustomers.Start();

                        break;
                    case 3:
                        countOfChoice=0;
                        managementBooking.Start();
                        break;
                    case 4:
                        countOfChoice=0;
                        managementCheckInOut.Start();
                        break;
                    case 5:
                        countOfChoice=0;
                        paymentManagement.Start();
                        break;
                    case 6:
                        countOfChoice=0;
                        System.out.print("\n========== Report Management ==========\n");
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
