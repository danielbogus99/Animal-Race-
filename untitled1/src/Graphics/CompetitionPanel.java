package Graphics;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
class CompetitionPanel extends JPanel {


    private JButton addCompetitionButton;
    private JButton addAnimal;
    private JButton clear;
    private JButton eat;
    private JButton info;
    private JButton exit;

    public CompetitionPanel() {






        //JPanel buttonPanel = new JPanel();

        System.out.println("Competition Panel");
        setLayout(new GridLayout(1,0));




        addCompetitionButton = new JButton("Add Competition");
        addAnimal = new JButton("Add Animal");
        clear = new JButton("Clear");
        eat = new JButton("Eat");
        info = new JButton("Info");
        exit = new JButton("Exit");
        add(addCompetitionButton);
        add(addAnimal);
        add(clear);
        add(eat);
        add(info);
        add(exit);
//        addButtonToPanel(buttonPanel, addCompetitionButton);
//        addButtonToPanel(buttonPanel, addAnimal);
//        addButtonToPanel(buttonPanel, clear);
//        addButtonToPanel(buttonPanel, eat);
//        addButtonToPanel(buttonPanel, info);
//        addButtonToPanel(buttonPanel, exit);
//
//        add(buttonPanel, BorderLayout.SOUTH);


    }



    private void addButtonToPanel(JPanel panel, JButton button) {
        panel.add(button);
    }


}