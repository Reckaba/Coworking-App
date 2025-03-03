package service;

import model.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private List<Reservation> reservations = new ArrayList<>();
    private int reservationCounter = 1;

    // Get all reservations
    public List<Reservation> getReservations() {
        return reservations;
    }

    // Make a reservation
    public void makeReservation(String customerName, int spaceId, String date, String time) {
        reservations.add(new Reservation(reservationCounter++, customerName, spaceId, date, time));
        System.out.println("Reservation successful!");
    }

    // Cancel a reservation
    public boolean cancelReservation(int id) {
        return reservations.removeIf(reservation -> reservation.getId() == id);
    }
}