package Graphics;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a competition involving various animals.
 */
public class Competition {
    private String name;
    private String type;
    private List<Animal> animals;
    private ArrayList<Integer> AirPath;
    private ArrayList<Integer> WaterPath;

    /**
     * Constructor to initialize a Competition object.
     *
     * @param name The name of the competition.
     * @param type The type of the competition (e.g., Air, Water, Terrestrial).
     */
    public Competition(String name, String type) {
        this.name = name;
        this.type = type;
        this.animals = new ArrayList<>();
        AirPath = new ArrayList<>();
        AirPath.add(1);
        AirPath.add(2);
        AirPath.add(3);
        AirPath.add(4);
        AirPath.add(5);
        WaterPath = new ArrayList<>();
        WaterPath.add(1);
        WaterPath.add(2);
        WaterPath.add(3);
        WaterPath.add(4);
    }

    /**
     * Gets the current competition instance.
     *
     * @return The current competition instance.
     */
    public Competition getCompetition() {
        return this;
    }

    /**
     * Gets the name of the competition.
     *
     * @return The name of the competition.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the competition.
     *
     * @return The type of the competition.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the list of animals participating in the competition.
     *
     * @return The list of animals.
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Adds an animal to the competition.
     *
     * @param animal The animal to add.
     */
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    /**
     * Gets the list of available air paths for the competition.
     *
     * @return The list of air paths.
     */
    public ArrayList<Integer> getAirPath() {
        return AirPath;
    }

    /**
     * Gets the list of available water paths for the competition.
     *
     * @return The list of water paths.
     */
    public ArrayList<Integer> getWaterPath() {
        return WaterPath;
    }

    /**
     * Deletes a specified water path from the list of available paths.
     *
     * @param path The path to delete.
     */
    public void deleteWaterPath(int path) {
        WaterPath.remove((Integer) path);
    }

    /**
     * Deletes a specified air path from the list of available paths.
     *
     * @param path The path to delete.
     */
    public void deleteAirPath(int path) {
        AirPath.remove((Integer) path);
    }
}
