package animals;

import Olympics.Medal;




public class Whale extends WaterAnimal {

    private String foodType;


    public Whale(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double diveDept, String foodType) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter, diveDept);
        this.foodType = foodType;
    }
    public Whale()
    {
        super();
        this.foodType = "";
    }

    /**
     * Method to make the whale produce its sound.
     */
    protected String getSound() {
       return  "Splash";
    }

    /**
     * Override of the toString method to provide a string representation of the Whale object.
     *
     * @return A string representation of the Whale object.
     */
    @Override
    public String toString() {
        return STR."Whale\{super.toString()}, foodType=\{foodType}}";
    }

    /**
     * Override of the equals method to compare if two Whale objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Whale)) return false;
        Whale other = (Whale) obj;
        return super.equals(obj) &&
                other.foodType.equals(foodType);
    }
    public String animalType()
    {
        return "Whale";
    }
}
