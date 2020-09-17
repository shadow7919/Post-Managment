package ir.ac.kntu;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private String name;
    private int code;
    private Customer sender;
    private Customer receiver;
    //    private City origin;
//    private City destination; define later
    private double weight;
    private double price;
    private Date date;
    private Condition condition;
    private Method method;
    private Type type;
    private Company company;

    public Product(Company company) {
        this.company = company;
    }


    public void menu() {
        while (true) {
            Product product = new Product(company);
            printMenu();
            switch (ScannerHelper.nextInt(5)) {
                case 1:
                    product.order();
                    break;
                case 2:
                    product.send();
                    break;
                case 3:
//                    edit();
                    break;
                case 4:
//                    cancel();
                    break;
                case 5:
                    return;
                default:
            }
        }
    }

    private void printMenu() {
        System.out.println("1 --> Add order");
        System.out.println("2 --> Send order");
        System.out.println("3 --> Edit order");
        System.out.println("4 --> cancel customer");
        System.out.println("5 --> Back");
    }

    private boolean chooseProduct() {
        System.out.print("Enter Order code : ");
        code = ScannerHelper.nextInt();
        if (getOrder() == null) {
            return true;
        }
        System.out.println("This code is registered");
        return false;
    }

    private void order() {
        if (chooseProduct()) {
            ScannerHelper.getInstance().nextLine();
            System.out.print("Product name : ");
            this.name = ScannerHelper.getInstance().nextLine();
            if (this.customers()) {
                System.out.print("Enter weight as kg : ");
                this.weight = ScannerHelper.nextDouble();
                this.method = Method.choose();
                this.type = Type.choose();
                declarePrice();
                this.condition = Condition.Unsent;
                this.date = new Date(true);
                company.addProduct(this);
            }
        }
    }

    private Product getOrder() {
        for (Product product : company.getProducts()) {
            if (this.equals(product)) {
                return product;
            }
        }
        return null;
    }

    private boolean customers() {
        System.out.print("Enter sender ");
        this.sender = new Customer(company).getCustomer();
        if (sender == null) {
            return false;
        }
        System.out.print("Enter receiver ");
        this.receiver = new Customer(company).getCustomer();
        if (receiver == null) {
            return false;
        }
        return true;
    }

    public void send() {
        for (Product product : company.getProducts()) {
            System.out.println(product);
            System.out.println("-------------------------");
        }
    }

    // Add km to this too later
    private void declarePrice() {
        this.price = this.weight * 1000;
        this.price *= this.type.getMultiplier();
        this.price *= this.method.getMultiplier();
        // each km get 100
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "code : " + code + ", name : " + name + ", sender : " + sender + ", receiver : " + receiver + "\t"
                + date + "\n" + "weight : " + weight + ", price : " + price + ", condition : " + condition
                + ", type : " + type + ", method : " + method;
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
