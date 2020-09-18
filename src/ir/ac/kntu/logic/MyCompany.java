package ir.ac.kntu.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyCompany implements Serializable, Company {
    private final List<Customer> customers;

    public MyCompany() {
        customers = new ArrayList<>();
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    @Override
    public void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        } else {
            System.out.println("This id has been registered ");
        }
    }

    @Override
    public void remove(Customer customer) {
        if (!customers.contains(customer)) {
            System.out.println("This id has not been registered");
        } else {
            customers.remove(customer);
        }
    }
}
