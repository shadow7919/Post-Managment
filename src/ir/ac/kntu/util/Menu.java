package ir.ac.kntu.util;

import ir.ac.kntu.logic.Company;
import ir.ac.kntu.logic.Customer;
import ir.ac.kntu.logic.Product;
import ir.ac.kntu.logic.Search;

public class Menu {
    public static void menu(Company company) {
        while (true) {
            switch (printStartMenu()) {
                case 1:
                    new Customer().menu(company);
                    break;
                case 2:
                    new Product().menu(company);
                    break;
                case 3:
                    new Search().menu(company);
                    break;
                case 4:
                    return;
                default:
            }
        }
    }

    private static int printStartMenu() {
        System.out.println("1 --> Customer ");
        System.out.println("2 --> Order ");
        System.out.println("3 --> Search");
        System.out.println("4 --> End");
        return ScannerHelper.nextInt(4);
    }

    public static int printCustomerMenu() {
        System.out.println("1 --> Add customer");
        System.out.println("2 --> Edit customer");
        System.out.println("3 --> remove customer");
        System.out.println("4 --> Show customer");
        System.out.println("5 --> Back");
        return ScannerHelper.nextInt(5);
    }

    public static int printEditMenu() {
        System.out.println("1 --> Name ");
        System.out.println("2 --> Last name ");
        System.out.println("3 --> Id ");
        return ScannerHelper.nextInt(3);
    }

    public static int printProductMenu() {
        System.out.println("1 --> Add order");
        System.out.println("2 --> Send order");
        System.out.println("3 --> cancel order");
        System.out.println("4 --> show order condition");
        System.out.println("5 --> Back");
        return ScannerHelper.nextInt(5);
    }

    public static int printSearchMenu() {
        System.out.println("1 --> Show Customers");
        System.out.println("2 --> Show Products");
        System.out.println("3 --> Back");
        return ScannerHelper.nextInt(3);
    }

    public static int printShowProductMenu() {
        System.out.println("1 --> By Method");
        System.out.println("2 --> By Condition");
        System.out.println("3 --> By Customer");
        System.out.println("4 --> All");
        System.out.println("5 --> Back");
        return ScannerHelper.nextInt(5);
    }
}
