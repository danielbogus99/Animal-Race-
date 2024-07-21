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

public abstract class Animal extends Mobile implements IDrawable,IAnimal ,IMoveable, ILocatable,IClonable {
    private String name;
    private double weight;
    private int speed;
    private Medal[] medals;
    private gender gender;
    protected int size = 65;
    private Orientation orien;
    private int maxEnergy;
    private int energyPerMeter;
    private int CurrentEnergy;
    private int totalConsumption = 0;
    private CompetitionPanel pan;
    private BufferedImage img1, img2, img3, img4;


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
        this.CurrentEnergy = maxEnergy;
        this.pan = pan;
        loadImages("animals/" + name + ".png");
    }

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
        this.CurrentEnergy = maxEnergy;
    }

    abstract protected String getSound();

    public void makeSound() {
        System.out.println("Animal " + getAnimaleName() + " said " + getSound());
    }

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
                ", currentEnergy=" + CurrentEnergy +
                ", totalConsumption=" + totalConsumption +
                '}';
    }



    public String getGender() {
        return gender.name();
    }

    public double getWeight() {
        return weight;
    }
    public String getAnimaleName()
    {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    protected void addSpeed(int speed) {
        this.speed += speed;
    }

    public Medal[] getMedals() {
        return medals;
    }



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

    public enum Orientation {
        NORTH, EAST, SOUTH, WEST
    }

    public enum gender {Male, Female, Hermaphrodite}

    public String animalType() {
        return "Animal";
    }

    public void loadImages(String nm) {
        try {img1 = ImageIO.read(new File("")); }
        catch (IOException e) { System.out.println("Cannot load image"); }

    }

    public void drawObject(Graphics g)
    {
        if (orien == Orientation.EAST) {
            g.drawImage(img1, getLocation().getX(), getLocation().getY() - size / 10, size * 2, size,  pan);
        } else if (orien == Orientation.SOUTH) {
            g.drawImage(img2, getLocation().getX(), getLocation().getY() - size / 10, size, size, pan);
        } else if (orien == Orientation.WEST) {
            g.drawImage(img3, getLocation().getX(), getLocation().getY() - size / 10, size * 2, size,  pan);
        } else if (orien == Orientation.NORTH) {
            g.drawImage(img4, getLocation().getX() - size / 2, getLocation().getY() - size / 10, size, size * 2,  pan);
        }
    }

    public boolean eat(int energy) {
        totalConsumption += energy;
        if (energy + CurrentEnergy > maxEnergy) {
            CurrentEnergy = maxEnergy;
            return true;
        }
        CurrentEnergy += energy;
        return true;
    }

    public void setOrientation(Orientation orientation)
    {
        this.orien = orientation;
    }

    public Orientation getOrientation() {
        return orien;
    }

    public String animalCategory() {
        return "Animal";
    }

    public int getCurrentEnergy() {
        return CurrentEnergy;
    }

    public int getTotalConsumption() {
        return totalConsumption;
    }
    public CompetitionPanel getPan() {
        return pan;
    }
}
