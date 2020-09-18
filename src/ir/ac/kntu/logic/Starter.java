package ir.ac.kntu.logic;

import ir.ac.kntu.util.File;
import ir.ac.kntu.util.FileHelper;
import ir.ac.kntu.util.Menu;

public class Starter {
    Company company;
    File file;

    public Starter() {
        company = new MyCompany();
        file = new FileHelper(company);
    }

    public void start() {
        initialize();
        Menu.menu(company);
        finish();
    }

    public void initialize() {
        file.read();
    }

    public void finish() {
        file.write();
    }
}
