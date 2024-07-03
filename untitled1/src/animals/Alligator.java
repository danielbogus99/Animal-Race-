package animals;

import Olympics.Medal;

/**
 * Represents an alligator, a water-dwelling animal that extends the WaterAnimal class.
 */
public class Alligator extends WaterAnimal {

    private String AreaOfLiving;

    /**
     * Constructor to initialize an Alligator object.
     *
     * @param totalDistance The total distance covered by the alligator.
     * @param gender        The gender of the alligator.
     * @param name          The name of the alligator.
     * @param weight        The weight of the alligator.
     * @param speed         The speed of the alligator.
     * @param medals        The array of medals won by the alligator.
     * @param diveDept      The dive depth of the alligator.
     * @param AreaOfLiving  The area of living of the alligator.
     */
    public Alligator(double totalDistance, Animal.gender gender, String name, double weight, double speed, Medal[] medals, double diveDept, String AreaOfLiving) {
        super(totalDistance, gender, name, weight, speed, medals, diveDept);
        this.AreaOfLiving = AreaOfLiving;
    }

    /**
     * Method to make the alligator produce its sound.
     */
    public void getSound() {
        System.out.println("Roar");
    }

    /**
     * Override of the toString method to provide a string representation of the Alligator object.
     *
     * @return A string representation of the Alligator object.
     */
    @Override
    public String toString() {
        return STR."Alligator\{super.toString()}, areaOfLiving=\{AreaOfLiving}}";
    }

    /**
     * Override of the equals method to compare if two Alligator objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Alligator)) return false;
        Alligator other = (Alligator) obj;
        return super.equals(obj) &&
                other.AreaOfLiving.equals(AreaOfLiving);
    }
}
