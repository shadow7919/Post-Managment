package ir.ac.kntu;

public enum Type {
    NORMAL(1), CUSTOM(2);
    private final int multiplier;

    Type(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public static Type choose() {
        System.out.println("choose your method");
        Type[] types = Type.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + " --> " + types[i]);
        }
        int option = ScannerHelper.nextInt(types.length);
        return types[option - 1];
    }
}
