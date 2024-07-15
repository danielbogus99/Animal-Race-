package animals;

import Graphics.IClonable;
import Graphics.IDrawable;
import Graphics.IMoveable;
import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;
import Olympics.Medal;


import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Represents an animal with various attributes.
 */
public abstract class Animal extends Mobile implements ILocatable, IMoveable, IDrawable, IClonable {

    private String name;
    private double weight;
    private int speed;
    private Medal medals[];
    private gender gender;
    private IMoveable Imoveable;
    private IDrawable Idrawable;
    private IClonable Clonable;
    private int size;
    private int id;
    private Location loc;
    private Orientation orien ;
    private int maxEnergy;
    private int energyPerMeter;
    //private CompetitionPanel pan;
    private BufferedImage img1, img2, img3, img4;

    /**
     * Constructor to initialize an Animal object.
     *
     * @param position      The initial position of the animal.
     * @param totalDistance The total distance covered by the animal.
     * @param gender        The gender of the animal.
     * @param name          The name of the animal.
     * @param weight        The weight of the animal.
     * @param speed         The speed of the animal.
     * @param medals        The array of medals won by the animal.
     * @param orien         The orientation of the animal.
     * @param maxEnergy     The maximum energy of the animal.
     * @param energyPerMeter The energy per meter for the animal.
     * @param //pan           The competition panel associated with the animal.
     * @param loc           The location of the animal.
     * @param img1          The first image of the animal.
     * @param img2          The second image of the animal.
     * @param img3          The third image of the animal.
     * @param img4          The fourth image of the animal.
     */
    public Animal(Point position, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, Location loc, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4) {
        super(position, totalDistance);
        this.weight = weight;
        this.speed = speed;
        this.medals = medals;
        this.gender = gender;
        this.name = name;
        this.orien = orien;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        //this.pan = pan;
        this.loc = loc;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
    }

    public Animal() {
        super();
        this.weight = 0;
        this.speed = 0;
        this.medals = null;
        this.gender = gender.Male;
        this.name = "";
        this.orien = Orientation.NORTH; // Default value
        this.maxEnergy = 0;
        this.energyPerMeter = 0;
        //this.pan = null;
        this.loc = null;
        this.img1 = null;
        this.img2 = null;
        this.img3 = null;
        this.img4 = null;
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


    public String getAnimaleName() {
        return name;
    }

    /**
     * Method to get the speed of the animal.
     *
     * @return The speed of the animal.
     */
    public int getSpeed() {
        return speed;
    }
    protected void addSpeed(int speed) {
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

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Animal)) return false;
        Animal other = (Animal) obj;
        return super.equals(obj) &&
                other.getName().equals(getName()) &&
                other.getGender().equals(getGender()) &&
                other.getWeight() == getWeight() &&
                other.getSpeed() == getSpeed() &&
                Arrays.equals(other.getMedals(), getMedals()) &&
                other.orien == this.orien &&
                other.maxEnergy == this.maxEnergy &&
                other.energyPerMeter == this.energyPerMeter &&
                (other.loc == this.loc || (other.loc != null && other.loc.equals(this.loc))) &&
                (other.img1 == this.img1 || (other.img1 != null && other.img1.equals(this.img1))) &&
                (other.img2 == this.img2 || (other.img2 != null && other.img2.equals(this.img2))) &&
                (other.img3 == this.img3 || (other.img3 != null && other.img3.equals(this.img3))) &&
                (other.img4 == this.img4 || (other.img4 != null && other.img4.equals(this.img4)));
    }


    public enum Orientation {
        NORTH, EAST, SOUTH, WEST
    }
    /**
     * Enumeration representing gender of the animal.
     */
    public enum gender {Male, Female, Hermaphrodite}
    public void loadImages(String nm) {

    }
    public void drawObject(Graphics g) {

    }
}
