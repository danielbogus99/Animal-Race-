package animals;

import Olympics.Medal;
import Graphics.CompetitionPanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a whale, a water-dwelling animal that extends the WaterAnimal class.
 */
public class Whale extends WaterAnimal {

    private String foodType;
    private BufferedImage img1;

    /**
     * Constructor to initialize a Whale object.
     *
     * @param x              The x coordinate of the whale.
     * @param y              The y coordinate of the whale.
     * @param totalDistance  The total distance covered by the whale.
     * @param gender         The gender of the whale.
     * @param name           The name of the whale.
     * @param weight         The weight of the whale.
     * @param speed          The speed of the whale.
     * @param medals         The array of medals won by the whale.
     * @param orien          The orientation of the whale.
     * @param maxEnergy      The maximum energy of the whale.
     * @param energyPerMeter The energy consumed per meter by the whale.
     * @param diveDept       The dive depth of the whale.
     * @param foodType       The type of food the whale eats.
     * @param competitionPanel The competition panel for the whale.
     */
    public Whale(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double diveDept, String foodType, CompetitionPanel competitionPanel) {
        super(x, y, totalDistance, gender, name, weight, speed, medals, orien, maxEnergy, energyPerMeter, diveDept, competitionPanel);
        this.foodType = foodType;
        loadImages("animals/" + name + ".png");
    }

    /**
     * Default constructor to initialize a Whale object with default values.
     */
    public Whale() {
        super();
        this.foodType = "";
    }

    /**
     * Method to make the whale produce its sound.
     *
     * @return The sound of the whale.
     */
    protected String getSound() {
        return "Splash";
    }

    /**
     * Provides a string representation of the Whale object.
     *
     * @return A string representation of the Whale object.
     */
    @Override
    public String toString() {
        return String.format("Whale{%s, foodType=%s}", super.toString(), foodType);
    }

    /**
     * Compares if two Whale objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Whale)) return false;
        Whale other = (Whale) obj;
        return super.equals(obj) && foodType.equals(other.foodType);
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType() {
        return "Whale";
    }

    /**
     * Draws the whale object.
     *
     * @param g The graphics context.
     */
    public void drawObject(Graphics g) {
        switch (getOrientation()) {
            case EAST:
                g.drawImage(img1, location.getX(), location.getY() - size / 10, size, size, getPan());
                break;
            // Handle other cases for different orientations if needed
        }
    }

    /**
     * Loads images for the whale.
     *
     * @param nm The name of the image file.
     */
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/whale2E.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
