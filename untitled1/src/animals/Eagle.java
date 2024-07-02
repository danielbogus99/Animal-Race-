package animals;

import Olympics.Medal;

public class Eagle extends AirAnimal{
    public static final int Max_ALTITUDE = 1000;
    public Eagle(double totalDistance, Animal.gender gender, String name, double weight, double speed,
                 Medal[] medals, double wingspan,double altitudeOfFlight) {
        super(totalDistance, gender, name, weight, speed, medals, wingspan);
        this.altitudeOfFlight = altitudeOfFlight;
    }
    public void getSound()
    {
        System.out.println("Clack-wack-chack");
    }


    public String toString() {
        return STR."Eagle \{super.toString()} ,altitudeOfFlight= \{altitudeOfFlight}}";
    }

    private double altitudeOfFlight;

}
