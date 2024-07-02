package animals;

import Olympics.Medal;

public class Snake extends TerrestrialAnimals {
    public Snake(double totalDistance, gender gender, String name, double weight,
                 double speed, Medal[] medals, int noLegs,Poisonous poison,double length) {
        super(totalDistance, gender, name, weight, speed, medals, noLegs);
        this.length = length;
        this.poisonous = poison;
    }
    public void getSound()
    {
        System.out.println("ssssssss");
    }

    @Override
    public String toString() {
        return STR."Snake{\{super.toString()} ,poisonous= \{poisonous} ,length= \{length}";
    }

    public enum Poisonous{NON_POISONOUS,POISONOUS}
    private double length;
    private Poisonous poisonous;
}
