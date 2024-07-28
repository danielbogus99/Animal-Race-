package animals;

/**
 * Represents the interface for reptile behaviors.
 */
public interface IReptile {

   /**
    * The maximum speed for a reptile.
    */
   int Max_SPEED = 5;

   /**
    * Increases the speed of the reptile.
    *
    * @param speed The amount of speed to increase.
    * @return True if the speed was successfully increased, false otherwise.
    */
   boolean speedUp(int speed);
}
