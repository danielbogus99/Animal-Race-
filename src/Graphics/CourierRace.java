package Graphics;

import animals.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a courier race, which consists of multiple groups of animals competing on a specified path.
 */
public class CourierRace {
    private String name; // Name of the race
    private String type; // Type of the race (e.g., Air, Water, Terrestrial)
    private int racePath; // Path of the race
    private Map<String, List<Animal>> groups; // Map of group names to their respective animals

    // Static list to store all created races
    private static List<CourierRace> allRaces = new ArrayList<>();

    /**
     * Constructs a new CourierRace with the specified name, type, and race path.
     *
     * @param name     The name of the race.
     * @param type     The type of the race (Air, Water, Terrestrial).
     * @param racePath The path of the race.
     */
    public CourierRace(String name, String type, int racePath) {
        this.name = name;
        this.type = type;
        this.racePath = racePath;
        this.groups = new HashMap<>();
    }

    /**
     * Adds a group of animals to the race.
     *
     * @param groupName The name of the group.
     * @param animals   The list of animals in the group.
     */
    public void addGroup(String groupName, List<Animal> animals) {
        groups.put(groupName, new ArrayList<>(animals));
    }

    /**
     * Adds the race to the static list of all races.
     *
     * @param race The CourierRace to add.
     */
    public static void addRace(CourierRace race) {
        allRaces.add(race);
    }

    /**
     * Retrieves a list of all created races.
     *
     * @return A list of all CourierRace instances.
     */
    public static List<CourierRace> getAllRaces() {
        return new ArrayList<>(allRaces);
    }

    /**
     * Gets the name of the race.
     *
     * @return The name of the race.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the race.
     *
     * @return The type of the race.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the path of the race.
     *
     * @return The path of the race.
     */
    public int getRacePath() {
        return racePath;
    }

    /**
     * Sets the path of the race.
     *
     * @param racePath The path to set.
     */
    public void setRacePath(int racePath) {
        this.racePath = racePath;
    }

    /**
     * Gets a list of all animals participating in the race.
     *
     * @return A list of all animals in the race.
     */
    public List<Animal> getAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (List<Animal> group : groups.values()) {
            allAnimals.addAll(group);
        }
        return allAnimals;
    }

    /**
     * Returns the animals grouped by their group in a 2D array.
     *
     * @return A 2D array of animals grouped by their respective groups.
     */
    public Animal[][] getAnimalGroups() {
        Animal[][] animalGroups = new Animal[groups.size()][];
        int index = 0;

        for (List<Animal> group : groups.values()) {
            animalGroups[index++] = group.toArray(new Animal[0]);
        }

        return animalGroups;
    }

    /**
     * Returns a string representation of the race, including its name, type, path, and groups.
     *
     * @return A string representation of the CourierRace.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CourierRace{name='").append(name).append("', type='").append(type).append("', path=").append(racePath).append(", groups=\n");

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
