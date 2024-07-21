package Graphics;

import animals.Animal;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;
    private List<Competition> competitions;

    public ImagePanel() {
        try {
            backgroundImage = ImageIO.read(new File("src/graphics2/competitionBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        if (competitions != null) {
            for (Competition competition : competitions) {
                for (Animal animal : competition.getAnimals()) {
                    animal.drawObject(g);
                }
            }
        }
    }
}
