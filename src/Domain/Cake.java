package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Cake implements Identifiable<Integer>, Comparable<Cake> {
    private Integer id;
    private String type;
    private double price;

    public Cake(int id, String type, double price) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("You must provide a type of cake");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.id = id;
        this.type = type;
        this.price = price;
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        this.id = id;}

    public String getType() {return type;}

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("You must provide a type of cake");
        }
        this.type = type;}

    public double getPrice() {return price;}

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cake cake = (Cake) o;
        return Objects.equals(id, cake.id) && price == cake.price && Objects.equals(type, cake.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price);
    }

    @Override
    public String toString() {
        return " id:" + id + ", " + type + " cake, " + "$" + price;
    }

    @Override
    public int compareTo(Cake o) {
        if (this.type.compareTo(o.getType()) < 0)
            return -1;
        else if (this.type.compareTo(o.getType()) > 0)
            return 1;
        return 0;
    }
}
