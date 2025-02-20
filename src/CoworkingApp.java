import java.util.*;

class CoworkingSpace {
    int id;
    String type;
    double price;
    boolean available;

    public CoworkingSpace(int id, String type, double price, boolean available) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Type: " + type + " | Price: $" + price + " | Available: " + (available ? "Yes" : "No");
    }
}

class Reservation {
    int id;
    String customerName;
    int spaceId;
    String date;
    String time;

    public Reservation(int id, String customerName, int spaceId, String date, String time) {
        this.id = id;
        this.customerName = customerName;
        this.spaceId = spaceId;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + id + " | Customer: " + customerName + " | Space ID: " + spaceId + " | Date: " + date + " | Time: " + time;
    }
}

public class CoworkingApp {
    static Scanner scanner = new Scanner(System.in);
    static List<CoworkingSpace> spaces = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static int spaceCounter = 1;
    static int reservationCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 3 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void adminMenu() {
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

    public static void addSpace() {
        System.out.print("Enter space type (Open, Private, Room): ");
        String type = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        spaces.add(new CoworkingSpace(spaceCounter++, type, price, true));
        System.out.println("Coworking space added successfully.");
    }

    public static void removeSpace() {
        System.out.print("Enter space ID to remove: ");
        int id = scanner.nextInt();
        spaces.removeIf(space -> space.id == id);
        System.out.println("Coworking space removed.");
    }

    public static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    public static void customerMenu() {
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
                case 3 -> viewMyReservations();
                case 4 -> cancelReservation();
                case 5 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void browseSpaces() {
        if (spaces.isEmpty()) {
            System.out.println("No coworking spaces available.");
        } else {
            spaces.forEach(System.out::println);
        }
    }

    public static void makeReservation() {
        browseSpaces();
        System.out.print("Enter space ID to book: ");
        int spaceId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM): ");
        String time = scanner.nextLine();

        boolean found = false;
        for (CoworkingSpace space : spaces) {
            if (space.id == spaceId && space.available) {
                space.available = false; // Mark as reserved
                reservations.add(new Reservation(reservationCounter++, name, spaceId, date, time));
                System.out.println("Reservation confirmed.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Invalid Space ID or space is already booked.");
        }
    }

    public static void viewMyReservations() {
        System.out.print("Enter your name to view reservations: ");
        String name = scanner.nextLine();
        reservations.stream()
                .filter(res -> res.customerName.equalsIgnoreCase(name))
                .forEach(System.out::println);
    }

    public static void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Reservation toRemove = null;
        for (Reservation res : reservations) {
            if (res.id == id) {
                toRemove = res;
                break;
            }
        }

        if (toRemove != null) {
            for (CoworkingSpace space : spaces) {
                if (space.id == toRemove.spaceId) {
                    space.available = true;
                    break;
                }
            }

            reservations.remove(toRemove);
            System.out.println("Reservation canceled and space is now available.");
        } else {
            System.out.println("Reservation ID not found.");
        }
    }
}
