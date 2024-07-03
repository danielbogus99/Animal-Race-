package animals;

import Olympics.Medal;

/**
 * Represents a snake, a terrestrial animal that extends the TerrestrialAnimals class.
 */
public class Snake extends TerrestrialAnimals {

    private double length;
    private Poisonous poisonous;

    /**
     * Enum defining the poisonous status of the snake.
     */
    public enum Poisonous {
        NON_POISONOUS,
        POISONOUS
    }

    /**
     * Constructor to initialize a Snake object.
     *
     * @param totalDistance The total distance covered by the snake.
     * @param gender        The gender of the snake.
     * @param name          The name of the snake.
     * @param weight        The weight of the snake.
     * @param speed         The speed of the snake.
     * @param medals        The array of medals won by the snake.
     * @param noLegs        The number of legs of the snake.
     * @param poison        The poisonous status of the snake.
     * @param length        The length of the snake.
     */
    public Snake(double totalDistance, gender gender, String name, double weight, double speed, Medal[] medals, int noLegs, Poisonous poison, double length) {
        super(totalDistance, gender, name, weight, speed, medals, noLegs);
        this.length = length;
        this.poisonous = poison;
    }

    /**
     * Method to make the snake produce its sound.
     */
    public void getSound() {
        System.out.println("ssssssss");
    }

    /**
     * Override of the toString method to provide a string representation of the Snake object.
     *
     * @return A string representation of the Snake object.
     */
    @Override
    public String toString() {
        return STR."Snake\{super.toString()}, poisonous=\{poisonous}, length=\{length}}";
    }

    /**
     * Override of the equals method to compare if two Snake objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Snake)) return false;
        Snake other = (Snake) obj;
        return super.equals(obj) &&
                other.length == length &&
                other.poisonous == poisonous;
    }
}
