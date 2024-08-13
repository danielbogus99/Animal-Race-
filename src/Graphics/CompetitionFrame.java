/**
 * Daniel Boguslavsky:207915729
 * Shain Simon:214223299
 */

package Graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main frame for the competition application.
 */
public class CompetitionFrame extends JFrame {
    private CompetitionPanel competitionPanel;
    private ImagePanel imagePanel;

    /**
     * Constructor to initialize the CompetitionFrame.
     */
    public CompetitionFrame() {
        super("Competition");

        setLayout(new BorderLayout());

        // Create menu bar and menus
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Create menu items
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        fileMenu.add(exitMenuItem);
        helpMenu.add(helpMenuItem);

        // Create and add the image panel
        imagePanel = new ImagePanel(this);
        add(imagePanel, BorderLayout.CENTER);

        // Create and add the competition panel
        competitionPanel = new CompetitionPanel(this, imagePanel);
        add(competitionPanel, BorderLayout.SOUTH);

        // Set default close operation and frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Add action listeners for menu items
        exitMenuItem.addActionListener(e -> System.exit(0));
        helpMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Home Work 2\n" + "GUI"));
        setLocationRelativeTo(null);

        setVisible(true);
    }

    /**
     * Gets the image panel.
     *
     * @return The image panel.
     */
    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    /**
     * Gets the competition panel.
     *
     * @return The competition panel.
     */
    public CompetitionPanel getCompetitionPanel() {
        return competitionPanel;
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        CompetitionFrame frame = new CompetitionFrame();
        frame.setVisible(true);
    }
}
