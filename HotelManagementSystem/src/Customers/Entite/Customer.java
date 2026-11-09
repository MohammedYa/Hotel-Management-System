package Customers.Entite;

import Rooms.entite.Room;

public class Customer {
    private int customerId;
    private String name;
    private String phone;
    private String email;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Customer(int customerId, String name,
                    String phone, String email) {
        setCustomerId(customerId);
        setName(name);
        setPhone(phone);
        setEmail(email);
    }





    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Customer other = (Customer) obj;
        if (customerId != other.customerId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

        return  this.customerId;
    }

    @Override
    public String toString() {
        return "========== Customer DETAILS ==========\n" +
                "Customer Id     : " + customerId + "\n" +
                "Customer Name       : " + name + "\n" +
                "Customer Number     : " + phone + "\n" +
                "Customer Email : " + email + "\n" +
                "==================================";
    }

}
