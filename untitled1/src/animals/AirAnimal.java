package animals;

import Olympics.Medal;
import mobility.Point;

public class AirAnimal extends Animal {
    public AirAnimal(double totalDistance, gender gender, String name, double weight, double speed, Medal[] medals, double wingspan) {
        super(new Point(0,100), totalDistance = 0, gender, name, weight, speed,medals);
        this.wingspan = wingspan;
    }
    public void getSound() {}

    @Override
    public String toString() {
        return STR." {\{super.toString()} ,wingspan=\{wingspan}";
    }

    private double wingspan;

}
