package animals;

import Olympics.Medal;
import mobility.Point;
import java.awt.*;
import Graphics.CompetitionPanel;

/**
 * Represents a terrestrial animal that extends the Animal class.
 */
public class TerrestrialAnimals extends Animal implements ITerrestrailAnimal {

    private int noLegs;

    /**
     * Constructor to initialize a TerrestrialAnimals object.
     *
     * @param x              The x coordinate of the terrestrial animal.
     * @param y              The y coordinate of the terrestrial animal.
     * @param totalDistance  The total distance covered by the terrestrial animal.
     * @param gender         The gender of the terrestrial animal.
     * @param name           The name of the terrestrial animal.
     * @param weight         The weight of the terrestrial animal.
     * @param speed          The speed of the terrestrial animal.
     * @param medals         The array of medals won by the terrestrial animal.
     * @param orien          The orientation of the terrestrial animal.
     * @param maxEnergy      The maximum energy of the terrestrial animal.
     * @param energyPerMeter The energy consumed per meter by the terrestrial animal.
     * @param noLegs         The number of legs of the terrestrial animal.
     * @param pan            The competition panel for the terrestrial animal.
     */
    public TerrestrialAnimals(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, int noLegs, CompetitionPanel pan) {
        if (x < 0 || y < 0) {
            System.out.println("X AND Y are negative, default coordinates were set");
            x = 0;
            y = 20;
        }
        super(new Point(x, y), totalDistance, gender, name, weight, speed, medals, orien, maxEnergy, energyPerMeter, pan);
        this.noLegs = noLegs;
    }

    /**
     * Default constructor to initialize a TerrestrialAnimals object with default values.
     */
    public TerrestrialAnimals() {
        super();
        new Point(0, 20);
        noLegs = 0;
    }

    /**
     * Provides a string representation of the TerrestrialAnimals object.
     *
     * @return A string representation of the TerrestrialAnimals object.
     */
    @Override
    public String toString() {
        return String.format("TerrestrialAnimals{%s, noLegs=%d}", super.toString(), noLegs);
    }

    /**
     * Compares if two TerrestrialAnimals objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TerrestrialAnimals)) return false;
        TerrestrialAnimals other = (TerrestrialAnimals) obj;
        return super.equals(obj) && other.noLegs == noLegs;
    }

    /**
     * Method to get the sound of the terrestrial animal.
     *
     * @return The sound of the terrestrial animal.
     */
    protected String getSound() {
        return "";
    }

    /**
     * Gets the number of legs of the terrestrial animal.
     *
     * @return The number of legs of the terrestrial animal.
     */
    @Override
    public int getNumberOfLegs() {
        return noLegs;
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType() {
        return "TerrestrialAnimals";
    }

    /**
     * Gets the category of the animal.
     *
     * @return The category of the animal.
     */
    public String animalCategory() {
        return "Terrestrial";
    }

    /**
     * Draws the terrestrial animal object.
     *
     * @param g The graphics context.
     */
    public void drawObject(Graphics g) {
        super.drawObject(g);
    }
}
