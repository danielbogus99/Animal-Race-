package Graphics;

import java.util.ArrayList;
import java.util.List;
import animals.*;

public class RegularRace {
    private String raceName;
    private String raceType;
    private List<Animal> animals;

    // Static list to hold all created races
    private static List<RegularRace> allRaces = new ArrayList<>();

    public RegularRace(String raceName, String raceType) {
        this.raceName = raceName;
        this.raceType = raceType;
        this.animals = new ArrayList<>();
    }

    public String getRaceName() {
        return raceName;
    }

    public String getRaceType() {
        return raceType;
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

    // Static method to add a race to the list of all races
    public static void addRace(RegularRace race) {
        allRaces.add(race);
    }

    // Static method to get all races
    public static List<RegularRace> getAllRaces() {
        return allRaces;
    }
}
