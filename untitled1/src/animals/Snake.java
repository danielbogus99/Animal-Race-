package animals;

import Olympics.Medal;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;

/**
 * Represents a snake, a terrestrial animal that extends the TerrestrialAnimals class.
 */
public class Snake extends TerrestrialAnimals implements IReptile{

    private double length;
    private Poisonous poisonous;

    @Override
    public boolean speedUp(int speeds) {
        if(getSpeed() > Max_SPEED){
            System.out.println("The speed is over the max speed");
            return false;
        }
        addSpeed(speeds);
        return true;
    }

    /**
     * Enum defining the poisonous status of the snake.
     */
    public enum Poisonous {
        LOW, MEDIUM, HIGH,

    }

    /**
     * Constructor to initialize a Snake object.
     *
     * @param totalDistance The total distance covered by the snake.
     * @param gender        The gender of the snake.
     * @param name          The name of the snake.
     * @param weight        The weight of the snake.
     * @param speed         The speed of the snake.
     * @param medals        The array of medals won by the snake.
     * @param noLegs        The number of legs of the snake.
     * @param poison        The poisonous status of the snake.
     * @param length        The length of the snake.
     */
    public Snake(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, Location loc, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, int noLegs, Poisonous poison, double length) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter,loc,img1,img2,img3,img4, noLegs);
        this.length = length;
        this.poisonous = poison;
    }
    public Snake()
    {
        super();
        this.length = 0;
        this.poisonous = Poisonous.LOW;
    }

    /**
     * Method to make the snake produce its sound.
     */
    protected String getSound() {
       return  "ssssssss";
    }

    /**
     * Override of the toString method to provide a string representation of the Snake object.
     *
     * @return A string representation of the Snake object.
     */
    @Override
    public String toString() {
        return STR."Snake\{super.toString()}, poisonous=\{poisonous}, length=\{length}}";
    }

    /**
     * Override of the equals method to compare if two Snake objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Snake)) return false;
        Snake other = (Snake) obj;
        return super.equals(obj) &&
                other.length == length &&
                other.poisonous == poisonous;
    }
}
