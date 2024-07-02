package animals;

import Olympics.Medal;

public class Dolphin extends WaterAnimal{
    public Dolphin(double totalDistance, Animal.gender gender, String name, double weight, double speed, Medal[] medals, double diveDept,WaterType waterType) {
        super(totalDistance, gender, name, weight, speed, medals, diveDept);
        this.waterType = waterType;
    }
    public void getSound()
    {
        System.out.println("Click-click");
    }
    public enum WaterType{Sea,Sweet}


    public String toString() {
        return STR."Dolphin \{super.toString()} ,Water Type= \{waterType}}";
    }

    private WaterType waterType;
}
