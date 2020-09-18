package ir.ac.kntu.logic;

import ir.ac.kntu.logic.enums.Condition;
import ir.ac.kntu.logic.enums.Method;
import ir.ac.kntu.logic.enums.Type;
import ir.ac.kntu.util.Menu;

public class Search {
    private Company company;

    public void menu(Company company) {
        while (true) {
            this.company = company;
            switch (Menu.printSearchMenu()) {
                case 1:
                    showCustomers();
                    break;
                case 2:
                    showProducts();
                    break;
                case 3:
                    return;
                default:
            }
        }
    }

    private void showProducts() {
        while (true) {
            switch (Menu.printShowProductMenu()) {
                case 1:
                    productsMethod();
                    break;
                case 2:
                    productsCondition();
                    break;
                case 3:
                    productsCustomer();
                    break;
                case 4:
                    productsAll();
                    break;
                case 5:
                    return;
                default:
            }
        }
    }

    private void productsMethod() {
        Method method = Method.choose();
        for (Customer customer : company.getCustomers()) {
            for (Product product : customer.getProducts().keySet()) {
                if (product.getMethod() == method && customer.getProducts().get(product) == Type.SENDER) {
                    System.out.println(product);
                }
            }
        }
    }

    private void productsAll() {
        for (Customer customer : company.getCustomers()) {
            for (Product product : customer.getProducts().keySet()) {
                if (customer.getProducts().get(product) == Type.SENDER) {
                    System.out.println(product);
                }
            }
        }
    }

    private void productsCondition() {
        Condition condition = Condition.choose();
        for (Customer customer : company.getCustomers()) {
            for (Product product : customer.getProducts().keySet()) {
                if (product.getCondition() == condition && customer.getProducts().get(product) == Type.SENDER) {
                    System.out.println(product);
                }
            }
        }
    }

    private void productsCustomer() {
        Customer customer = new Customer();
        customer.setCompany(company);
        customer = customer.getCustomer();
        for (Product product : customer.getProducts().keySet()) {
            if (customer.getProducts().get(product) == Type.SENDER) {
                System.out.println(product);
            }
        }
    }

    private void showCustomers() {
        for (Customer customer : company.getCustomers()) {
            System.out.println(customer);
        }
    }
}
