package animals;

import Olympics.Medal;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Graphics.CompetitionPanel;

/**
 * Represents a dolphin, a water-dwelling animal that extends the WaterAnimal class.
 */
public class Dolphin extends WaterAnimal {

    private WaterType waterType;
    private BufferedImage img1;

    /**
     * Enum defining the type of water the dolphin inhabits.
     */
    public enum WaterType {
        Sea,
        Sweet
    }

    /**
     * Constructor to initialize a Dolphin object.
     *
     * @param x              The x coordinate of the dolphin.
     * @param y              The y coordinate of the dolphin.
     * @param totalDistance  The total distance covered by the dolphin.
     * @param gender         The gender of the dolphin.
     * @param name           The name of the dolphin.
     * @param weight         The weight of the dolphin.
     * @param speed          The speed of the dolphin.
     * @param medals         The array of medals won by the dolphin.
     * @param orien          The orientation of the dolphin.
     * @param maxEnergy      The maximum energy of the dolphin.
     * @param energyPerMeter The energy consumed per meter by the dolphin.
     * @param diveDept       The dive depth of the dolphin.
     * @param waterType      The type of water the dolphin inhabits.
     * @param competitionPanel The competition panel for the dolphin.
     */
    public Dolphin(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double diveDept, WaterType waterType, CompetitionPanel competitionPanel) {
        super(x, y, totalDistance, gender, name, weight, speed, medals, orien, maxEnergy, energyPerMeter, diveDept, competitionPanel);
        this.waterType = waterType;
        loadImages("animals/" + name + ".png");
    }

    /**
     * Default constructor to initialize a Dolphin object with default values.
     */
    public Dolphin() {
        super();
        this.waterType = WaterType.Sea;
    }

    /**
     * Method to make the dolphin produce its sound.
     *
     * @return The sound of the dolphin.
     */
    protected String getSound() {
        return "Click-click";
    }

    /**
     * Provides a string representation of the Dolphin object.
     *
     * @return A string representation of the Dolphin object.
     */
    @Override
    public String toString() {
        return String.format("Dolphin{%s, Water Type=%s}", super.toString(), waterType);
    }

    /**
     * Compares if two Dolphin objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dolphin)) return false;
        Dolphin other = (Dolphin) obj;
        return super.equals(obj) && other.waterType == waterType;
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType() {
        return "Dolphin";
    }

    /**
     * Draws the dolphin object.
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
     * Loads images for the dolphin.
     *
     * @param nm The name of the image file.
     */
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/dolphin3E.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
