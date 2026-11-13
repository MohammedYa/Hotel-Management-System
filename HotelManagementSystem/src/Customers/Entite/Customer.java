
package Customers.Entite;

import Validations.ValidationUtils;

/**
 * Represents a hotel customer.
 * Stores customer identification and contact information.
 */
public class Customer {

    private int customerId;
    private String name;
    private String phone;
    private String email;

    /**
     * Constructs a Customer with the specified information.
     *
     * @param customerId unique customer identifier
     * @param name customer name
     * @param phone customer phone number
     * @param email customer email address
     * @throws IllegalArgumentException if any field violates validation rules
     */
    public Customer(int customerId, String name, String phone, String email) {
        setCustomerId(customerId);
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    /**
     * Gets the customer ID.
     *
     * @return customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId customer identifier
     * @throws IllegalArgumentException if customerId is not positive
     */
    public void setCustomerId(int customerId) {
        ValidationUtils.requirePositive(customerId, "Customer ID");
        this.customerId = customerId;
    }

    /**
     * Gets the customer name.
     *
     * @return customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer name.
     *
     * @param name customer name
     * @throws IllegalArgumentException if name is null or blank
     */
    public void setName(String name) {
        ValidationUtils.requireNotBlank(name, "Customer Name");
        this.name = name;
    }

    /**
     * Gets the customer phone number.
     *
     * @return customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the customer phone number.
     *
     * @param phone customer phone number
     * @throws IllegalArgumentException if phone is null or blank
     */
    public void setPhone(String phone) {
        ValidationUtils.requireNotBlank(phone, "Customer Phone");
        this.phone = phone;
    }

    /**
     * Gets the customer email address.
     *
     * @return customer email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer email address.
     *
     * @param email customer email address
     * @throws IllegalArgumentException if email is null or blank
     */
    public void setEmail(String email) {
        ValidationUtils.requireNotBlank(email, "Customer Email");
        this.email = email;
    }

    /**
     * Compares this customer with another object based on customer ID.
     *
     * @param obj object to compare
     * @return true if both objects represent the same customer
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Customer other = (Customer) obj;
        return customerId == other.customerId;
    }

    /**
     * Generates a hash code based on customer ID.
     *
     * @return hash code of this customer
     */
    @Override
    public int hashCode() {
        return this.customerId;
    }

    /**
     * Returns a formatted representation of customer details.
     *
     * @return formatted customer information
     */
    @Override
    public String toString() {
        return "========== Customer DETAILS ==========\n" +
                "Customer Id     : " + customerId + "\n" +
                "Customer Name   : " + name + "\n" +
                "Customer Number : " + phone + "\n" +
                "Customer Email  : " + email + "\n" +
                "==================================";
    }
}
