package animals;

import Olympics.Medal;
import mobility.Point;

import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents a terrestrial animal that extends the Animal class.
 */
public  class TerrestrialAnimals extends Animal implements ITerrestrailAnimal {

    private int noLegs;


    public TerrestrialAnimals(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, int noLegs) {
        if (x < 0 || y < 0)
        {
            System.out.println("X AND Y are negative,default coordinates were set");
            x = 0;
            y = 20;
        }
        super(new Point(x, y), totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter);
        this.noLegs = noLegs;
    }
    public TerrestrialAnimals()
    {
        super();
        new Point(0, 20);
        noLegs = 0;
    }

    /**
     * Override of the toString method to provide a string representation of the TerrestrialAnimals object.
     *
     * @return A string representation of the TerrestrialAnimals object.
     */
    @Override
    public String toString() {
        return STR." \{super.toString()} ,noLegs= \{noLegs}";
    }

    /**
     * Override of the equals method to compare if two TerrestrialAnimals objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TerrestrialAnimals)) return false;
        TerrestrialAnimals other = (TerrestrialAnimals) obj;
        return super.equals(obj) &&
                other.noLegs == noLegs;
    }


    /**
     * Method to get the sound of the terrestrial animal.
     */
    protected String getSound(){
        return "";
    };

    @Override
    public int getNumberOfLegs() {
        return noLegs;
    }

    public String animalType()
    {
        return "TerrestrialAnimals";
    }
    public String animalCategory()
    {
        return "Terrestrial";
    }
    public void drawObject(Graphics g) {
        super.drawObject(g);
    }

    @Override
    public String getAnimaleName() {
        return "";
    }
}
