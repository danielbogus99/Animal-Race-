package animals;

import Olympics.Medal;
import mobility.Point;

/**
 * Represents a terrestrial animal that extends the Animal class.
 */
public abstract class TerrestrialAnimals extends Animal {

    private int noLegs;

    /**
     * Constructor to initialize a TerrestrialAnimals object.
     *
     * @param totalDistance The total distance covered by the animal.
     * @param gender        The gender of the animal.
     * @param name          The name of the animal.
     * @param weight        The weight of the animal.
     * @param speed         The speed of the animal.
     * @param medals        The array of medals won by the animal.
     * @param noLegs        The number of legs of the terrestrial animal.
     */
    public TerrestrialAnimals(double totalDistance, Animal.gender gender, String name, double weight, double speed, Medal[] medals, int noLegs) {
        super(new Point(0, 20), totalDistance, gender, name, weight, speed, medals);
        this.noLegs = noLegs;
    }
    public TerrestrialAnimals()
    {
        super();
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
    abstract protected String getSound();
}
