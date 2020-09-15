package ir.ac.kntu;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
    private String name;
    private String lastName;
    private int id;
    private Company company;

    public void menu(Company company) {
        this.company = company;
        while (true) {
            printMenu();
            switch (ScannerHelper.nextInt(5)) {
                case 1:
                    add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    remove();
                    break;
                case 4:
                    show();
                    break;
                case 5:
                    return;
                default:
            }
        }
    }

    private void printMenu() {
        System.out.println("1 --> Add customer");
        System.out.println("2 --> Edit customer");
        System.out.println("3 --> remove customer");
        System.out.println("4 --> Show customer");
        System.out.println("5 --> Back");
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
        System.out.print("id : ");
        this.id = ScannerHelper.nextInt();
        company.remove(this);
    }

    private void edit() {
        Customer customer = getCustomer();
        if (customer != null) {
            System.out.println("1 --> Name ");
            System.out.println("2 --> Last name ");
            System.out.println("3 --> Id ");
            switch (ScannerHelper.nextInt(3)) {
                case 1:
                    System.out.print("new Name : ");
                    customer.name = ScannerHelper.getInstance().nextLine();
                    break;
                case 2:
                    System.out.print("new Last Name : ");
                    customer.lastName = ScannerHelper.getInstance().nextLine();
                    break;
                case 3:
                    System.out.print("new Id : ");
                    customer.id = ScannerHelper.nextInt();
                    break;
                default:
            }
        }
    }

    public Customer getCustomer() {
        System.out.print("id : ");
        this.id = ScannerHelper.nextInt();
        for (Customer customer : company.getCustomers()) {
            if (this.equals(customer)) {
                return this;
            }
        }
        System.out.println("This id is not registered");
        return null;
    }

    private void show() {
//        Customer customer = getCustomer();
//        if (customer != null) {
//            System.out.println(customer);
//        }
        for (Customer customer : company.getCustomers()) {
            System.out.println(customer);
        }
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
