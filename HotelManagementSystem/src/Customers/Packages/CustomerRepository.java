package Customers.Packages;

import Customers.Entite.Customer;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class CustomerRepository {
    private ArrayList<Customer> customers=new ArrayList();

    public boolean addCustomer(Customer customer) {
        if (customers.contains(customer)) {
            return false;
        }

        customers.add(customer);
        return true;
    }
    public Customer searchCustomer(int customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .orElse(null);
    }
    public boolean deleteCustomer(int customerId){
        return customers.remove(searchCustomer(customerId));
    }
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
    public List<Customer> getCustomers() {

            return Collections.unmodifiableList(customers);
    }

}
