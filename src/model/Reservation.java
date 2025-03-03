package model;

public class Reservation {
    private int id;
    private String customerName;
    private int spaceId;
    private String date;
    private String time;

    public Reservation(int id, String customerName, int spaceId, String date, String time) {
        this.id = id;
        this.customerName = customerName;
        this.spaceId = spaceId;
        this.date = date;
        this.time = time;
    }

    public int getId() {  // Added this method
        return id;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + id + " | Customer: " + customerName + " | Space ID: " + spaceId + " | Date: " + date + " | Time: " + time;
    }
}
