package Graphics;
import animals.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourierRace {
    private String name;
    private String type;
    private List<Animal> animals;

    // Static list to store all created races
    private static List<CourierRace> allRaces = new ArrayList<>();

    // Constructor
    public CourierRace(String name, String type) {
        this.name = name;
        this.type = type;
        this.animals = new ArrayList<>();
    }

    // Method to add animals to the race
    public void addAnimals(List<Animal> animals) {
        this.animals.addAll(animals);
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

    // Method to get the list of animals in the race
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    // Method to return the animals grouped by their group in a 2D array
    public Animal[][] getAnimalGroups() {
        Map<String, List<Animal>> groupMap = groupAnimalsByGroup();

        // Create a 2D array to store animal groups
        Animal[][] animalGroups = new Animal[groupMap.size()][];
        int index = 0;

        // Populate the 2D array with groups of animals
        for (List<Animal> group : groupMap.values()) {
            animalGroups[index++] = group.toArray(new Animal[0]);
        }

        return animalGroups;
    }

    // Helper method to group animals by their group key
    private Map<String, List<Animal>> groupAnimalsByGroup() {
        Map<String, List<Animal>> groupMap = new HashMap<>();

        // Assuming each animal has a method getGroupName() to get its group name
        for (Animal animal : animals) {
            String groupName = animal.getAnimaleName(); // Adjust this as per your Animal class implementation
            groupMap.computeIfAbsent(groupName, k -> new ArrayList<>()).add(animal);
        }

        return groupMap;
    }

    // Method to print race details
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CourierRace{name='").append(name).append("', type='").append(type).append("', groups=\n");

        // Get groups of animals
        Map<String, List<Animal>> groupMap = groupAnimalsByGroup();

        for (Map.Entry<String, List<Animal>> entry : groupMap.entrySet()) {
            sb.append("  Group '").append(entry.getKey()).append("':\n");

            for (Animal animal : entry.getValue()) {
                sb.append("    - ").append(animal.getAnimaleName()).append(" (").append(animal.animalType()).append(")\n");
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
