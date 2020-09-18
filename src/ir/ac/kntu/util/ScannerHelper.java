package ir.ac.kntu.util;

import java.util.Scanner;

public class ScannerHelper {
    private final static Scanner SCANNER = new Scanner(System.in);

    private ScannerHelper() {
    }

    public static Scanner getInstance() {
        return SCANNER;
    }

    /**
     * @return positive integer
     */
    public static int nextInt() {
        while (true) {
            if (SCANNER.hasNextInt()) {
                int input = nextNonNegativeInt();
                if (input > 0) {
                    return input;
                }
                System.out.println("input should be larger than 0 ");
            }
            System.out.println("input should be integer ");
        }
    }

    /**
     * use for menu usually this for menus
     *
     * @param max is the maximum number user can choose
     * @return an integer
     */
    public static int nextInt(int max) {
        while (true) {
            int input = nextInt();
            if (input <= max && input >= 1) {
                return input;
            }
            System.out.println("Input should be smaller than " + max);
        }
    }

    /**
     * @return not negative integer
     */
    public static int nextNonNegativeInt() {
        while (true) {
            if (SCANNER.hasNextInt()) {
                int input = SCANNER.nextInt();
                if (input >= 0) {
                    return input;
                }
                System.out.println("input should positive");
            }
            System.out.println("input should be integer ");
        }
    }

    public static double nextDouble() {
        while (true) {
            if (SCANNER.hasNextDouble()) {
                double input = SCANNER.nextDouble();
                if (input > 0) {
                    return input;
                }
                System.out.println("input should be larger than 0 ");
            }
            System.out.println("input should be double");
        }
    }
}
