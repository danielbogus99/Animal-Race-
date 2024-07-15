package animals;

import Olympics.Medal;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;

/**
 * Represents a dog, a terrestrial animal that extends the TerrestrialAnimals class.
 */
public class Dog extends TerrestrialAnimals {

    private String breed;

    /**
     * Constructor to initialize a Dog object.
     *
     * @param totalDistance The total distance covered by the dog.
     * @param gender        The gender of the dog.
     * @param name          The name of the dog.
     * @param weight        The weight of the dog.
     * @param speed         The speed of the dog.
     * @param medals        The array of medals won by the dog.
     * @param noLegs        The number of legs of the dog.
     * @param breed         The breed of the dog.
     */
    public Dog(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, Location loc, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, int noLegs, String breed) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter,loc,img1,img2,img3,img4, noLegs);
        this.breed = breed;
    }
    public Dog()
    {
        super();
        this.breed = "";
    }

    /**
     * Returns the breed of the dog.
     *
     * @return The breed of the dog.
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Method to make the dog produce its sound.
     */
    protected String getSound() {
        return "Woof woof";
    }

    /**
     * Override of the toString method to provide a string representation of the Dog object.
     *
     * @return A string representation of the Dog object.
     */
    @Override
    public String toString() {
        return STR."Dog\{super.toString()}, breed=\{breed}}";
    }

    /**
     * Override of the equals method to compare if two Dog objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dog)) return false;
        Dog other = (Dog) obj;
        return super.equals(obj) &&
                breed.equals(other.breed);
    }
}
