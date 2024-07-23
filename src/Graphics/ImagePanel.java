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
    private CompetitionFrame parentFrame;
    private final int preferredWidth = 1024;  // Default width for scaling
    private final int preferredHeight = 768;  // Default height for scaling

    public ImagePanel(CompetitionFrame parentFrame) {
        this.parentFrame = parentFrame;
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
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        Graphics2D g2d = (Graphics2D) g;
        if (competitions != null) {
            double scaleX = getWidth() / (double) preferredWidth;
            double scaleY = getHeight() / (double) preferredHeight;
            g2d.scale(scaleX, scaleY);
            for (Competition competition : competitions) {
                for (Animal animal : competition.getAnimals()) {
                    animal.drawObject(g2d);
                }
            }
        }
    }

    public int getHeight2() {
        return backgroundImage.getHeight();
    }

    public int getWidth2() {
        return backgroundImage.getWidth();
    }
}
