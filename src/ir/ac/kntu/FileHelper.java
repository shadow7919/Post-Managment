package ir.ac.kntu;

import java.io.*;

/**
 * This class has some duplication try to make it easier
 */
public class FileHelper implements MyFile {
    Company company;

    public FileHelper(Company company) {
        this.company = company;
    }

    public void writeProducts() {
        try (
                FileOutputStream file = new FileOutputStream("products");
                ObjectOutputStream obj = new ObjectOutputStream(file)
        ) {
            for (Product product : company.getProducts()) {
                obj.writeObject(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readProducts() {
        try (
                FileInputStream file = new FileInputStream("products");
                ObjectInputStream obj = new ObjectInputStream(file)) {
            while (true) {
                try {
                    company.addProduct((Product) obj.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCustomers() {
        try (
                FileOutputStream file = new FileOutputStream("customers");
                ObjectOutputStream obj = new ObjectOutputStream(file)
        ) {
            for (Customer customer : company.getCustomers()) {
                obj.writeObject(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readCustomers() {
        try (
                FileInputStream file = new FileInputStream("customers");
                ObjectInputStream obj = new ObjectInputStream(file)) {
            while (true) {
                try {

                    company.addCustomer(((Customer) obj.readObject()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        readCustomers();
        readProducts();
    }

    @Override
    public void write() {
        writeCustomers();
        writeProducts();
    }
}
