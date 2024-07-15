package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
public class CompetitionFrame extends JFrame
{

    private CompetitionPanel competitionPanel;
    private ImagePanel imagePanel;
    public CompetitionFrame() {

        super("Competition");

        setLayout(new BorderLayout());


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Add a menu to the menu bar
        JMenu file1 = new JMenu("File");
        JMenu help = new JMenu("Help");
        menuBar.add(file1);
        menuBar.add(help);

        // Add menu items to the menu
        JMenuItem menuItem1 = new JMenuItem("Exit");
        JMenuItem menuItem2 = new JMenuItem("Help");
        file1.add(menuItem1);
        help.add(menuItem2);


        // Create and add the competition panel
        imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.CENTER);
        competitionPanel = new CompetitionPanel();
        add(competitionPanel, BorderLayout.SOUTH);


        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        setSize(800, 800);
        menuItem1.addActionListener(e -> System.exit(0));
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, "Home Work 2\n" + "GUI");
            }});

        // Set the frame to be visible
        setVisible(true);
    }

    public void main(String[] args) {

        // Create a JPanel
        new CompetitionFrame();
    }
}
