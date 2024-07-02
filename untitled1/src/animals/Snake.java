package animals;

import Olympics.Medal;

public class Snake extends TerrestrialAnimals {
    public Snake(double totalDistance, gender gender, String name, double weight, double speed, Medal[] medals, int noLegs,Poisonous poison,double lenght) {
        super(totalDistance, gender, name, weight, speed, medals, noLegs);
        this.length = lenght;
        this.poisonous = poison;
    }
    public void getSound()
    {
        System.out.println("ssssssss");
    }
    public enum Poisonous{NON_POISONOUS,POISONOUS}
    private double length;
    private Poisonous poisonous;
}
