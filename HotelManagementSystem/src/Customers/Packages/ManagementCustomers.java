
package Customers.Packages;

import Customers.Entite.Customer;
import Customers.Services.CustomerService;
import Validations.ValidationUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Handles user interactions related to customer management.
 *
 * Provides operations for adding, displaying, searching,
 * updating, and deleting customers.
 */
public class ManagementCustomers {

    private final CustomerService customerService;
    private final CustomerMenu customerMenu = new CustomerMenu();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs ManagementCustomers with the required service.
     *
     * @param customerService service responsible for customer operations
     * @throws IllegalArgumentException if customerService is null
     */
    public ManagementCustomers(CustomerService customerService) {

        ValidationUtils.requireNotNull(customerService, "Customer Service");

        this.customerService = customerService;
    }

    /**
     * Starts the customer management menu.
     *
     * Allows the user three invalid menu attempts before exiting.
     */
    public void Start() {

        boolean flag = true;
        int tryChoiceCount = 0;

        while (flag) {

            customerMenu.displayMenu();
            System.out.print("Enter Your Choice: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {

                tryChoiceCount++;

                System.out.println(
                        "Invalid input! Please enter a number between 0-5."
                );
                System.out.println(
                        "You have " + (3 - tryChoiceCount) + " chances left."
                );

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
                    addCustomer();
                    break;

                case 2:
                    tryChoiceCount = 0;
                    displayAllCustomers();
                    break;

                case 3:
                    tryChoiceCount = 0;
                    displayCustomerById();
                    break;

                case 4:
                    tryChoiceCount = 0;
                    updateCustomer();
                    break;

                case 5:
                    tryChoiceCount = 0;
                    deleteCustomer();
                    break;

                default:
                    tryChoiceCount++;

                    System.out.println(
                            "Invalid Choice! Enter a number between 0-5."
                    );
                    System.out.println(
                            "You have " + (3 - tryChoiceCount) + " chances left."
                    );

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
     * @return integer entered by the user
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
     * Reads a non-blank text value from the user.
     *
     * @param message prompt displayed to the user
     * @return non-blank text entered by the user
     */
    private String readNonBlank(String message) {

        while (true) {

            System.out.print(message);
            String value = scanner.nextLine();

            if (!value.isBlank()) {
                return value.trim();
            }

            System.out.println("This field cannot be empty!");
        }
    }

    /**
     * Adds a new customer after collecting the required information.
     *
     * @throws IllegalArgumentException if customer data violates
     *                                  entity validation rules
     */
    private void addCustomer() {

        System.out.println("\n========== Add Customer Page ==========");

        int customerId = readPositiveInt("Enter the Customer ID: ");

        String customerName = readNonBlank("Enter the Customer Name: ");

        String customerPhone = readNonBlank("Enter the Customer Phone: ");

        String customerEmail = readNonBlank("Enter the Customer Email: ");

        Customer customer = new Customer(
                customerId,
                customerName,
                customerPhone,
                customerEmail
        );

        if (customerService.addCustomer(customer)) {
            System.out.println("Customer added successfully!");
        } else {
            System.out.println(
                    "Customer not added --- Customer already exists!"
            );
        }
    }

    /**
     * Displays all customers registered in the system.
     *
     * Displays a message if no customers are available.
     */
    private void displayAllCustomers() {

        System.out.println("\n========== All Customers Page ==========");

        List<Customer> customers = customerService.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("There are no customers in the system!");
        } else {
            customers.forEach(System.out::println);
        }
    }

    /**
     * Searches for a customer using the customer ID
     * and displays the customer's information.
     */
    private void displayCustomerById() {

        System.out.println("\n========== Display Customer By ID Page ==========");

        int customerId = readPositiveInt("Enter Customer ID: ");

        Customer customer = customerService.findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found!");
        } else {
            System.out.println(customer);
        }
    }

    /**
     * Deletes an existing customer using the customer ID.
     *
     * @implNote The service handles the related booking cancellation
     *           rules before deleting the customer.
     */
    private void deleteCustomer() {

        System.out.println("\n========== Delete Customer By ID Page ==========");

        int customerId = readPositiveInt("Enter the Customer ID: ");

        if (customerService.deleteCustomer(customerId)) {
            System.out.println("Customer deleted successfully!");
        } else {
            System.out.println(
                    "Customer not deleted --- Customer does not exist!"
            );
        }
    }

    /**
     * Updates an existing customer's information.
     *
     * Collects the new customer name, phone, and email,
     * then delegates the update operation to CustomerService.
     */
    private void updateCustomer() {

        System.out.println("\n========== Update Customer By ID Page ==========");

        int customerId = readPositiveInt("Enter the Customer ID: ");

        Customer customer = customerService.findCustomerById(customerId);

        if (customer == null) {
            System.out.println(
                    "Customer not updated --- Customer does not exist!"
            );
            return;
        }

        String customerName = readNonBlank("Enter the Customer Name: ");

        String customerPhone = readNonBlank("Enter the Customer Phone: ");

        String customerEmail = readNonBlank("Enter the Customer Email: ");

        Customer customerUpdate = new Customer(
                customerId,
                customerName,
                customerPhone,
                customerEmail
        );

        if (customerService.updateCustomer(customerId, customerUpdate)) {
            System.out.println("Customer updated successfully!");
        } else {
            System.out.println("Customer update failed!");
        }
    }
}
