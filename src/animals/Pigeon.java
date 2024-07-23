package animals;

import Olympics.Medal;

import javax.imageio.ImageIO;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Graphics.CompetitionPanel;

/**
 * Represents a pigeon, an air-dwelling animal that extends the AirAnimal class.
 */
public class Pigeon extends AirAnimal {

    private String family;
    private BufferedImage img1;

    /**
     * Constructor to initialize a Pigeon object.
     *
     * @param totalDistance The total distance covered by the pigeon.
     * @param gender        The gender of the pigeon.
     * @param name          The name of the pigeon.
     * @param weight        The weight of the pigeon.
     * @param speed         The speed of the pigeon.
     * @param medals        The array of medals won by the pigeon.
     * @param wingspan      The wingspan of the pigeon.
     * @param family        The family of the pigeon.
     */
    public Pigeon(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double wingspan, String family,CompetitionPanel competitionPanel) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter, wingspan,competitionPanel);
        this.family = family;
        loadImages("animals/" + name + ".png");
    }
    public Pigeon()
    {
        super();
        this.family = "";
    }

    /**
     * Method to make the pigeon produce its sound.
     */
    protected String getSound() {
        return  "Arr-rar-rar-rar-raah";
    }

    /**
     * Override of the toString method to provide a string representation of the Pigeon object.
     *
     * @return A string representation of the Pigeon object.
     */
    @Override
    public String toString() {
        return STR."Pigeon\{super.toString()}, family=\{family}}";
    }

    /**
     * Override of the equals method to compare if two Pigeon objects are equal.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pigeon)) return false;
        Pigeon other = (Pigeon) obj;
        return super.equals(obj) &&
                other.family.equals(family);
    }
    public String animalType()
    {
        return "Pigeon";
    }
    public void drawObject(Graphics g)
    {
        switch (getOrientation()) {
            case EAST:
                g.drawImage(img1, location.getX(), location.getY() - size / 10, size , size,getPan());
                break;
        }
    }
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/pigeon.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
