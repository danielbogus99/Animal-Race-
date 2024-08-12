package Graphics;

import java.util.ArrayList;
import java.util.List;
import animals.*;

public class RegularRace {
    private String raceName;
    private String raceType;
    private int racePath; // Add this field to store the path
    private List<Animal> animals;

    // Static list to hold all created races
    private static List<RegularRace> allRaces = new ArrayList<>();

    public RegularRace(String raceName, String raceType, int racePath) { // Update constructor
        this.raceName = raceName;
        this.raceType = raceType;
        this.racePath = racePath;
        this.animals = new ArrayList<>();
    }

    public String getRaceName() {
        return raceName;
    }

    public String getRaceType() {
        return raceType;
    }

    public int getRacePath() {
        return racePath; // Getter for race path
    }

    public void setRacePath(int racePath) {
        this.racePath = racePath; // Setter for race path
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addAnimals(List<Animal> newAnimals) {
        animals.addAll(newAnimals);
    }

    public static void addRace(RegularRace race) {
        allRaces.add(race);
    }

    public static List<RegularRace> getAllRaces() {
        return allRaces;
    }

    public Animal[][] toAnimalTeams() {
        Animal[][] animalTeams = new Animal[animals.size()][1];
        for (int i = 0; i < animals.size(); i++) {
            animalTeams[i][0] = animals.get(i);
        }
        return animalTeams;
    }
}
