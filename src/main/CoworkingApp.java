package main;

import model.CoworkingSpace;
import model.Reservation;
import service.CoworkingSpaceService;
import service.ReservationService;
import java.util.Scanner;
import java.util.List;


public class CoworkingApp {
    private static Scanner scanner = new Scanner(System.in);
    private static CoworkingSpaceService spaceService = new CoworkingSpaceService();
    private static ReservationService reservationService = new ReservationService();

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("\nMain Menu:");
                System.out.println("1. Admin Login");
                System.out.println("2. User Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> adminMenu();
                    case 2 -> customerMenu();
                    case 3 -> {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add a coworking space");
            System.out.println("2. Remove a coworking space");
            System.out.println("3. View all reservations");
            System.out.println("4. Back to main menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addSpace();
                case 2 -> removeSpace();
                case 3 -> viewReservations();
                case 4 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addSpace() {
        System.out.print("Enter space type (Open, Private, Room): ");
        String type = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        spaceService.addSpace(type, price);
    }

    private static void removeSpace() {
        System.out.print("Enter space ID to remove: ");
        int id = scanner.nextInt();
        if (spaceService.removeSpace(id)) {
            System.out.println("Coworking space removed.");
        } else {
            System.out.println("Space ID not found.");
        }
    }

    private static void viewReservations() {
        List<Reservation> reservations = reservationService.getReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }


    private static void customerMenu() {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Browse available spaces");
            System.out.println("2. Make a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Cancel a reservation");
            System.out.println("5. Back to main menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> browseSpaces();
                case 2 -> makeReservation();
                case 3 -> viewReservations();
                case 4 -> cancelReservation();
                case 5 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void browseSpaces() {
        if (spaceService.getSpaces().isEmpty()) {
            System.out.println("No coworking spaces available.");
        } else {
            spaceService.getSpaces().forEach(System.out::println);
        }
    }

    private static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter space ID to book: ");
        int spaceId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM): ");
        String time = scanner.nextLine();
        reservationService.makeReservation(name, spaceId, date, time);
    }

    private static void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        int id = scanner.nextInt();
        if (reservationService.cancelReservation(id)) {
            System.out.println("Reservation cancelled.");
        } else {
            System.out.println("Reservation ID not found.");
        }
    }
}
