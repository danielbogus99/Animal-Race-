package animals;

import Olympics.Medal;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Graphics.*;

/**
 * Represents a dog, a terrestrial animal that extends the TerrestrialAnimals class.
 */
public class Dog extends TerrestrialAnimals {

    private String breed;
    private BufferedImage img1, img2, img3, img4;

    /**
     * Constructor to initialize a Dog object.
     *
     * @param x              The x coordinate of the dog.
     * @param y              The y coordinate of the dog.
     * @param totalDistance  The total distance covered by the dog.
     * @param gender         The gender of the dog.
     * @param name           The name of the dog.
     * @param weight         The weight of the dog.
     * @param speed          The speed of the dog.
     * @param medals         The array of medals won by the dog.
     * @param orien          The orientation of the dog.
     * @param maxEnergy      The maximum energy of the dog.
     * @param energyPerMeter The energy consumed per meter by the dog.
     * @param noLegs         The number of legs of the dog.
     * @param breed          The breed of the dog.
     * @param pan            The competition panel for the dog.
     */
    public Dog(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, int noLegs, String breed, CompetitionPanel pan) {
        super(x, y, totalDistance, gender, name, weight, speed, medals, orien, maxEnergy, energyPerMeter, noLegs, pan);
        this.breed = breed;
        loadImages("fgh");
    }

    /**
     * Default constructor to initialize a Dog object with default values.
     */
    public Dog() {
        super();
        this.breed = "";
    }

    /**
     * Gets the breed of the dog.
     *
     * @return The breed of the dog.
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Method to make the dog produce its sound.
     *
     * @return The sound of the dog.
     */
    protected String getSound() {
        return "Woof woof";
    }

    /**
     * Returns a string representation of the Dog object.
     *
     * @return A string representation including the superclass representation and breed.
     */
    @Override
    public String toString() {
        return "Dog{" + super.toString() + ", breed='" + breed + '\'' + '}';
    }

    /**
     * Override of the equals method to compare if two Dog objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dog)) return false;
        Dog other = (Dog) obj;
        return super.equals(obj) && breed.equals(other.breed);
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType() {
        return "Dog";
    }

    /**
     * Draws the dog object.
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
     * Loads images for the dog.
     *
     * @param nm The name of the image file.
     */
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/dog2E.png"));
            img2 = ImageIO.read(new File("src/graphics2/dog2S.png"));
            img3 = ImageIO.read(new File("src/graphics2/dog2W.png"));
            img4 = ImageIO.read(new File("src/graphics2/dog2N.png"));
            System.out.println("Image loaded successfully");
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
