package Graphics;

/**
 * Interface representing an animal with the ability to move and eat.
 */
public interface IAnimal extends IMoveable {

    /**
     * Feeds the animal with the specified amount of energy.
     *
     * @param energy The amount of energy to be given to the animal.
     * @return True if the energy was successfully consumed, false otherwise.
     */
    public boolean eat(int energy);
}
