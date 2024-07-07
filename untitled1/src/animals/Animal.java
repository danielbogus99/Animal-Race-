package animals;

import mobility.Mobile;
import mobility.Point;
import Olympics.Medal;

import java.util.Arrays;

/**
 * Represents an animal with various attributes.
 */
public abstract class Animal extends Mobile {

    /**
     * Enumeration representing gender of the animal.
     */
    public enum gender {Male, Female, Hermaphrodite}

    private String name;
    private double weight;
    private double speed;
    private Medal medals[];
    private gender gender;

    /**
     * Constructor to initialize an Animal object.
     *
     * @param position     The initial position of the animal.
     * @param totalDistance The total distance covered by the animal.
     * @param gender       The gender of the animal.
     * @param name         The name of the animal.
     * @param weight       The weight of the animal.
     * @param speed        The speed of the animal.
     * @param medals       The array of medals won by the animal.
     */
    public Animal(Point position, double totalDistance, gender gender, String name, double weight, double speed, Medal medals[]) {
        super(position, totalDistance);
        this.weight = weight;
        this.speed = speed;
        this.medals = null; // Corrected initialization here
        this.gender = gender;
        this.name = name;
    }
    public Animal() {
        super();
        this.weight = 0;
        this.speed = 0;
        this.medals = null;
        this.gender = gender;
        this.name = "";
    }

    /**
     * Method to get the sound of the animal.
     */
    abstract protected String getSound();

    /**
     * Method to make the animal produce its sound.
     */
    public void makeSound() {
        System.out.println(STR."Animal \{getName()} said \{getSound()}");
    }

    /**
     * Override of the toString method to provide a string representation of the Animal object.
     *
     * @return A string representation of the Animal object.
     */
    @Override
    public String toString() {
        return STR."\{super.toString()}, Animal name='\{name}\{'\''}, weight=\{weight}, speed=\{speed}, medals=\{Arrays.toString(medals)}, gender=\{gender},";

    }

    /**
     * Method to get the name of the animal.
     *
     * @return The name of the animal.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the gender of the animal.
     *
     * @return The gender of the animal.
     */
    public String getGender() {
        return gender.name();
    }

    /**
     * Method to get the weight of the animal.
     *
     * @return The weight of the animal.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Method to get the speed of the animal.
     *
     * @return The speed of the animal.
     */
    protected double getSpeed() {
        return speed;
    }
    protected void addSpeed(double speed) {
        this.speed += speed;
    }

    /**
     * Method to get the array of medals won by the animal.
     *
     * @return The array of medals won by the animal.
     */
    public Medal[] getMedals() {
        return medals;
    }

    /**
     * Method to get the current position of the animal.
     *
     * @return The current position of the animal.
     */


    /**
     * Override of the equals method to compare if two Animal objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Animal)) return false;
        Animal other = (Animal) obj;
        return super.equals(obj) &&
                other.getName().equals(getName()) &&
                other.getGender().equals(getGender()) &&
                other.getWeight() == getWeight() &&
                other.getSpeed() == getSpeed() &&
                Arrays.equals(other.getMedals(), getMedals());

    }
}
