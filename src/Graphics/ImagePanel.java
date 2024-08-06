package Graphics;

import animals.Animal;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ImagePanel is a custom JPanel that displays a background image and draws animals on it.
 */
public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;
    private Animal[][] animalTeams;
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
            System.err.println("Error loading background image: " + e.getMessage());
            backgroundImage = null;
        }
        setLayout(null);
    }

    /**
     * Sets the animal teams to be displayed on this panel.
     *
     * @param animalTeams The array of animal teams to display.
     */
    public void setAnimalTeams(Animal[][] animalTeams) {
        synchronized (this) {
            this.animalTeams = animalTeams;
        }
        repaint();
    }

    /**
     * Paints the component, including the background image and the animals.
     *
     * @param g The Graphics context to use for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        Graphics2D g2d = (Graphics2D) g.create();
        if (animalTeams != null) {
            double scaleX = getWidth() / (double) preferredWidth;
            double scaleY = getHeight() / (double) preferredHeight;
            g2d.scale(scaleX, scaleY);
            synchronized (this) {
                for (Animal[] team : animalTeams) {
                    for (Animal animal : team) {
                        synchronized (animal) {
                            animal.drawObject(g2d); // Draw each animal
                        }
                    }
                }
            }
        }
        g2d.dispose(); // Dispose of the graphics context to release resources
    }

    /**
     * Gets the preferred height for scaling or the background image height if available.
     *
     * @return The height of the panel or background image.
     */
    public int getHeight2() {
        return getHeight(); // Return the current height of the panel
    }

    /**
     * Gets the preferred width for scaling or the background image width if available.
     *
     * @return The width of the panel or background image.
     */
    public int getWidth2() {
        return getWidth(); // Return the current width of the panel
    }
}
