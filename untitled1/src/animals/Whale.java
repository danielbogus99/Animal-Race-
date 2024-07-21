package animals;

import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Graphics.CompetitionPanel;

public class Whale extends WaterAnimal {

    private String foodType;
    private BufferedImage img1;

    public Whale(int x, int y, double totalDistance, gender gender, String name, double weight, int speed, Medal[] medals, Orientation orien, int maxEnergy, int energyPerMeter, double diveDept, String foodType,CompetitionPanel competitionPanel) {
        super(x,y, totalDistance, gender, name, weight, speed, medals,orien,maxEnergy,energyPerMeter, diveDept,competitionPanel);
        this.foodType = foodType;
        loadImages("animals/" + name + ".png");
    }
    public Whale()
    {
        super();
        this.foodType = "";
    }
    protected String getSound() {
       return  "Splash";
    }

    @Override
    public String toString() {
        return STR."Whale\{super.toString()}, foodType=\{foodType}}";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Whale)) return false;
        Whale other = (Whale) obj;
        return super.equals(obj) &&
                other.foodType.equals(foodType);
    }
    public String animalType()
    {
        return "Whale";
    }
    public void drawObject(Graphics g)
    {
        switch (getOrientation()) {
            case EAST:
                g.drawImage(img1, location.getX(), location.getY() - size / 10, size * 2, size,getPan());
                break;
        }
    }
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/graphics2/whale2E.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image: " + nm);
        }
    }
}
