package Customers.Packages;

import Customers.Entite.Customer;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Repository responsible for managing customer data in memory.
 * Provides operations to add, search, update, delete, and retrieve customers.
 */
public class CustomerRepository {

    private ArrayList<Customer> customers = new ArrayList();

    /**
     * Adds a new customer to the repository.
     *
     * @param customer the customer to be added
     * @return true if the customer is added successfully;
     *         false if the customer already exists
     */
    public boolean addCustomer(Customer customer) {
        if (customers.contains(customer)) {
            return false;
        }

        customers.add(customer);
        return true;
    }

    /**
     * Searches for a customer using their unique ID.
     *
     * @param customerId the ID of the customer to search for
     * @return the matching customer if found; otherwise, null
     */
    public Customer searchCustomer(int customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Deletes a customer from the repository using their ID.
     *
     * @param customerId the ID of the customer to delete
     * @return true if the customer is successfully removed;
     *         false if no matching customer exists
     */
    public boolean deleteCustomer(int customerId) {
        return customers.remove(searchCustomer(customerId));
    }

    /**
     * Updates the name, email, and phone number of an existing customer.
     *
     * @param customerId the ID of the customer to update
     * @param updatedCustomer the customer containing the updated information
     * @return true if the customer is found and updated; otherwise, false
     */
    public boolean updateCustomer(int customerId, Customer updatedCustomer) {
        Customer customer = searchCustomer(customerId);

        if (customer == null) {
            return false;
        }

        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());

        return true;
    }

    /**
     * Retrieves an unmodifiable view of all customers in the repository.
     *
     * @return an unmodifiable list containing all customers
     */
    public List<Customer> getCustomers() {

        return Collections.unmodifiableList(customers);
    }

}