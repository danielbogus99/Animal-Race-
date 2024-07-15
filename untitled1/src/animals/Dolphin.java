package animals;

import Olympics.Medal;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;

/**
 * Represents a dolphin, a water-dwelling animal that extends the WaterAnimal class.
 */
public class Dolphin extends WaterAnimal {

    private WaterType waterType;

    /**
     * Enum defining the type of water the dolphin inhabits.
     */
    public enum WaterType {
        Sea,
        Sweet
    }

    /**
     * Constructor to initialize a Dolphin object.
     *
     * @param totalDistance The total distance covered by the dolphin.
     * @param gender        The gender of the dolphin.
     * @param name          The name of the dolphin.
     * @param weight        The weight of the dolphin.
     * @param speed         The speed of the dolphin.
     * @param medals        The array of medals won by the dolphin.
     * @param diveDept      The dive depth of the dolphin.
     * @param waterType     The type of water the dolphin inhabits.
     */
    public Dolphin(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, Location loc, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, double diveDept, WaterType waterType) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter,loc,img1,img2,img3,img4, diveDept);
        this.waterType = waterType;
    }
    public Dolphin()
    {
        super();
        this.waterType = WaterType.Sea;
    }

    /**
     * Method to make the dolphin produce its sound.
     */
    protected String getSound() {
        return  "Click-click";
    }

    /**
     * Override of the toString method to provide a string representation of the Dolphin object.
     *
     * @return A string representation of the Dolphin object.
     */
    @Override
    public String toString() {
        return STR."Dolphin\{super.toString()}, Water Type=\{waterType}}";
    }

    /**
     * Override of the equals method to compare if two Dolphin objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dolphin)) return false;
        Dolphin other = (Dolphin) obj;
        return super.equals(obj) &&
                other.waterType == waterType;
    }
}
