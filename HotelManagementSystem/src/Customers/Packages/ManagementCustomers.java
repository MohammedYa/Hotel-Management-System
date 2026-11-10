package Customers.Packages;

import Customers.Entite.Customer;
import Customers.Services.CustomerService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManagementCustomers {

    private final CustomerService customerService;
    CustomerMenu customerMenu = new CustomerMenu();
    Scanner scanner = new Scanner(System.in);

    public ManagementCustomers(CustomerService customerService) {
        this.customerService = customerService;
    }



    public  void Start(){
      boolean  flag=true;
      int tryChoiceCount=0;
        while (flag) {
            try {
                customerMenu.displayMenu();
                System.out.print("  Enter Your Choice: ");
                int choice = scanner.nextInt();
                switch(choice){
                    case 0:
                        flag=false;
                        break;
                    case 1:
                        tryChoiceCount=0;
                        addCustomer();
                        break;
                    case 2:
                        tryChoiceCount=0;
                        displayAllCustomers();
                        break;
                    case 3:
                        tryChoiceCount=0;
                        displayCustomerById();
                        break;
                    case 4:
                        tryChoiceCount=0;
                        updateCustomer();
                        break;
                    case 5:
                        tryChoiceCount=0;
                        deleteCustomer();
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


    private void addCustomer() {
        System.out.print("\n==========Add Customer Page ==========\n");

        System.out.print("Enter the Customer Id: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter the Customer Phone: ");
        String customerPhone = scanner.next();
        System.out.print("Enter the Customer Email: ");
        String customerEmail = scanner.next();


        Customer customer = new Customer(customerId, customerName, customerPhone, customerEmail);
        if (customerService.addCustomer(customer)){
            System.out.println("Customer added successfully!");
        }
        else {
            System.out.println("Customer not added  ---  Customer is already Exist!");
        }

    }

    private  void displayAllCustomers() {
        System.out.print("\n==========All Customers Page ==========\n");

        List<Customer> customers = customerService.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("There are no customers in the system!");
        } else {
            customers.forEach(System.out::println);
        }

    }

    private  void displayCustomerById() {
        System.out.print("\n==========Display Customers By Id Page ==========\n");

        System.out.print("Enter Customer Id: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        Customer customer = customerService.findCustomerById(customerId);

        if(customer==null){
            System.out.println("Customer not found!");
        }
        else {
            System.out.println(customer);
        }

    }

    private void deleteCustomer() {
        System.out.print("\n==========Delete Customers By Id Page ==========\n");

        System.out.print("Enter the Customer Id: ");
        int customerId = scanner.nextInt();
        if (customerService.deleteCustomer(customerId)){
            System.out.println("Customer deleted successfully!");
        }
        else {
            System.out.println("Customer not deleted  ---  Customer is not exist!");
        }
    }

    private void updateCustomer() {
        System.out.print("\n==========Update Customers By Id Page ==========\n");

        System.out.print("Enter the Customer Id: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        Customer customer = customerService.findCustomerById(customerId);
        if (customer!=null){
            System.out.print("Enter the Customer Name: ");
            String customerName = scanner.nextLine();
            System.out.print("Enter the Customer Phone: ");
            String customerPhone = scanner.nextLine();
            System.out.print("Enter the Customer Email: ");
            String customerEmail = scanner.nextLine();
            Customer customerUpdate = new Customer(customerId, customerName, customerPhone, customerEmail);
            if (customerService.updateCustomer(customerId, customerUpdate)){
                System.out.println("Customer updated successfully!");
            }

        }
        else {
            System.out.println("Customer not updated  ---  Customer is not exist!");
        }
    }


}
