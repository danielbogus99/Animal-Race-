package animals;

import mobility.Mobile;
import mobility.Point;
import Olympics.*;

public class Animal extends Mobile {
    private String name;



   public enum gender {Male, Female, Hermaphrodite}

    ;
    private double weight;
    private double speed;
    private Medal medals[];
    private Point position;
    private gender gender;

    public Animal(Point position, double totalDistance, gender gender, String name, double weight, double speed,Medal medals[]) {
        super(position, totalDistance);
        this.weight = weight;
        this.speed = speed;
        medals = null;
        this.gender = gender;
        this.name = name;

    }

    public void getSound() {

    }

    public void makeSound() {
        System.out.print("Animal " + getName() + " said ");getSound();

    }

    public String getName(){
        return name;
    }
    public String getGender(){
        return gender.name();
    }

}
