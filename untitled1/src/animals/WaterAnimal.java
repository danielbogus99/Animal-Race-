package animals;

import Olympics.Medal;
import mobility.Point;

public class WaterAnimal extends Animal {
    public static final int Max_DIVE = -800;
    public  WaterAnimal(double totalDistance, gender gender, String name, double weight, double speed, Medal[] medals, double diveDept) {
        super(new Point(50,0),totalDistance=0,gender,name,weight,speed,medals);
        this.diveDept = diveDept;
    }
    public boolean Dive(double distanceDive)
    {
        if (distanceDive < Max_DIVE) {
            return false;
        }
        diveDept = diveDept + distanceDive;
        return true;
    }


    public String toString() {
        return STR." {\{super.toString()} ,diveDept = \{diveDept}";
    }

    public void getSound() {}
    private double diveDept;
}
