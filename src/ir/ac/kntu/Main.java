package ir.ac.kntu;

public class Main {
    public static void main(String[] args) {
        start();
    }

    public static void initialize(Company company) {
        MyFile myFile = new FileHelper(company);
        myFile.read();
    }

    public static void start() {
        Company company = new Company();
        initialize(company);
        // goes to the menu
        Menu.start(company);
        finish(company);
    }

    public static void finish(Company company) {
        MyFile file = new FileHelper(company);
        file.write();
    }
}
