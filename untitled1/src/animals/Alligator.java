package animals;

import Olympics.Medal;

public class Alligator extends WaterAnimal{
    public Alligator(double totalDistance, Animal.gender gender, String name, double weight, double speed, Medal[] medals, double diveDept,String AreaOfLiving) {
        super(totalDistance, gender, name, weight, speed, medals, diveDept);
        this.AreaOfLiving = AreaOfLiving;
    }
    public void getSound()
    {
        System.out.println("Roar");
    }
    private String AreaOfLiving;
}
