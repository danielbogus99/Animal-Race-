package animals;

import Olympics.Medal;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Graphics.CompetitionPanel;

/**
 * Represents an alligator, a water-dwelling animal that extends the WaterAnimal class.
 */
public class Alligator extends WaterAnimal implements IReptile, ITerrestrailAnimal {

    private String AreaOfLiving;
    private TerrestrialAnimals terrestrialAnimals;
    private WaterAnimal waterAnimal;
    private BufferedImage img1;

    /**
     * Constructor to initialize an Alligator object.
     *
     * @param x              The x coordinate of the alligator.
     * @param y              The y coordinate of the alligator.
     * @param totalDistance  The total distance covered by the alligator.
     * @param gender         The gender of the alligator.
     * @param name           The name of the alligator.
     * @param weight         The weight of the alligator.
     * @param speed          The speed of the alligator.
     * @param medals         The array of medals won by the alligator.
     * @param orien          The orientation of the alligator.
     * @param maxEnergy      The maximum energy of the alligator.
     * @param energyPerMeter The energy consumed per meter by the alligator.
     * @param diveDept       The dive depth of the alligator.
     * @param noLegs         The number of legs of the alligator.
     * @param AreaOfLiving   The area of living of the alligator.
     * @param competitionPanel The competition panel for the alligator.
     */
    public Alligator(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double diveDept, int noLegs, String AreaOfLiving, CompetitionPanel competitionPanel) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter,diveDept,competitionPanel);
        terrestrialAnimals = new TerrestrialAnimals(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter,noLegs,competitionPanel);
        this.AreaOfLiving = AreaOfLiving;
        loadImages("animals/" + name + ".png");
    }

    /**
     * Default constructor to initialize an Alligator object with default values.
     */
    public Alligator() {
        super();
        terrestrialAnimals = new TerrestrialAnimals();
        AreaOfLiving = "";
    }

    /**
     * Method to make the alligator produce its sound.
     *
     * @return The sound of the alligator.
     */
    protected String getSound() {
        return "Roar";
    }

    /**
     * Provides a string representation of the Alligator object.
     *
     * @return A string representation of the Alligator object.
     */
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

    /**
     * Increases the speed of the alligator.
     *
     * @param speeds The amount of speed to increase.
     * @return True if the speed was successfully increased, false otherwise.
     */
    public boolean speedUp(int speeds) {
        if(getSpeed() > Max_SPEED){
            System.out.println("The speed is over the max speed");
            return false;
        }
        addSpeed(speeds);
        return true;
    }

    /**
     * Gets the number of legs of the alligator.
     *
     * @return The number of legs of the alligator.
     */
    @Override
    public int getNumberOfLegs()
    {
        return terrestrialAnimals.getNumberOfLegs();
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType()
    {
        return "Alligator";
    }

    /**
     * Draws the alligator object.
     *
     * @param g The graphics context.
     */
    public void drawObject(Graphics g)
    {
        switch (getOrientation()) {
            case EAST:
                g.drawImage(img1, location.getX(), location.getY() - size / 10, size , size,getPan());
                break;
        }
    }

    /**
     * Loads images for the alligator.
     *
     * @param nm The name of the image file.
     */
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/alligator2E.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
