package Graphics;

import java.util.ArrayList;
import java.util.List;
import animals.*;

/**
 * Represents a regular race, which involves individual animals competing on a specified path.
 */
public class RegularRace {
    private String raceName; // Name of the race
    private String raceType; // Type of the race (e.g., Air, Water, Terrestrial)
    private int racePath; // Path of the race
    private List<Animal> animals; // List of animals participating in the race

    // Static list to hold all created races
    private static List<RegularRace> allRaces = new ArrayList<>();

    /**
     * Constructs a new RegularRace with the specified name, type, and race path.
     *
     * @param raceName The name of the race.
     * @param raceType The type of the race (Air, Water, Terrestrial).
     * @param racePath The path of the race.
     */
    public RegularRace(String raceName, String raceType, int racePath) {
        this.raceName = raceName;
        this.raceType = raceType;
        this.racePath = racePath;
        this.animals = new ArrayList<>();
    }

    /**
     * Gets the name of the race.
     *
     * @return The race name.
     */
    public String getRaceName() {
        return raceName;
    }

    /**
     * Gets the type of the race.
     *
     * @return The race type.
     */
    public String getRaceType() {
        return raceType;
    }

    /**
     * Gets the path of the race.
     *
     * @return The race path.
     */
    public int getRacePath() {
        return racePath;
    }

    /**
     * Sets the path of the race.
     *
     * @param racePath The race path to set.
     */
    public void setRacePath(int racePath) {
        this.racePath = racePath;
    }

    /**
     * Gets the list of animals participating in the race.
     *
     * @return A list of animals in the race.
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Adds a list of animals to the race.
     *
     * @param newAnimals The list of animals to add.
     */
    public void addAnimals(List<Animal> newAnimals) {
        animals.addAll(newAnimals);
    }

    /**
     * Adds the race to the static list of all races.
     *
     * @param race The RegularRace to add.
     */
    public static void addRace(RegularRace race) {
        allRaces.add(race);
    }

    /**
     * Retrieves a list of all created regular races.
     *
     * @return A list of all RegularRace instances.
     */
    public static List<RegularRace> getAllRaces() {
        return allRaces;
    }

    /**
     * Converts the list of animals into a 2D array, with each animal in its own array.
     *
     * @return A 2D array of animals, with each array containing a single animal.
     */
    public Animal[][] toAnimalTeams() {
        Animal[][] animalTeams = new Animal[animals.size()][1];
        for (int i = 0; i < animals.size(); i++) {
            animalTeams[i][0] = animals.get(i);
        }
        return animalTeams;
    }
}
