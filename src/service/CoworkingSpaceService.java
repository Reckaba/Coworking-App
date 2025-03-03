package service;

import model.CoworkingSpace;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CoworkingSpaceService {
    private List<CoworkingSpace> spaces = new ArrayList<>();
    private int spaceCounter = 1;
    private static final String FILE_NAME = "spaces.dat";

    public CoworkingSpaceService() {
        loadSpacesFromFile();
    }

    public void addSpace(String type, double price) {
        spaces.add(new CoworkingSpace(spaceCounter++, type, price));
        saveSpacesToFile();
    }

    public boolean removeSpace(int id) {
        boolean removed = spaces.removeIf(space -> space.getId() == id);
        if (removed) {
            saveSpacesToFile();
        }
        return removed;
    }

    public List<CoworkingSpace> getSpaces() {
        return spaces;
    }

    // Save coworking spaces to a file
    private void saveSpacesToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(spaces);
        } catch (IOException e) {
            System.out.println("Error saving coworking spaces: " + e.getMessage());
        }
    }

    // Load coworking spaces from a file
    private void loadSpacesFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            spaces = (List<CoworkingSpace>) in.readObject();
            System.out.println("Coworking spaces loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous coworking spaces found.");
        }
    }
}
