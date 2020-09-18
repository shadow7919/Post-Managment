package ir.ac.kntu.logic.enums;

import ir.ac.kntu.util.ScannerHelper;

public enum Kind {
    NORMAL(1), CUSTOM(2);
    private final int multiplier;

    Kind(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public static Kind choose() {
        System.out.println("choose your method");
        Kind[] kinds = Kind.values();
        for (int i = 0; i < kinds.length; i++) {
            System.out.println((i + 1) + " --> " + kinds[i]);
        }
        int option = ScannerHelper.nextInt(kinds.length);
        return kinds[option - 1];
    }
}
