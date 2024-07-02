package animals;

import Olympics.Medal;

public class Cat extends TerrestrialAnimals{
    public Cat(double totalDistance, gender gender, String name, double weight, double speed, Medal[] medals, int noLegs,boolean Castrated) {
        super(totalDistance, gender, name, weight, speed, medals, noLegs);
        this.Castrated=Castrated;
    }
    public void getSound()
    {
        System.out.println("Meow");
    }
    public String toString()
    {
        return STR."Cat{\{super.toString()} ,Castrated=\{Castrated}}";
    }
    private boolean Castrated;
}
