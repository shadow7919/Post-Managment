package ir.ac.kntu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company implements Serializable {
    private final List<Customer> customers;
    private final List<Product> products;

    public Company() {
        customers = new ArrayList<>();
        products = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Product> getProducts() {
        products.sort(Comparator.comparing(Product::getDate));
        return new ArrayList<>(products);
    }

    public void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        } else {
            System.out.println("This id has been registered ");
        }
    }

    public void remove(Customer customer) {
        customers.remove(customer);
    }

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        } else {
            System.out.println("This code has been registered");
        }
    }

    public void remove(Product product) {
        if (products.contains(product)) {
            products.remove(product);
        } else {
            System.out.println("This code is not registered");
        }
    }
}
