package service;

import model.CoworkingSpace;
import java.util.ArrayList;
import java.util.List;

public class CoworkingSpaceService {
    private List<CoworkingSpace> spaces = new ArrayList<>();
    private int spaceCounter = 1;

    public void addSpace(String type, double price) {
        spaces.add(new CoworkingSpace(spaceCounter++, type, price));
    }

    public boolean removeSpace(int id) {
        return spaces.removeIf(space -> space.getId() == id);
    }

    public List<CoworkingSpace> getSpaces() {
        return spaces;
    }
}
