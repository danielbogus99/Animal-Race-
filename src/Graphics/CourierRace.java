package Graphics;
import animals.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourierRace {
    private String name;
    private String type;
    private int racePath; // New field to store the path
    private Map<String, List<Animal>> groups;

    // Static list to store all created races
    private static List<CourierRace> allRaces = new ArrayList<>();

    // Constructor
    public CourierRace(String name, String type, int racePath) { // Updated constructor
        this.name = name;
        this.type = type;
        this.racePath = racePath; // Store the path
        this.groups = new HashMap<>();
    }

    // Method to add a group of animals to the race
    public void addGroup(String groupName, List<Animal> animals) {
        groups.put(groupName, new ArrayList<>(animals));
    }

    // Method to add the race to the list of all races
    public static void addRace(CourierRace race) {
        allRaces.add(race);
    }

    // Method to retrieve all created races
    public static List<CourierRace> getAllRaces() {
        return new ArrayList<>(allRaces);
    }

    // Method to get the name of the race
    public String getName() {
        return name;
    }

    // Method to get the type of the race
    public String getType() {
        return type;
    }

    // Method to get the path of the race
    public int getRacePath() {
        return racePath;
    }

    // Method to set the path of the race
    public void setRacePath(int racePath) {
        this.racePath = racePath;
    }

    // Method to get the list of all animals in the race
    public List<Animal> getAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (List<Animal> group : groups.values()) {
            allAnimals.addAll(group);
        }
        return allAnimals;
    }

    // Method to return the animals grouped by their group in a 2D array
    public Animal[][] getAnimalGroups() {
        Animal[][] animalGroups = new Animal[groups.size()][];
        int index = 0;

        // Populate the 2D array with groups of animals
        for (List<Animal> group : groups.values()) {
            animalGroups[index++] = group.toArray(new Animal[0]);
        }

        return animalGroups;
    }

    // Method to print race details
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CourierRace{name='").append(name).append("', type='").append(type).append("', path=").append(racePath).append(", groups=\n");

        // Get groups of animals
        for (Map.Entry<String, List<Animal>> entry : groups.entrySet()) {
            sb.append("  Group '").append(entry.getKey()).append("':\n");

            for (Animal animal : entry.getValue()) {
                sb.append("    - ").append(animal.getAnimalName()).append(" (").append(animal.animalType()).append(")\n");
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
