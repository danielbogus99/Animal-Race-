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
 * ImagePanel is a custom JPanel that displays a background image and draws animals on it.
 */
public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;
    private List<Animal> animals;
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
     * Sets the list of animals to be displayed on this panel.
     *
     * @param animals The list of animals to display.
     */
    public void setAnimals(List<Animal> animals) {
        synchronized (this) {
            this.animals = animals;
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

        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw only the animals that are moving
        if (animals != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            double scaleX = getWidth() / (double) preferredWidth;
            double scaleY = getHeight() / (double) preferredHeight;
            g2d.scale(scaleX, scaleY);
            synchronized (this) {
                for (Animal animal : animals) {
                    synchronized (animal) {
                        // Only draw the animal if it is moving
                        if (animal.isMoving())
                        {
                            animal.drawObject(g2d);
                        }
                    }
                }
            }
            g2d.dispose(); // Dispose of the graphics context to release resources
        }
    }

    /**
     * Gets the preferred height for scaling or the background image height if available.
     *
     * @return The height of the panel or background image.
     */
    public int getHeight2() {
        return preferredHeight; // Return the preferred height for scaling
    }

    /**
     * Gets the preferred width for scaling or the background image width if available.
     *
     * @return The width of the panel or background image.
     */
    public int getWidth2() {
        return preferredWidth; // Return the preferred width for scaling
    }
}
