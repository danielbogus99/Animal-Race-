package animals;

import Olympics.Medal;
import mobility.Point;

public class TerrestrialAnimals extends Animal
{
    public  TerrestrialAnimals(double totalDistance, Animal.gender gender, String name, double weight, double speed, Medal[] medals, int noLegs) {
        super(new Point(0, 20), totalDistance = 0, gender, name, weight, speed, medals);
        this.noLegs = noLegs;
    }
    public void getSound() {}
    private int noLegs;
}

