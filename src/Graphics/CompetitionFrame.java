package Graphics;

import javax.swing.*;
import java.awt.*;


public class CompetitionFrame extends JFrame
{
    private CompetitionPanel competitionPanel;
    private ImagePanel imagePanel;


    public CompetitionFrame() {
        super("Competition");

        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        fileMenu.add(exitMenuItem);
        helpMenu.add(helpMenuItem);

        imagePanel = new ImagePanel(this);
        add(imagePanel, BorderLayout.CENTER);


        competitionPanel = new CompetitionPanel(this,imagePanel);
        add(competitionPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);


        exitMenuItem.addActionListener(e -> System.exit(0));
        helpMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Home Work 2\n" + "GUI"));

        setVisible(true);
    }

    public ImagePanel getImagePanel()
    {
        return imagePanel;
    }

    public static void main(String[] args) {
        CompetitionFrame frame = new CompetitionFrame();
        frame.setVisible(true);
    }
    public CompetitionPanel getCompetitionPanel(){return competitionPanel;}

}
