package animals;

import Olympics.Medal;
import mobility.Point;
import java.awt.*;
import Graphics.CompetitionPanel;

/**
 * Represents an air-dwelling animal that extends the Animal class.
 */
public abstract class AirAnimal extends Animal {

    private double wingspan;

    /**
     * Constructor to initialize an AirAnimal object.
     *
     * @param x                The x coordinate of the animal.
     * @param y                The y coordinate of the animal.
     * @param totalDistance    The total distance covered by the animal.
     * @param gender           The gender of the animal.
     * @param name             The name of the animal.
     * @param weight           The weight of the animal.
     * @param speed            The speed of the animal.
     * @param medals           The array of medals won by the animal.
     * @param orien            The orientation of the animal.
     * @param maxEnergy        The maximum energy of the animal.
     * @param energyPerMeter   The energy consumed per meter by the animal.
     * @param wingspan         The wingspan of the air animal.
     * @param competitionPanel The competition panel for the animal.
     */
    public AirAnimal(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double wingspan, CompetitionPanel competitionPanel) {
        if (x < 0 || y < 0) {
            System.out.println("X and Y are negative, default coordinates were set");
            x = 0;
            y = 100;
        }
        super(new Point(x, y), totalDistance, gender, name, weight, speed, medals, orien, maxEnergy, energyPerMeter, competitionPanel);
        this.wingspan = wingspan;
    }

    /**
     * Default constructor to initialize an AirAnimal object with default values.
     */
    public AirAnimal() {
        super();
        this.wingspan = 0;
    }

    /**
     * Provides a string representation of the AirAnimal object.
     *
     * @return A string representation of the AirAnimal object.
     */
    @Override
    public String toString() {
        return String.format("%s, wingspan=%.2f", super.toString(), wingspan);
    }

    /**
     * Compares if two AirAnimal objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AirAnimal)) return false;
        AirAnimal other = (AirAnimal) obj;
        return super.equals(obj) &&
                Double.compare(other.wingspan, wingspan) == 0;
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType() {
        return "AirAnimals";
    }

    /**
     * Gets the category of the animal.
     *
     * @return The category of the animal.
     */
    public String animalCategory() {
        return "Air";
    }

    /**
     * Draws the animal object.
     *
     * @param g The graphics context.
     */
    public void drawObject(Graphics g) {
        super.drawObject(g);
    }

    /**
     * Gets the sound of the air animal.
     *
     * @return The sound of the air animal.
     */
    abstract protected String getSound();

}
