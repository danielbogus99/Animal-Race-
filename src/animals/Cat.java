package animals;

import Olympics.Medal;
import Graphics.CompetitionPanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a cat, a terrestrial animal that extends the TerrestrialAnimals class.
 */
public class Cat extends TerrestrialAnimals {

    private boolean Castrated;
    private BufferedImage img1, img2, img3, img4;

    /**
     * Constructor to initialize a Cat object.
     *
     * @param x              The x coordinate of the cat.
     * @param y              The y coordinate of the cat.
     * @param totalDistance  The total distance covered by the cat.
     * @param gender         The gender of the cat.
     * @param name           The name of the cat.
     * @param weight         The weight of the cat.
     * @param speed          The speed of the cat.
     * @param medals         The array of medals won by the cat.
     * @param orien          The orientation of the cat.
     * @param maxEnergy      The maximum energy of the cat.
     * @param energyPerMeter The energy consumed per meter by the cat.
     * @param noLegs         The number of legs of the cat.
     * @param Castrated      Whether the cat is castrated or not.
     * @param competitionPanel The competition panel for the cat.
     */
    public Cat(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, int noLegs, boolean Castrated, CompetitionPanel competitionPanel) {
        super(x, y, totalDistance, gender, name, weight, speed, medals, orien, maxEnergy, energyPerMeter, noLegs, competitionPanel);
        this.Castrated = Castrated;
        loadImages("animals/" + name + ".png");
    }

    /**
     * Default constructor to initialize a Cat object with default values.
     */
    public Cat() {
        super();
        this.Castrated = false;
    }

    /**
     * Method to make the cat produce its sound.
     *
     * @return The sound of the cat.
     */
    protected String getSound() {
        return "Meow";
    }

    /**
     * Override of the equals method to compare if two Cat objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cat)) return false;
        Cat other = (Cat) obj;
        return super.equals(obj) && other.Castrated == Castrated;
    }

    /**
     * Returns a string representation of the Cat object.
     *
     * @return A string representation including the superclass representation and castration status.
     */
    @Override
    public String toString() {
        return String.format("Cat{%s, Castrated=%b}", super.toString(), Castrated);
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType() {
        return "Cat";
    }

    /**
     * Draws the cat object.
     *
     * @param g The graphics context.
     */
    public void drawObject(Graphics g) {
        switch (getOrientation()) {
            case EAST:
                g.drawImage(img1, location.getX(), location.getY() - size / 10, size * 2, size, getPan());
                break;
            case SOUTH:
                g.drawImage(img2, location.getX(), location.getY() - size / 10, size, size, getPan());
                break;
            case WEST:
                g.drawImage(img3, location.getX(), location.getY() - size / 10, size * 2, size, getPan());
                break;
            case NORTH:
                g.drawImage(img4, location.getX() - size / 2, location.getY() - size / 10, size, size * 2, getPan());
                break;
        }
    }

    /**
     * Loads images for the cat.
     *
     * @param nm The name of the image file.
     */
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/cat1.East.png"));
            img2 = ImageIO.read(new File("src/graphics2/cat1.S.png"));
            img3 = ImageIO.read(new File("src/graphics2/cat1.png"));
            img4 = ImageIO.read(new File("src/graphics2/cat1.N.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
