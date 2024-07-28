package animals;

import Olympics.Medal;
import Graphics.CompetitionPanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents an eagle, an air-dwelling animal that extends the AirAnimal class.
 */
public class Eagle extends AirAnimal {

    private double altitudeOfFlight;
    private BufferedImage img1;

    /**
     * Constructor to initialize an Eagle object.
     *
     * @param x                The x coordinate of the eagle.
     * @param y                The y coordinate of the eagle.
     * @param totalDistance    The total distance covered by the eagle.
     * @param gender           The gender of the eagle.
     * @param name             The name of the eagle.
     * @param weight           The weight of the eagle.
     * @param speed            The speed of the eagle.
     * @param medals           The array of medals won by the eagle.
     * @param orien            The orientation of the eagle.
     * @param maxEnergy        The maximum energy of the eagle.
     * @param energyPerMeter   The energy consumed per meter by the eagle.
     * @param wingspan         The wingspan of the eagle.
     * @param altitudeOfFlight The altitude of flight of the eagle.
     * @param competitionPanel The competition panel for the eagle.
     */
    public Eagle(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double wingspan, double altitudeOfFlight, CompetitionPanel competitionPanel) {
        super(x, y, totalDistance, gender, name, weight, speed, medals, orien, maxEnergy, energyPerMeter, wingspan, competitionPanel);
        this.altitudeOfFlight = altitudeOfFlight;
        loadImages("animals/" + name + ".png");
    }

    /**
     * Default constructor to initialize an Eagle object with default values.
     */
    public Eagle() {
        super();
        this.altitudeOfFlight = 0;
    }

    /**
     * Method to make the eagle produce its sound.
     *
     * @return The sound of the eagle.
     */
    protected String getSound() {
        return "Clack-wack-chack";
    }

    /**
     * Provides a string representation of the Eagle object.
     *
     * @return A string representation of the Eagle object.
     */
    @Override
    public String toString() {
        return String.format("Eagle{%s, altitudeOfFlight=%.2f}", super.toString(), altitudeOfFlight);
    }

    /**
     * Compares if two Eagle objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Eagle)) return false;
        Eagle other = (Eagle) obj;
        return super.equals(obj) && Double.compare(other.altitudeOfFlight, altitudeOfFlight) == 0;
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType() {
        return "Eagle";
    }

    /**
     * Draws the eagle object.
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
     * Loads images for the eagle.
     *
     * @param nm The name of the image file.
     */
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/eagle1.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
