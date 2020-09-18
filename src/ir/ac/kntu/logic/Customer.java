package ir.ac.kntu.logic;

import ir.ac.kntu.logic.enums.Type;
import ir.ac.kntu.util.Menu;
import ir.ac.kntu.util.ScannerHelper;

import java.io.Serializable;
import java.util.*;

public class Customer implements Serializable {
    private String name;
    private String lastName;
    private int id;
    private final Map<Product, Type> products;
    private Company company;

    public Customer() {
        products = new HashMap<>();
    }

    public void menu(Company company) {
        while (true) {
            Customer customer = new Customer();
            customer.company = company;
            switch (Menu.printCustomerMenu()) {
                case 1:
                    customer.add();
                    break;
                case 2:
                    customer.edit();
                    break;
                case 3:
                    customer.remove();
                    break;
                case 4:
                    customer.show();
                    break;
                case 5:
                    return;
                default:
            }
        }
    }

    private void add() {
        ScannerHelper.getInstance().nextLine();
        System.out.print("Name : ");
        this.name = ScannerHelper.getInstance().nextLine();
        System.out.print("lastName : ");
        this.lastName = ScannerHelper.getInstance().nextLine();
        System.out.print("id : ");
        this.id = ScannerHelper.nextInt();
        company.addCustomer(this);
    }

    private void remove() {
        Customer customer = getCustomer();
        if (customer != null) {
            company.remove(customer);
        }
    }

    private void edit() {
        Customer customer = getCustomer();
        if (customer != null) {
            switch (Menu.printEditMenu()) {
                case 1:
                    ScannerHelper.getInstance().nextLine();
                    System.out.print("new Name : ");
                    customer.name = ScannerHelper.getInstance().nextLine();
                    break;
                case 2:
                    ScannerHelper.getInstance().nextLine();
                    System.out.print("new Last Name : ");
                    customer.lastName = ScannerHelper.getInstance().nextLine();
                    break;
                case 3:
                    System.out.print("new Id : ");
                    int id = ScannerHelper.nextInt();
                    for (Customer customer1 : company.getCustomers()) {
                        if (customer1.id == id) {
                            System.out.println("This id is been registered");
                            return;
                        }
                    }
                    customer.id = id;
                    break;
                default:
            }
        }
    }

    public Customer getCustomer() {
        System.out.print("id : ");
        int id = ScannerHelper.nextInt();
        for (Customer customer : company.getCustomers()) {
            if (customer.id == id) {
                return customer;
            }
        }
        System.out.println("This id is not registered");
        return null;
    }

    private void show() {
        Customer customer = getCustomer();
        if (customer != null) {
            System.out.println(customer);
        }
    }

    public void addProduct(Product product, Type type) {
        if (products.containsKey(product)) {
            System.out.println("This product is been registered");
            return;
        }
        products.put(product, type);
    }

    public HashMap<Product, Type> getProducts() {
        return new HashMap<>(products);
    }

    public void removeProduct(Product product) {
        for (Product registeredProduct : products.keySet()) {
            System.out.println(registeredProduct);
            if (registeredProduct.equals(product)) {
                products.remove(product);
            }
        }
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return this.name + "\t" + lastName + "\t" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return this.id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
