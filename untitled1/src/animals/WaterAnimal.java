package animals;

import Olympics.Medal;
import mobility.Point;

import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.CompetitionPanel;

/**
 * Represents a water-dwelling animal that extends the Animal class.
 */
public abstract class WaterAnimal extends Animal {

    /**
     * Constant defining the maximum depth for diving.
     */
    public static final int Max_DIVE = -800;

    private double diveDept;

    /**
     * Constructor to initialize a WaterAnimal object.
     *
     * @param totalDistance The total distance covered by the animal.
     * @param gender        The gender of the animal.
     * @param name          The name of the animal.
     * @param weight        The weight of the animal.
     * @param speed         The speed of the animal.
     * @param medals        The array of medals won by the animal.
     * @param diveDept      The initial depth of diving for the water animal.
     */
    public WaterAnimal(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double diveDept,CompetitionPanel competitionPanel)
    {
        if (x < 0 || y < 0)
        {
            System.out.println("X AND Y are negative,default coordinates were set");
            x = 50;
            y = 0;
        }
        super(new Point(x, y), totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter,competitionPanel);
        this.diveDept = diveDept;
    }
    public WaterAnimal()
    {
        super();
        this.diveDept = 0;
    }

    /**
     * Method to simulate diving by the water animal.
     *
     * @param distanceDive The distance to dive.
     * @return True if the dive is successful (within maximum depth), false otherwise.
     */
    public boolean Dive(double distanceDive) {
        if (distanceDive < Max_DIVE) {
            return false;
        }
        diveDept = diveDept + distanceDive;
        return true;
    }

    /**
     * Override of the toString method to provide a string representation of the WaterAnimal object.
     *
     * @return A string representation of the WaterAnimal object.
     */
    @Override
    public String toString() {
        return STR." {\{super.toString()} ,diveDept = \{diveDept}";
    }

    /**
     * Override of the equals method to compare if two WaterAnimal objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof WaterAnimal)) return false;
        WaterAnimal other = (WaterAnimal) obj;
        return super.equals(obj) &&
                other.diveDept == diveDept;
    }

    /**
     * Method to get the sound of the water animal.
     */
    abstract protected String getSound();
    public String animalType()
    {
        return "WaterAnimal";
    }
    public String animalCategory()
    {
        return "Water";
    }
    public void drawObject(Graphics g) {
        super.drawObject(g);
    }

}
