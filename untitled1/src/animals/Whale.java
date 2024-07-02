package animals;

import Olympics.Medal;

public class Whale extends WaterAnimal{
    public Whale(double totalDistance, Animal.gender gender, String name, double weight, double speed, Medal[] medals, double diveDept,String foodType) {
        super(totalDistance, gender, name, weight, speed, medals, diveDept);
        this.foodType = foodType;
    }
    public void getSound()
    {
        System.out.println("Splash");
    }

    public String toString() {
        return STR."Whale \{super.toString()} ,foodType= \{foodType}}";
    }

    private String foodType;
}
