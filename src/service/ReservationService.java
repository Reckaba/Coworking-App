package service;

import model.Reservation;
import exception.ReservationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private List<Reservation> reservations = new ArrayList<>();
    private int reservationCounter = 1;
    private static final String FILE_NAME = "reservations.dat";

    public ReservationService() {
        loadReservationsFromFile();
    }

    // Get all reservations
    public List<Reservation> getReservations() {
        return reservations;
    }

    // Make a reservation with exception handling
    public void makeReservation(String customerName, int spaceId, String date, String time) throws ReservationException {
        if (spaceId <= 0) {
            throw new ReservationException("Invalid Space ID.");
        }
        reservations.add(new Reservation(reservationCounter++, customerName, spaceId, date, time));
        System.out.println("Reservation successful!");
        saveReservationsToFile();
    }

    // Cancel a reservation
    public boolean cancelReservation(int id) {
        boolean removed = reservations.removeIf(reservation -> reservation.getId() == id);
        if (removed) {
            saveReservationsToFile();
        }
        return removed;
    }

    // Save reservations to a file
    private void saveReservationsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(reservations);
        } catch (IOException e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }

    // Load reservations from a file
    private void loadReservationsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            reservations = (List<Reservation>) in.readObject();
            System.out.println("Reservations loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous reservations found.");
        }
    }
}
