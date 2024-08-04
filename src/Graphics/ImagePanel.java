package Graphics;

import animals.Animal;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ImagePanel is a custom JPanel that displays a background image and draws animals from competitions on it.
 */
public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;
    private List<Competition> competitions;
    private CompetitionFrame parentFrame;
    private final int preferredWidth = 1024;  // Default width for scaling
    private final int preferredHeight = 768;  // Default height for scaling

    /**
     * Constructs an ImagePanel with the specified parent frame.
     *
     * @param parentFrame The parent frame containing this panel.
     */
    public ImagePanel(CompetitionFrame parentFrame) {
        this.parentFrame = parentFrame;
        try {
            backgroundImage = ImageIO.read(new File("src/graphics2/competitionBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);
    }

    /**
     * Sets the competitions to be displayed on this panel.
     *
     * @param competitions The list of competitions to display.
     */
    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
        repaint();
    }

    /**
     * Paints the component, including the background image and the animals from the competitions.
     *
     * @param g The Graphics context to use for painting.
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (backgroundImage != null)
        {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        Graphics2D g2d = (Graphics2D) g;
        if (competitions != null) {
            double scaleX = getWidth() / (double) preferredWidth;
            double scaleY = getHeight() / (double) preferredHeight;
            g2d.scale(scaleX, scaleY);
            for (Competition competition : competitions)
            {
                for (Animal animal : competition.getAnimals())
                {
                    synchronized (animal) {
                        animal.drawObject(g2d);
                    }
                }
            }
        }
    }

    /**
     * Gets the height of the background image.
     *
     * @return The height of the background image.
     */
    public int getHeight2() {
        return backgroundImage.getHeight();
    }

    /**
     * Gets the width of the background image.
     *
     * @return The width of the background image.
     */
    public int getWidth2() {
        return backgroundImage.getWidth();
    }
}
