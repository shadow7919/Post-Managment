package ir.ac.kntu.logic;

import java.util.List;

public interface Company {

    List<Customer> getCustomers();

    void addCustomer(Customer customer);

    void remove(Customer customer);

}

