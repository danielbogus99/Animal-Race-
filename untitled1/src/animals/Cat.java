package animals;

import Olympics.Medal;

/**
 * Represents a cat, a terrestrial animal that extends the TerrestrialAnimals class.
 */
public class Cat extends TerrestrialAnimals {

    private boolean Castrated;

    /**
     * Constructor to initialize a Cat object.
     *
     * @param totalDistance The total distance covered by the cat.
     * @param gender        The gender of the cat.
     * @param name          The name of the cat.
     * @param weight        The weight of the cat.
     * @param speed         The speed of the cat.
     * @param medals        The array of medals won by the cat.
     * @param noLegs        The number of legs of the cat.
     * @param Castrated     Whether the cat is castrated or not.
     */
    public Cat(double totalDistance, gender gender, String name, double weight, double speed, Medal[] medals, int noLegs, boolean Castrated) {
        super(totalDistance, gender, name, weight, speed, medals, noLegs);
        this.Castrated = Castrated;
    }

    /**
     * Method to make the cat produce its sound.
     */
    public void getSound() {
        System.out.println("Meow");
    }

    /**
     * Override of the equals method to compare if two Cat objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cat)) return false;
        Cat other = (Cat) obj;
        return super.equals(obj) &&
                other.Castrated == Castrated;
    }

    /**
     * Returns a string representation of the Cat object.
     *
     * @return A string representation including the superclass representation and castration status.
     */
    @Override
    public String toString() {
        return STR."Cat\{super.toString()}, Castrated=\{Castrated}}";
    }
}
