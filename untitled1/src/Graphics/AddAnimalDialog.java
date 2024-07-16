package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalDialog extends JDialog {

    private String selectedAnimal = null;

    public AddAnimalDialog(JFrame parent, String raceType) {
        super(parent, "Animal Selection", true); // Make the dialog modal
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel animalPanel = new JPanel();
        animalPanel.setLayout(new GridLayout(4, 4));

        JRadioButton dogButton = new JRadioButton("Dog");
        JRadioButton catButton = new JRadioButton("Cat");
        JRadioButton alligatorButton = new JRadioButton("Alligator");
        JRadioButton dolphinButton = new JRadioButton("Dolphin");
        JRadioButton eagleButton = new JRadioButton("Eagle");
        JRadioButton pigeonButton = new JRadioButton("Pigeon");
        JRadioButton snakeButton = new JRadioButton("Snake");
        JRadioButton whaleButton = new JRadioButton("Whale");

        ButtonGroup animalGroup = new ButtonGroup();
        animalGroup.add(dogButton);
        animalGroup.add(catButton);
        animalGroup.add(alligatorButton);
        animalGroup.add(dolphinButton);
        animalGroup.add(eagleButton);
        animalGroup.add(pigeonButton);
        animalGroup.add(whaleButton);
        animalGroup.add(snakeButton);

        animalPanel.add(dogButton);
        animalPanel.add(catButton);
        animalPanel.add(alligatorButton);
        animalPanel.add(dolphinButton);
        animalPanel.add(eagleButton);
        animalPanel.add(pigeonButton);
        animalPanel.add(whaleButton);
        animalPanel.add(snakeButton);

        add(animalPanel, BorderLayout.CENTER);

        JButton goBackButton = new JButton("Go Back");
        JButton confirmButton = new JButton("Confirm Animal Selection");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(goBackButton);
        buttonPanel.add(confirmButton);
        buttonPanel.setLayout(new FlowLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        goBackButton.addActionListener(e -> dispose());

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (dogButton.isSelected()) {
                        if (!raceType.equals("Terrestrial")) {
                            throw new IllegalArgumentException("You can't select a dog for a " + raceType + " race");
                        }
                        selectedAnimal = "Dog";
                    } else if (catButton.isSelected()) {
                        if (!raceType.equals("Terrestrial")) {
                            throw new IllegalArgumentException("You can't select a cat for a " + raceType + " race");
                        }
                        selectedAnimal = "Cat";
                    } else if (alligatorButton.isSelected()) {
                        if (raceType.equals("Air")) {
                            throw new IllegalArgumentException("You can't select an alligator for an " + raceType + " race");
                        }
                        selectedAnimal = "Alligator";
                    } else if (dolphinButton.isSelected()) {
                        if (!raceType.equals("Water")) {
                            throw new IllegalArgumentException("You can't select a dolphin for a " + raceType + " race");
                        }
                        selectedAnimal = "Dolphin";
                    } else if (whaleButton.isSelected())
                    {
                        if (!raceType.equals("Water"))
                        {
                            throw new IllegalArgumentException("You can't select a whale for a " + raceType + " race");
                        }
                        selectedAnimal = "Whale";
                    } else if (eagleButton.isSelected()) {
                        if (!raceType.equals("Air")) {
                            throw new IllegalArgumentException("You can't select an eagle for an " + raceType + " race");
                        }
                        selectedAnimal = "Eagle";
                    } else if (pigeonButton.isSelected()) {
                        if (!raceType.equals("Air")) {
                            throw new IllegalArgumentException("You can't select a pigeon for a " + raceType + " race");
                        }
                        selectedAnimal = "Pigeon";
                    } else if (snakeButton.isSelected()) {
                        if (!raceType.equals("Terrestrial")) {
                            throw new IllegalArgumentException("You can't select a snake for a " + raceType + " race");
                        }
                        selectedAnimal = "Snake";
                    }
                    dispose();
                } catch (IllegalArgumentException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public String getSelectedAnimal() {
        return selectedAnimal;
    }
}
