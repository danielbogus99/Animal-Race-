package Graphics;

import animals.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the groups of animals participating in competitions.
 * Implements a singleton pattern to ensure only one instance of the manager is created.
 */
public class CompetitionManager {
    private static CompetitionManager instance; // Singleton instance
    private Map<String, List<Animal>> groupMap; // Map to store groups of animals

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the group map.
     */
    private CompetitionManager() {
        groupMap = new HashMap<>();
    }

    /**
     * Returns the singleton instance of the CompetitionManager.
     * Creates the instance if it does not exist.
     *
     * @return The singleton instance of CompetitionManager.
     */
    public static CompetitionManager getInstance() {
        if (instance == null) {
            instance = new CompetitionManager();
        }
        return instance;
    }

    /**
     * Returns the map of animal groups.
     *
     * @return A map containing group names as keys and lists of animals as values.
     */
    public Map<String, List<Animal>> getGroupMap() {
        return groupMap;
    }

    /**
     * Adds a new group of animals to the manager.
     *
     * @param groupName The name of the group.
     * @param animals   The list of animals in the group.
     */
    public void addGroup(String groupName, List<Animal> animals) {
        groupMap.put(groupName, animals);
    }

    /**
     * Removes an animal from all groups.
     *
     * @param animal The animal to be removed.
     */
    public void removeAnimalFromAllGroups(Animal animal) {
        for (List<Animal> group : groupMap.values()) {
            group.remove(animal);
        }
    }

    /**
     * Clears all groups from the manager.
     */
    public void clearGroups() {
        groupMap.clear();
    }
}
