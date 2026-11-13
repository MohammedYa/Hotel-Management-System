
package Customers.Services;

import Booking.services.BookingService;
import Customers.Entite.Customer;
import Customers.Packages.CustomerRepository;
import Validations.ValidationUtils;

import java.util.List;

/**
 * Implements customer management operations.
 *
 * Handles customer creation, updating, deletion, and searching.
 * Coordinates with BookingService to cancel customer bookings
 * before deleting a customer.
 */
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BookingService bookingService;

    /**
     * Constructs CustomerServiceImpl with its required dependencies.
     *
     * @param customerRepository repository used to manage customers
     * @param bookingService service used to manage customer bookings
     * @throws IllegalArgumentException if either dependency is null
     */
    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            BookingService bookingService) {

        ValidationUtils.requireNotNull(customerRepository, "Customer Repository");
        ValidationUtils.requireNotNull(bookingService, "Booking Service");

        this.customerRepository = customerRepository;
        this.bookingService = bookingService;
    }

    /**
     * Adds a new customer.
     *
     * @param customer customer to be added
     * @return true if the customer is added successfully, false otherwise
     * @throws IllegalArgumentException if customer is null
     */
    @Override
    public boolean addCustomer(Customer customer) {

        ValidationUtils.requireNotNull(customer, "Customer");

        return customerRepository.addCustomer(customer);
    }

    /**
     * Updates an existing customer.
     *
     * @param customerId ID of the customer to update
     * @param customer updated customer information
     * @return true if the customer is updated successfully, false otherwise
     * @throws IllegalArgumentException if customerId is not positive
     *         or customer is null
     */
    @Override
    public boolean updateCustomer(int customerId, Customer customer) {

        ValidationUtils.requirePositive(customerId, "Customer ID");
        ValidationUtils.requireNotNull(customer, "Customer");

        return customerRepository.updateCustomer(customerId, customer);
    }

    /**
     * Deletes an existing customer after cancelling their bookings.
     *
     * @param customerId ID of the customer to delete
     * @return true if the customer is deleted successfully, false otherwise
     * @throws IllegalArgumentException if customerId is not positive
     */
    @Override
    public boolean deleteCustomer(int customerId) {

        ValidationUtils.requirePositive(customerId, "Customer ID");

        Customer customer = customerRepository.searchCustomer(customerId);

        if (customer == null) {
            return false;
        }

        bookingService.cancelBookingsByCustomerId(customerId);

        return customerRepository.deleteCustomer(customerId);
    }

    /**
     * Finds a customer by their ID.
     *
     * @param id customer identifier
     * @return matching customer, or null if not found
     * @throws IllegalArgumentException if id is not positive
     */
    @Override
    public Customer findCustomerById(int id) {

        ValidationUtils.requirePositive(id, "Customer ID");

        return customerRepository.searchCustomer(id);
    }

    /**
     * Retrieves all registered customers.
     *
     * @return list of all customers
     */
    @Override
    public List<Customer> getAllCustomers() {

        return customerRepository.getCustomers();
    }
}
