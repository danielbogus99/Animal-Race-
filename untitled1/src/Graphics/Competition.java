package Graphics;
import animals.*;
import java.util.ArrayList;
import java.util.List;

public class Competition {
    private String name;
    private List<Animal> animals;

    public Competition(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }
}
