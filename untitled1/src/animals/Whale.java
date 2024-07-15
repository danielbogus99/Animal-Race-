package animals;

import Olympics.Medal;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;

/**
 * Represents a whale, a water-dwelling animal that extends the WaterAnimal class.
 */
public class Whale extends WaterAnimal {

    private String foodType;

    /**
     * Constructor to initialize a Whale object.
     *
     * @param totalDistance The total distance covered by the whale.
     * @param gender        The gender of the whale.
     * @param name          The name of the whale.
     * @param weight        The weight of the whale.
     * @param speed         The speed of the whale.
     * @param medals        The array of medals won by the whale.
     * @param diveDept      The dive depth of the whale.
     * @param foodType      The type of food the whale consumes.
     */
    public Whale(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, Location loc, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, double diveDept, String foodType) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter,loc,img1,img2,img3,img4, diveDept);
        this.foodType = foodType;
    }
    public Whale()
    {
        super();
        this.foodType = "";
    }

    /**
     * Method to make the whale produce its sound.
     */
    protected String getSound() {
       return  "Splash";
    }

    /**
     * Override of the toString method to provide a string representation of the Whale object.
     *
     * @return A string representation of the Whale object.
     */
    @Override
    public String toString() {
        return STR."Whale\{super.toString()}, foodType=\{foodType}}";
    }

    /**
     * Override of the equals method to compare if two Whale objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Whale)) return false;
        Whale other = (Whale) obj;
        return super.equals(obj) &&
                other.foodType.equals(foodType);
    }
}
