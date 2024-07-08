package animals;

import Olympics.Medal;

/**
 * Represents an alligator, a water-dwelling animal that extends the WaterAnimal class.
 */
public class Alligator extends WaterAnimal implements IReptile{

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
    public Alligator(int x,int y,double totalDistance, Animal.gender gender, String name, double weight, double speed, Medal[] medals, double diveDept, String AreaOfLiving) {
        super(x,y,totalDistance, gender, name, weight, speed, medals, diveDept);
        this.AreaOfLiving = AreaOfLiving;
    }
    public Alligator()
    {
        super();
        AreaOfLiving = "";
    }

    /**
     * Method to make the alligator produce its sound.
     */
    protected String getSound() {
        return "Roar";
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

    public boolean speedUp(int speeds) {
        if(getSpeed() > Max_SPEED){
            System.out.println("The speed is over the max speed");
            return false;
        }
        addSpeed(speeds);
        return true;
    }
}
