package animals;

import Olympics.Medal;

import javax.imageio.ImageIO;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a snake, a terrestrial animal that extends the TerrestrialAnimals class.
 */
public class Snake extends TerrestrialAnimals implements IReptile{
    private BufferedImage img1, img2, img3, img4;
    private double length;
    private Poisonous poisonous;

    @Override
    public boolean speedUp(int speeds) {
        if(getSpeed() > Max_SPEED){
            System.out.println("The speed is over the max speed");
            return false;
        }
        addSpeed(speeds);
        return true;
    }

    /**
     * Enum defining the poisonous status of the snake.
     */
    public enum Poisonous {
        LOW, MEDIUM, HIGH,

    }

    /**
     * Constructor to initialize a Snake object.
     *
     * @param totalDistance The total distance covered by the snake.
     * @param gender        The gender of the snake.
     * @param name          The name of the snake.
     * @param weight        The weight of the snake.
     * @param speed         The speed of the snake.
     * @param medals        The array of medals won by the snake.
     * @param noLegs        The number of legs of the snake.
     * @param poison        The poisonous status of the snake.
     * @param length        The length of the snake.
     */
    public Snake(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, int noLegs, Poisonous poison, double length) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter, noLegs);
        this.length = length;
        this.poisonous = poison;
    }
    public Snake()
    {
        super();
        this.length = 0;
        this.poisonous = Poisonous.LOW;
    }

    /**
     * Method to make the snake produce its sound.
     */
    protected String getSound() {
       return  "ssssssss";
    }

    /**
     * Override of the toString method to provide a string representation of the Snake object.
     *
     * @return A string representation of the Snake object.
     */
    @Override
    public String toString() {
        return STR."Snake\{super.toString()}, poisonous=\{poisonous}, length=\{length}}";
    }

    /**
     * Override of the equals method to compare if two Snake objects are equal.
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
                other.length == length &&
                other.poisonous == poisonous;
    }
    public String animalType()
    {
        return "Snake";
    }

    public void drawObject(Graphics g)
    {
        switch (getOrientation()) {
            case EAST:
                g.drawImage(img1, location.getX(), location.getY() - size / 10, size * 2, size,getPan());
                break;
            case SOUTH:
                g.drawImage(img2, location.getX(), location.getY() - size / 10, size, size,getPan());
                break;
            case WEST:
                g.drawImage(img3, location.getX(), location.getY() - size / 10, size * 2, size, getPan());
                break;
            case NORTH:
                g.drawImage(img4, location.getX() - size / 2, location.getY() - size / 10, size, size * 2, getPan());
                break;
        }
    }
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("untitled1/src/graphics2/snake2.E.png"));
            img2 = ImageIO.read(new File("untitled1/src/graphics2/snake2.S.png"));
            img3 = ImageIO.read(new File("untitled1/src/graphics2/snake2.png"));
            img4 = ImageIO.read(new File("untitled1/src/graphics2/snake2.N.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
