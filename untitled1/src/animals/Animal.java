package animals;

import mobility.Mobile;
import mobility.Point;
import Olympics.*;

import java.util.Arrays;

public class Animal extends Mobile {
    public enum gender {Male, Female, Hermaphrodite}
    private String name;

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

    @Override
    public String toString() {
        return STR."\{super.toString()}, Animal name='\{name}\{'\''}, weight=\{weight}, speed=\{speed}, medals=\{Arrays.toString(medals)}, gender=\{gender},";

    }

    public String getName(){
        return name;
    }
    public String getGender(){
        return gender.name();
    }


}
