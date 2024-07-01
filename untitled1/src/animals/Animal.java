package animals;

import mobility.Mobile;
import mobility.Point;
import Olympics.*;

public class Animal extends Mobile {
    private String name;

    enum gender {Male, Female, Hermaphrodite}

    ;
    private double weight;
    private double speed;
    private String medals[];
    private String position;

    public Animal(Point location, double totalDistance, gender gender, String name, double weight, double speed, String position) {
        super(location, totalDistance);
        this.weight = weight;
        this.speed = speed;
        medals = null;
        this.position = position;
        this.name = name;

    }
    public String sound()
    {
        return "sound";
    }
    public void makeSound() {
        System.out.println("Animal " + getName() + " said");
    }

    public String getName(){
        return name;
    }

}
