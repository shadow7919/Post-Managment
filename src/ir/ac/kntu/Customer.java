package ir.ac.kntu;

import java.util.Objects;

public class Customer {
    private String name;
    private String lastName;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return this.id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
