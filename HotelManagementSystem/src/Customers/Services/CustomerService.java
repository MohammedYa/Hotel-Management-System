package Customers.Services;

import Customers.Entite.Customer;

import java.util.List;

/**
 * Defines the business operations available for managing customers.
 * Provides methods for creating, updating, deleting, searching,
 * and retrieving customer records.
 */
public interface CustomerService {

    /**
     * Adds a new customer.
     *
     * @param customer the customer to be added
     * @return true if the customer is added successfully; otherwise, false
     */
    boolean addCustomer(Customer customer);

    /**
     * Updates the information of an existing customer.
     *
     * @param customerId the ID of the customer to update
     * @param customer the customer containing the updated information
     * @return true if the customer is updated successfully; otherwise, false
     */
    boolean updateCustomer(int customerId, Customer customer);

    /**
     * Deletes a customer using their unique ID.
     *
     * @param customerId the ID of the customer to delete
     * @return true if the customer is deleted successfully; otherwise, false
     */
    boolean deleteCustomer(int customerId);

    /**
     * Finds a customer using their unique ID.
     *
     * @param id the ID of the customer to find
     * @return the matching customer if found; otherwise, null
     */
    Customer findCustomerById(int id);

    /**
     * Retrieves all customers.
     *
     * @return a list containing all customers
     */
    List<Customer> getAllCustomers();
}