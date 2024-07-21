package Graphics;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class Competition {
    private String name;
    private String type;
    private List<Animal> animals;

    public Competition(String name, String type) {
        this.name = name;
        this.type = type;
        this.animals = new ArrayList<>();
    }

    // Method to return the current competition object
    public Competition getCompetition() {
        return this;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }
}
