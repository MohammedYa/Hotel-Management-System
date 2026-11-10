package Customers.Services;

import Customers.Entite.Customer;

import java.util.List;

public interface CustomerService {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(int customerId,Customer customer);
    boolean deleteCustomer(int customerId);
    Customer  findCustomerById(int id);
    List<Customer> getAllCustomers();
}
