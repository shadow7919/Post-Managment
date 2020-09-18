package ir.ac.kntu.logic.enums;

import ir.ac.kntu.util.ScannerHelper;

public enum Condition {
    OnWay, Unsent, Received;

    public static Condition choose() {
        Condition[] conditions = Condition.values();
        for (int i = 0; i < conditions.length; i++) {
            System.out.println((i + 1) + " --> " + conditions[i]);
        }
        int option = ScannerHelper.nextInt(conditions.length);
        return conditions[option - 1];
    }
}
