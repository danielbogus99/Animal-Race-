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

    public Dog(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, int noLegs, String breed, CompetitionPanel pan) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter, noLegs, pan);
        this.breed = breed;
        loadImages("fgh");
    }

    public Dog() {
        super();
        this.breed = "";
    }

    public String getBreed() {
        return breed;
    }

    protected String getSound() {
        return "Woof woof";
    }

    @Override
    public String toString() {
        return "Dog{" + super.toString() + ", breed='" + breed + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dog)) return false;
        Dog other = (Dog) obj;
        return super.equals(obj) && breed.equals(other.breed);
    }

    public String animalType() {
        return "Dog";
    }

    public void drawObject(Graphics g){

        switch (getOrientation()) {
            case EAST:
                g.drawImage(img1, location.getX(), location.getY()-size/10, size * 2, size, getPan());
                break;
            case SOUTH:
                g.drawImage(img2, location.getX(), location.getY()-size/10, size, size, getPan());
                break;
            case WEST:
                g.drawImage(img3, location.getX(), location.getY()-size/10, size * 2, size, getPan());
                break;
            case NORTH:
                g.drawImage(img4, location.getX()-size/2, location.getY()-size/10, size, size * 2, getPan());
                break;
        }
    }

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
