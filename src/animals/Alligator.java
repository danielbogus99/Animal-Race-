package animals;

import Olympics.Medal;
import mobility.Point;

import javax.imageio.ImageIO;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Graphics.CompetitionPanel;

/**
 * Represents an alligator, a water-dwelling animal that extends the WaterAnimal class.
 */
public class Alligator extends WaterAnimal implements IReptile,ITerrestrailAnimal{

    private String AreaOfLiving;
    private TerrestrialAnimals terrestrialAnimals;
    private WaterAnimal waterAnimal;
    private BufferedImage img1;
    /**
     * Constructor to initialize an Alligator object.
     *
     * @param totalDistance The total distance covered by the alligator.
     * @param gender        The gender of the alligator.
     * @param name          The name of the alligator.
     * @param weight        The weight of the alligator.
     * @param speed         The speed of the alligator.
     * @param medals        The array of medals won by the alligator.
     * @param diveDept      The dive depth of the alligator.
     * @param AreaOfLiving  The area of living of the alligator.
     */
    public Alligator(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double diveDept, int noLegs, String AreaOfLiving,CompetitionPanel competitionPanel) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter,diveDept,competitionPanel);
        terrestrialAnimals = new TerrestrialAnimals(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter,noLegs,competitionPanel);
        this.AreaOfLiving = AreaOfLiving;
        loadImages("animals/" + name + ".png");
    }
    public Alligator() {
        super();
        terrestrialAnimals = new TerrestrialAnimals();
        AreaOfLiving = "";
    }

    /**
     * Method to make the alligator produce its sound.
     */
    protected String getSound() {
        return "Roar";
    }


    @Override
    public String toString() {
        return STR."Alligator\{super.toString()}noLegs=\{getNumberOfLegs()}, areaOfLiving=\{AreaOfLiving}}";
    }

    /**
     * Override of the equals method to compare if two Alligator objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Alligator)) return false;
        Alligator other = (Alligator) obj;
        return super.equals(obj) &&
                other.AreaOfLiving.equals(AreaOfLiving);
    }

    public boolean speedUp(int speeds) {
        if(getSpeed() > Max_SPEED){
            System.out.println("The speed is over the max speed");
            return false;
        }
        addSpeed(speeds);
        return true;
    }

    @Override
    public int getNumberOfLegs()
    {
        return terrestrialAnimals.getNumberOfLegs();
    }
    public String animalType()
    {
        return "Alligator";
    }

    public void drawObject(Graphics g)
    {
        switch (getOrientation()) {
            case EAST:
                g.drawImage(img1, location.getX(), location.getY() - size / 10, size , size,getPan());
                break;
        }
    }
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/alligator2E.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
