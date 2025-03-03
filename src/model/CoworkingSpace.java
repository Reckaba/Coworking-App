package model;

public class CoworkingSpace {
    private int id;
    private String type;
    private double price;

    public CoworkingSpace(int id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public int getId() {  // Added this method
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Type: " + type + " | Price: $" + price;
    }
}
