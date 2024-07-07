package animals;

import Olympics.Medal;
import mobility.Point;

/**
 * Represents an air-dwelling animal that extends the Animal class.
 */
public abstract class AirAnimal extends Animal {

    private double wingspan;

    /**
     * Constructor to initialize an AirAnimal object.
     *
     * @param totalDistance The total distance covered by the animal.
     * @param gender        The gender of the animal.
     * @param name          The name of the animal.
     * @param weight        The weight of the animal.
     * @param speed         The speed of the animal.
     * @param medals        The array of medals won by the animal.
     * @param wingspan      The wingspan of the air animal.
     */
    public AirAnimal(double totalDistance, gender gender, String name, double weight, double speed, Medal[] medals, double wingspan) {
        super(new Point(0, 100), totalDistance, gender, name, weight, speed, medals);
        this.wingspan = wingspan;
    }
    public AirAnimal()
    {
        super();
        this.wingspan = 0;
    }

    /**
     * Override of the toString method to provide a string representation of the AirAnimal object.
     *
     * @return A string representation of the AirAnimal object.
     */
    @Override
    public String toString() {
        return STR." {\{super.toString()} ,wingspan=\{wingspan}";
    }

    /**
     * Override of the equals method to compare if two AirAnimal objects are equal.
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
                other.wingspan == wingspan;
    }

    /**
     * Method to get the sound of the air animal.
     */
    abstract protected String getSound();
}