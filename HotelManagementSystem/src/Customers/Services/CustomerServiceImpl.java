package Customers.Services;

import Customers.Entite.Customer;
import Customers.Packages.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return customerRepository.addCustomer(customer);


    }

    @Override
    public boolean updateCustomer(int customerId ,Customer customer) {
        return  customerRepository.updateCustomer(customerId,customer);

    }

    @Override
    public boolean deleteCustomer(int customerId) {

        return customerRepository.deleteCustomer(customerId);

    }
    @Override
    public Customer findCustomerById(int id) {
        return customerRepository.searchCustomer(id);
    }

    @Override
    public  List<Customer> getAllCustomers(){

        return customerRepository.getCustomers();

    }

}
