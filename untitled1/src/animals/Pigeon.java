package animals;

import Olympics.Medal;

public class Pigeon extends AirAnimal {
    public Pigeon(double totalDistance, Animal.gender gender, String name, double weight, double speed, Medal[] medals, double wingspan,String family) {
        super(totalDistance, gender, name, weight, speed, medals, wingspan);
        this.family = family;
    }
    public void getSound()
    {
        System.out.println("Arr-rar-rar-rar-raah");
    }
    private String family;
}
