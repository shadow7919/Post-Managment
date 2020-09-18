package ir.ac.kntu.logic;

import ir.ac.kntu.logic.enums.Condition;
import ir.ac.kntu.logic.enums.Kind;
import ir.ac.kntu.logic.enums.Method;
import ir.ac.kntu.logic.enums.Type;
import ir.ac.kntu.util.Menu;
import ir.ac.kntu.util.ScannerHelper;

import java.io.Serializable;
import java.util.*;

public class Product implements Serializable {
    private int code;
    private String name;
    private double weight;
    private double price;
    private Date date;
    private Condition condition;
    private Method method;
    private Kind kind;
    private int howLong;
    private Company company;


    public void menu(Company company) {
        while (true) {
            Product product = new Product();
            product.company = company;
            switch (Menu.printProductMenu()) {
                case 1:
                    product.order();
                    break;
                case 2:
                    product.send();
                    break;
                case 3:
                    product.cancel();
                    break;
                case 4:
                    product.show();
                    break;
                case 5:
                    return;
                default:
            }
        }
    }

    private void order() {
        System.out.print("Enter Order code : ");
        this.code = ScannerHelper.nextInt();
        if (chooseProduct(true) == null) {
            System.out.print("Product name : ");
            ScannerHelper.getInstance().nextLine();
            this.name = ScannerHelper.getInstance().nextLine();
            System.out.print("Enter weight as kg : ");
            this.weight = ScannerHelper.nextDouble();
            this.method = Method.choose();
            this.kind = Kind.choose();
            this.condition = Condition.Unsent;
            this.declarePrice();
            if (this.customers()) {
                this.date = new Date(true);
                System.out.println("How long it takes (max is a week)");
                howLong = ScannerHelper.nextInt(7);
            }
        }
    }

    private Product chooseProduct(boolean creation) {
        int code = this.code;
        if (!creation) {
            System.out.print("Enter Order code : ");
            code = ScannerHelper.nextInt();
        }
        for (Customer customer : company.getCustomers()) {
            for (Product product : customer.getProducts().keySet()) {
                if (product.code == code && customer.getProducts().get(product) == Type.SENDER) {
                    return product;
                }
            }
        }
        return null;
    }

    private void cancel() {
        Product product = chooseProduct(false);
        if (product != null) {
            for (Customer customer : company.getCustomers()) {
                customer.removeProduct(product);
            }
        } else {
            System.out.println("Product is not registered");
        }
    }


    private boolean customers() {
        Customer first = new Customer();
        first.setCompany(company);
        System.out.print("Enter sender ");
        first = first.getCustomer();
        if (first == null) {
            return false;
        }
        System.out.print("Enter receiver ");
        Customer second = first.getCustomer();
        if (second == null || first.equals(second)) {
            return false;
        }
        first.addProduct(this, Type.SENDER);
        second.addProduct(this, Type.RECEIVER);
        if (first.getProducts().size() > 5) {
            this.price *= .9;
        }
        return true;
    }

    private void send() {
        Product product = chooseProduct(false);
        List<Product> sortedProducts = new ArrayList<>();
        if (product != null) {
            for (Customer customer : company.getCustomers()) {
                for (Product product1 : customer.getProducts().keySet()) {
                    if (customer.getProducts().get(product) == Type.SENDER) {
                        sortedProducts.add(product1);
                    }
                }
            }
            sortedProducts.sort(Comparator.comparing(Product::getDate));
            for (Product registeredProduct : sortedProducts) {
                System.out.println(registeredProduct);
                if (product.equals(registeredProduct)) {
                    break;
                }
                registeredProduct.condition = Condition.OnWay;
            }
        }
    }

    private void show() {
        Product product = chooseProduct(false);
        if (product != null) {
            System.out.println("Today's date");
            Date date = new Date(false);
            int different = Date.compareDates(date, product.date);
            if (different < 0) {
                System.out.println("Today's can't be before the day you register order ");
                return;
            }
            if (different > product.howLong && product.condition == Condition.OnWay) {
                product.condition = Condition.Received;
            }
            System.out.println(product);
        }
    }

    private void declarePrice() {
        this.price = this.weight * 1000;
        this.price *= this.kind.getMultiplier();
        this.price *= this.method.getMultiplier();
    }

    public Date getDate() {
        return date;
    }

    public Method getMethod() {
        return method;
    }

    public Condition getCondition() {
        return condition;
    }

    public String toString() {
        return "code : " + code + ", name : " + name + ", sender : " + "\t"
                + date + "\n" + "weight : " + weight + ", price : " + price + ", condition : " + condition
                + ", type : " + kind + ", method : " + method + ", waiting : " + howLong;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return code == product.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
