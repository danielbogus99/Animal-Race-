package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;
    private String selectedAnimal;
    private int animalX = 50;
    private int animalY = 50;
    private BufferedImage selectedImage;

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
            selectedImage = ImageIO.read(new File("untitled1/src/graphics2/dog1.png"));
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

        if (selectedAnimal != null) {
            g.setColor(Color.RED);
            switch (selectedAnimal) {
                case "Dog":
                    g.drawImage(selectedImage, 0, 0, this);
                    break;
                case "Cat":
                    g.fillOval(animalX + 40, animalY, 30, 30);
                    break;
                // Add cases for other animals
                default:
                    g.fillOval(animalX, animalY, 30, 30);
                    break;
            }
        }
    }

    public void addAnimal(String animal) {
        this.selectedAnimal = animal;
        repaint();
    }
}
