package ir.ac.kntu.util;

import ir.ac.kntu.logic.Company;
import ir.ac.kntu.logic.Customer;

import java.io.*;

public class FileHelper implements File {
    Company company;

    public FileHelper(Company company) {
        this.company = company;
    }

    public void writeCustomers() {
        try (FileOutputStream file = new FileOutputStream("customers");
             ObjectOutputStream obj = new ObjectOutputStream(file)) {
            for (Customer customer : company.getCustomers()) {
                obj.writeObject(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readCustomers() {
        try (FileInputStream file = new FileInputStream("customers");
             ObjectInputStream obj = new ObjectInputStream(file)) {
            while (true) {
                try {
                    company.addCustomer((Customer) obj.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (EOFException ignored) {
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        readCustomers();
    }

    @Override
    public void write() {
        writeCustomers();
    }
}
