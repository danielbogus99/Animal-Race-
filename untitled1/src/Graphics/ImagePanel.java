package Graphics;
import Olympics.Medal;
import animals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;
    private ArrayList<Animal> animals = new ArrayList<>();
    private BufferedImage selectedImage;
    private BufferedImage DogImage;
    private BufferedImage CatImage;
    private BufferedImage alligatorImage;
    private BufferedImage dolphinImage;
    private BufferedImage eagleImage;
    private BufferedImage pigeonImage;
    private BufferedImage snakeImage;
    private BufferedImage WhaleImage;



    public ImagePanel() {
        try {
            backgroundImage = ImageIO.read(new File("untitled1/src/graphics2/competitionBackground2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadImages();
    }

    private void loadImages() {
        try {
            DogImage = ImageIO.read(new File("untitled1/src/graphics2/dog2.png"));
            CatImage = ImageIO.read(new File("untitled1/src/graphics2/cat1.png"));
            alligatorImage = ImageIO.read(new File("untitled1/src/graphics2/alligator1.png"));
            dolphinImage = ImageIO.read(new File("untitled1/src/graphics2/dolphin1.png"));
            eagleImage = ImageIO.read(new File("untitled1/src/graphics2/eagle1.png"));
            pigeonImage = ImageIO.read(new File("untitled1/src/graphics2/pigeon.png"));
            snakeImage = ImageIO.read(new File("untitled1/src/graphics2/snake1.png"));
            WhaleImage = ImageIO.read(new File("untitled1/src/graphics2/whale.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

    }




}
