package ir.ac.kntu;

public class Menu {
    private static void printStartMenu() {
        System.out.println("1 --> Customer ");
        System.out.println("2 --> Order ");
        System.out.println("3 --> End");
    }

    public static void start(Company company) {
        while (true) {
            printStartMenu();
            switch (ScannerHelper.nextInt(2)) {
                case 1:
                    new Customer().menu(company);
                    break;
                case 2:
                    new Product().order(company);
                    break;
                case 3:
                    return;
                default:
            }
        }
    }
}
