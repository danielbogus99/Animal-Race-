package animals;

import Olympics.Medal;

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
    public Dolphin(double totalDistance, Animal.gender gender, String name, double weight, double speed, Medal[] medals, double diveDept, WaterType waterType) {
        super(totalDistance, gender, name, weight, speed, medals, diveDept);
        this.waterType = waterType;
    }

    /**
     * Method to make the dolphin produce its sound.
     */
    public void getSound() {
        System.out.println("Click-click");
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
