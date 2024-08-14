package animals;

import Graphics.*;
import Graphics.IDrawable;
import Graphics.CompetitionPanel;
import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents an abstract Animal that is mobile and drawable.
 */
public abstract class Animal extends Mobile implements IDrawable, IAnimal, IMoveable, ILocatable, IClonable {
    private String name;
    private double weight;
    private int speed;
    private Medal[] medals;
    private gender gender;
    protected int size = 65;
    private Orientation orien;
    private int maxEnergy;
    private int energyPerMeter;
    private int currentEnergy;
    private int totalConsumption = 0;
    private CompetitionPanel pan;
    private BufferedImage img1, img2, img3, img4;
    private boolean moving = false;

    /**
     * Constructor to initialize an Animal object.
     *
     * @param position      The position of the animal.
     * @param totalDistance The total distance covered by the animal.
     * @param gender        The gender of the animal.
     * @param name          The name of the animal.
     * @param weight        The weight of the animal.
     * @param speed         The speed of the animal.
     * @param medals        The array of medals won by the animal.
     * @param orien         The orientation of the animal.
     * @param maxEnergy     The maximum energy of the animal.
     * @param energyPerMeter The energy consumed per meter by the animal.
     * @param pan           The competition panel for the animal.
     */
    public Animal(Point position, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan) {
        super(position, totalDistance);
        this.weight = weight;
        this.speed = speed;
        this.medals = medals;
        this.gender = gender;
        this.name = name;
        this.orien = orien;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        this.currentEnergy = maxEnergy;
        this.pan = pan;
        this.moving = false;

        loadImages("animals/" + name + ".png");
    }

    /**
     * Default constructor to initialize an Animal object with default values.
     */
    public Animal() {
        super();
        this.weight = 0;
        this.speed = 0;
        this.medals = null;
        this.gender = gender.Male;
        this.name = "";
        this.orien = Orientation.EAST;
        this.maxEnergy = 0;
        this.energyPerMeter = 0;
        this.currentEnergy = maxEnergy;
    }

    /**
     * Abstract method to get the sound of the animal.
     *
     * @return The sound of the animal.
     */
    abstract protected String getSound();

    /**
     * Method to make the animal produce its sound.
     */
    public void makeSound() {
        System.out.println("Animal " + getAnimalName() + " said " + getSound());
    }

    /**
     * Provides a string representation of the Animal object.
     *
     * @return A string representation of the Animal object.
     */
    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + Arrays.toString(medals) +
                ", gender=" + gender +
                ", orientation=" + orien +
                ", maxEnergy=" + maxEnergy +
                ", energyPerMeter=" + energyPerMeter +
                ", currentEnergy=" + currentEnergy +
                ", totalConsumption=" + totalConsumption +
                '}';
    }

    /**
     * Gets the gender of the animal.
     *
     * @return The gender of the animal.
     */
    public String getGender() {
        return gender.name();
    }

    /**
     * Gets the weight of the animal.
     *
     * @return The weight of the animal.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets the name of the animal.
     *
     * @return The name of the animal.
     */
    public String getAnimalName() {
        return name;
    }

    /**
     * Gets the speed of the animal.
     *
     * @return The speed of the animal.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Adds the specified speed to the animal's speed.
     *
     * @param speed The speed to add.
     */
    protected void addSpeed(int speed) {
        this.speed += speed;
    }

    /**
     * Gets the medals won by the animal.
     *
     * @return The medals won by the animal.
     */
    public Medal[] getMedals() {
        return medals;
    }

    /**
     * Compares if two Animal objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Animal)) return false;
        Animal other = (Animal) obj;
        return super.equals(obj) &&
                other.name.equals(name) &&
                other.getGender().equals(getGender()) &&
                other.getWeight() == getWeight() &&
                other.getSpeed() == getSpeed() &&
                Arrays.equals(other.getMedals(), getMedals()) &&
                other.orien == this.orien &&
                other.maxEnergy == this.maxEnergy &&
                other.energyPerMeter == this.energyPerMeter;
    }

    /**
     * Enum representing the orientation of the animal.
     */
    public enum Orientation {
        NORTH, EAST, SOUTH, WEST
    }

    /**
     * Enum representing the gender of the animal.
     */
    public enum gender {
        Male, Female, Hermaphrodite
    }

    /**
     * Gets the type of the animal.
     *
     * @return The type of the animal.
     */
    public String animalType() {
        return "Animal";
    }

    /**
     * Loads images for the animal.
     *
     * @param nm The name of the image file.
     */
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File(nm));
        } catch (IOException e) {
            System.out.println("Cannot load image");
        }
    }

    /**
     * Draws the animal object.
     *
     * @param g The graphics context.
     */
    public synchronized void drawObject(Graphics g) {
        if (orien == Orientation.EAST) {
            g.drawImage(img1, getLocation().getX(), getLocation().getY() - size / 10, size, size, pan);
        } else if (orien == Orientation.SOUTH) {
            g.drawImage(img2, getLocation().getX(), getLocation().getY() - size / 10, size, size, pan);
        } else if (orien == Orientation.WEST) {
            g.drawImage(img3, getLocation().getX(), getLocation().getY() - size / 10, size, size, pan);
        } else if (orien == Orientation.NORTH) {
            g.drawImage(img4, getLocation().getX(), getLocation().getY() - size / 10, size, size, pan);
        }
    }

    /**
     * Checks if the animal is out of energy.
     *
     * @return True if the animal is out of energy, false otherwise.
     */
    public boolean isOutOfEnergy() {
        if (currentEnergy < 0) {
            currentEnergy = 0;
            return true;
        }
        return false;
    }

    /**
     * Feeds the animal and increases its energy.
     *
     * @param energy The amount of energy to add.
     * @return True if the energy was successfully added, false otherwise.
     */
    public boolean eat(int energy) {
        totalConsumption += energy;
        if (energy + currentEnergy > maxEnergy) {
            currentEnergy = maxEnergy;
            return true;
        }
        currentEnergy += energy;
        return true;
    }

    /**
     * Resets the animal's state.
     */


    /**
     * Moves the animal and updates its position.
     *
     * @return The distance moved.
     */
    public synchronized double move() {
        if (currentEnergy <= 0) {
            return 0;
        }

        int x = getLocation().getX();
        int y = getLocation().getY();

        switch (orien) {
            case EAST:
                x += speed;
                break;
            case SOUTH:
                y += speed;
                break;
            case WEST:
                x -= speed;
                break;
            case NORTH:
                y -= speed;
                break;
        }

        // Update the total energy consumption by the distance moved
        totalConsumption += speed;

        // Update location and check boundaries
        setLocation(new Point(x, y));
        checkBoundsAndChangeDirection(this);

        return speed;
    }

    /**
     * Sets the Y-coordinate of the animal.
     *
     * @param y The Y-coordinate to set.
     */
    public void setY(int y) {
        location.setY(y);
    }

    /**
     * Checks the bounds and changes the animal's direction if necessary.
     *
     * @param animal The animal to check.
     */
    public void checkBoundsAndChangeDirection(Animal animal) {
        int x = animal.getLocation().getX();
        int y = animal.getLocation().getY();
        int backgroundWidth = new ImagePanel(null).getWidth2();
        int backgroundHeight = new ImagePanel(null).getHeight2();

        if (animal instanceof TerrestrialAnimals || animal instanceof ITerrestrailAnimal)
        {
            if (x >= backgroundWidth - 75 && y >= backgroundHeight - 75)
            {
                animal.setOrientation(Orientation.WEST);
            }
            else if (x >= backgroundWidth - 75 )
            {
                animal.setOrientation(Orientation.SOUTH);
            }
            else if (x <= 15 && y >= backgroundHeight - 155)
            {
                animal.setOrientation(Orientation.NORTH);
            }
            else if (x <= 30 && y <= 30) {
                animal.setOrientation(Orientation.EAST);
            }
        }
    }
    public void setX(int x) {
        location.setX(x);
    }


    /**
     * Resets the animal's position.
     *
     * @param animal The animal to reset.
     * @param x      The X-coordinate to reset to.
     */
    public void resetPosition(Animal animal, int x) {
        if (animal instanceof TerrestrialAnimals)
        {
            setLocation(new Point(0, 0));
        }
        else if (animal instanceof AirAnimal)
        {
            setLocation(new Point(0, location.getY()));
        }
        else
        {
            setLocation(new Point(x, location.getY()));
        }
    }
    public void setStartPosition(Point startPosition) {
        this.setLocation(startPosition);
    }


    /**
     * Checks if the animal is moving.
     *
     * @return True if the animal is moving, false otherwise.
     */
    public boolean isMoving() {
        return moving;
    }

    /**
     * Sets the animal to moving state.
     */
    public void setMoving() {
        moving = true;
    }

    /**
     * Sets the animal to not moving state.
     */
    public void setNotMoving() {
        moving = false;
    }

    /**
     * Decreases the animal's energy.
     */
    public void decreaseEnergy() {
        currentEnergy -= energyPerMeter;
    }

    /**
     * Sets the orientation of the animal.
     *
     * @param orientation The orientation to set.
     */
    public void setOrientation(Orientation orientation) {
        this.orien = orientation;
    }

    /**
     * Gets the orientation of the animal.
     *
     * @return The orientation of the animal.
     */
    public Orientation getOrientation() {
        return orien;
    }

    /**
     * Gets the category of the animal.
     *
     * @return The category of the animal.
     */
    public String animalCategory() {
        return "Animal";
    }

    /**
     * Gets the current energy of the animal.
     *
     * @return The current energy of the animal.
     */
    public int getCurrentEnergy() {
        return currentEnergy;
    }

    /**
     * Gets the total energy consumption of the animal.
     *
     * @return The total energy consumption of the animal.
     */
    public int getTotalConsumption() {
        return totalConsumption;
    }

    /**
     * Gets the competition panel of the animal.
     *
     * @return The competition panel of the animal.
     */
    public CompetitionPanel getPan() {
        return pan;
    }
}
