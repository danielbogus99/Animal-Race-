package Graphics;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class CompetitionPanel extends JPanel {


    private JButton addCompetitionButton;
    private JButton addAnimal;
    private JButton clear;
    private JButton eat;
    private JButton info;
    private JButton exit;

    public CompetitionPanel() {
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
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        addCompetitionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        addAnimal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}
        });
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        eat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        info.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });


    }



}