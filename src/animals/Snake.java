package animals;

import Olympics.Medal;
import Graphics.CompetitionPanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a snake, a terrestrial animal that extends the TerrestrialAnimals class.
 */
public class Snake extends TerrestrialAnimals implements IReptile {

    private BufferedImage img1, img2, img3, img4;
    private double length;
    private Poisonous poisonous;

    /**
     * Enum defining the poisonous status of the snake.
     */
    public enum Poisonous {
        LOW, MEDIUM, HIGH
    }

    /**
     * Constructor to initialize a Snake object.
     *
     * @param x              The x coordinate of the snake.
     * @param y              The y coordinate of the snake.
     * @param totalDistance  The total distance covered by the snake.
     * @param gender         The gender of the snake.
     * @param name           The name of the snake.
     * @param weight         The weight of the snake.
     * @param speed          The speed of the snake.
     * @param medals         The array of medals won by the snake.
     * @param orien          The orientation of the snake.
     * @param maxEnergy      The maximum energy of the snake.
     * @param energyPerMeter The energy consumed per meter by the snake.
     * @param noLegs         The number of legs of the snake.
     * @param poison         The poisonous status of the snake.
     * @param length         The length of the snake.
     * @param competitionPanel The competition panel for the snake.
     */
    public Snake(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, int noLegs, Poisonous poison, double length, CompetitionPanel competitionPanel) {
        super(x, y, totalDistance, gender, name, weight, speed, medals, orien, maxEnergy, energyPerMeter, noLegs, competitionPanel);
        this.length = length;
        this.poisonous = poison;
        loadImages("animals/" + name + ".png");
    }

    /**
     * Default constructor to initialize a Snake object with default values.
     */
    public Snake() {
        super();
        this.length = 0;
        this.poisonous = Poisonous.LOW;
    }

    /**
     * Method to make the snake produce its sound.
     *
     * @return The sound of the snake.
     */
    protected String getSound() {
        return "ssssssss";
    }

    /**
     * Provides a string representation of the Snake object.
     *
     * @return A string representation of the Snake object.
     */
    @Override
    public String toString() {
        return String.format("Snake{%s, poisonous=%s, length=%.2f}", super.toString(), poisonous, length);
    }

    /**
     * Compares if two Snake objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Snake)) return false;
        Snake other = (Snake) obj;
        return super.equals(obj) &&
                Double.compare(other.length, length) == 0 &&
                other.poisonous == poisonous;
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType() {
        return "Snake";
    }

    /**
     * Draws the snake object.
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
     * Loads images for the snake.
     *
     * @param nm The name of the image file.
     */
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/snake2.E.png"));
            img2 = ImageIO.read(new File("src/graphics2/snake2.S.png"));
            img3 = ImageIO.read(new File("src/graphics2/snake2.png"));
            img4 = ImageIO.read(new File("src/graphics2/snake2.N.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }

    /**
     * Increases the speed of the snake.
     *
     * @param speeds The amount of speed to increase.
     * @return True if the speed was successfully increased, false otherwise.
     */
    @Override
    public boolean speedUp(int speeds) {
        if (getSpeed() > Max_SPEED) {
            System.out.println("The speed is over the max speed");
            return false;
        }
        addSpeed(speeds);
        return true;
    }
}
