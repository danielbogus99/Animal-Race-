package animals;

import Olympics.Medal;

public class Dog extends TerrestrialAnimals {
    public Dog(double totalDistance, gender gender, String name, double weight,
               double speed, Medal[] medals, int noLegs,String breed) {
        super(totalDistance, gender, name, weight, speed, medals, noLegs);
        this.breed = breed;
    }
    private String breed;


    public String toString() {
        return STR."Dog{\{super.toString()} ,breed=\{breed}}";
    }

    public void getSound()
    {
        System.out.println("Woof Woof");
    }
}
