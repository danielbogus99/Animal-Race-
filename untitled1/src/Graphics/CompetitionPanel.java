package Graphics;
import Olympics.Medal;
import animals.Animal;
import animals.Dog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CompetitionPanel extends JPanel {

    private JButton addCompetitionButton;
    private JButton addAnimalButton;
    private JButton clearButton;
    private JButton eatButton;
    private JButton infoButton;
    private JButton exitButton;

    private List<Competition> competitions = new ArrayList<>();
    private Animal selectedAnimal;
    private CompetitionFrame parentFrame;

    public CompetitionPanel(CompetitionFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridLayout(1, 0));

        addCompetitionButton = new JButton("Add Competition");
        addAnimalButton = new JButton("Add Animal");
        clearButton = new JButton("Clear");
        eatButton = new JButton("Eat");
        infoButton = new JButton("Info");
        exitButton = new JButton("Exit");

        add(addCompetitionButton);
        add(addAnimalButton);
        add(clearButton);
        add(eatButton);
        add(infoButton);
        add(exitButton);

        exitButton.addActionListener(e -> System.exit(0));
        addCompetitionButton.addActionListener(e -> addCompetition());
        addAnimalButton.addActionListener(e -> addAnimal());
        // infoButton.addActionListener(e -> info());
        clearButton.addActionListener(e -> clear());
        eatButton.addActionListener(e -> eat());
    }
    private void addCompetition() {
        AddCompetitionDialog addCompetitionDialog = new AddCompetitionDialog(parentFrame);
        addCompetitionDialog.setVisible(true);

        String newCompetition = addCompetitionDialog.getSelectedCompetitionType();
        if (newCompetition != null) {
            competitions.add(new Competition(newCompetition));
        } else {
            System.out.println("Competition not added.");
        }
    }
    private void addAnimal() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (competitions.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "You need to select at least one competition.");
            return;
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Competition Type Selection");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        JComboBox<String> competitionTypeComboBox = new JComboBox<>(competitions.stream().map(Competition::getName).toArray(String[]::new));
        competitionTypeComboBox.setSelectedIndex(0);
        competitionTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        JButton selectTypeOfCompetitionButton = new JButton("Confirm");
        selectTypeOfCompetitionButton.setToolTipText("Click to confirm your selection");
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setToolTipText("Click to go back to the previous menu");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Include spacing for better layout
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around panel edges
        panel.add(competitionTypeComboBox, BorderLayout.NORTH);
        panel.add(selectTypeOfCompetitionButton, BorderLayout.CENTER);
        panel.add(goBackButton, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setLocationRelativeTo(parentFrame);
        frame.setVisible(true);
        goBackButton.addActionListener(e -> frame.dispose());
        selectTypeOfCompetitionButton.addActionListener(e ->
        {
            frame.dispose();
            String selectedCompetitionName = (String) competitionTypeComboBox.getSelectedItem();
            if (selectedCompetitionName != null)
            {
                AddAnimalDialog addAnimalDialog = new AddAnimalDialog(parentFrame, selectedCompetitionName);
                selectedAnimal = addAnimalDialog.getSelectedAnimalObject();
                if (selectedAnimal != null)
                {
                    for (Competition competition : competitions)
                    {
                        if (competition.getName().equals(selectedCompetitionName))
                        {
                            competition.addAnimal(selectedAnimal);
                            JOptionPane.showMessageDialog(parentFrame, selectedAnimal.getName() + " added to the " + selectedCompetitionName + " race");
                            break;
                        }
                    }
                }
            }
        });
    }
    private void eat() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (competitions.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "There are no competitions to clear.");
            return;
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Select Competition to Clear Animal");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        JComboBox<String> competitionTypeComboBox = new JComboBox<>(competitions.stream().map(Competition::getName).toArray(String[]::new));
        competitionTypeComboBox.setSelectedIndex(0);
        competitionTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        JButton selectTypeOfCompetitionButton = new JButton("Confirm");
        selectTypeOfCompetitionButton.setToolTipText("Click to confirm your selection");
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setToolTipText("Click to go back to the previous menu");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Include spacing for better layout
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around panel edges
        panel.add(competitionTypeComboBox, BorderLayout.NORTH);
        panel.add(selectTypeOfCompetitionButton, BorderLayout.CENTER);
        panel.add(goBackButton, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setLocationRelativeTo(parentFrame);
        frame.setVisible(true);
        goBackButton.addActionListener(e -> frame.dispose());
        selectTypeOfCompetitionButton.addActionListener(e ->
        {
            frame.dispose();
            String selectedCompetitionName = (String) competitionTypeComboBox.getSelectedItem();
            if (selectedCompetitionName != null)
            {
                for (Competition competition : competitions)
                {
                    if (competition.getName().equals(selectedCompetitionName))
                    {
                        List<Animal> animalList = competition.getAnimals();
                        if (animalList.isEmpty())
                        {
                            JOptionPane.showMessageDialog(parentFrame, "There are no animals in this competition.");
                            return;
                        }
                        JFrame GiveToEatFrame = new JFrame("Give To Eat");
                        GiveToEatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        GiveToEatFrame.setSize(300, 200);

                        JComboBox<Animal> animalComboBox = new JComboBox<>(animalList.toArray(new Animal[0]));
                        animalComboBox.setFont(new Font("Arial", Font.PLAIN, 15));

                        animalComboBox.setRenderer(new DefaultListCellRenderer() {
                            @Override
                            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                                if (value instanceof Animal)
                                {
                                    setText(((Animal) value).getName());
                                }
                                return this;
                            }
                        });

                        JButton EatAnimalButton = new JButton("Add Energy");
                        JButton GoBackButton = new JButton("Go Back");
                        EatAnimalButton.setToolTipText("Click to give the selected animal more energy");

                        JPanel EatPanel = new JPanel();
                        EatPanel.setLayout(new BorderLayout(10, 10));
                        EatPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        EatPanel.add(animalComboBox, BorderLayout.NORTH);
                        EatPanel.add(GoBackButton, BorderLayout.SOUTH);
                        EatPanel.add(EatAnimalButton, BorderLayout.CENTER);

                        GiveToEatFrame.add(EatPanel);
                        GiveToEatFrame.setLocationRelativeTo(parentFrame);
                        GiveToEatFrame.setVisible(true);

                        EatAnimalButton.addActionListener(e1 -> {
                            Animal selectedAnimal = (Animal) animalComboBox.getSelectedItem();
                            if (selectedAnimal == null) {
                                JOptionPane.showMessageDialog(GiveToEatFrame, "No animal selected.");
                                return;
                            }

                            JDialog EATDialog = new JDialog(parentFrame, "Eat Animal", true);
                            EATDialog.setSize(265, 150);
                            EATDialog.setLayout(new BorderLayout());

                            JTextField EatField = new JTextField(20); // Adjust the width here
                            EatField.setPreferredSize(new Dimension(100, 25));
                            JPanel eatPanel = new JPanel(new BorderLayout(5, 5));
                            eatPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                            eatPanel.add(new JLabel("Enter an integer to add to the energy:"), BorderLayout.NORTH);
                            eatPanel.add(EatField,BorderLayout.EAST);

                            JPanel buttonPanel = new JPanel(new FlowLayout());
                            JButton submitButton = new JButton("Submit");
                            JButton backButton = new JButton("Go back");
                            buttonPanel.add(submitButton);
                            buttonPanel.add(backButton);

                            eatPanel.add(buttonPanel, BorderLayout.SOUTH);
                            EATDialog.add(eatPanel);

                            submitButton.addActionListener(e3 -> {
                                try {
                                    int energy = Integer.parseInt(EatField.getText());
                                    if (energy < 0)
                                    {
                                        throw new Exception("Energy should be greater than 0.");
                                    }
                                    selectedAnimal.eat(energy);
                                    JOptionPane.showMessageDialog(EATDialog, "Energy added successfully.");
                                    EATDialog.dispose();
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(EATDialog, ex.getMessage());
                                }
                            });

                            backButton.addActionListener(e2 -> EATDialog.dispose());

                            EATDialog.setLocationRelativeTo(GiveToEatFrame);
                            EATDialog.setVisible(true);
                        });

                        GoBackButton.addActionListener(e2 -> GiveToEatFrame.dispose());
                    }
                }
            }
        });
    }
    private void clear() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (competitions.isEmpty())
        {
            JOptionPane.showMessageDialog(parentFrame, "There are no competitions to clear.");
            return;
        }
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Select Competition to eat an Animal");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        JComboBox<String> competitionTypeComboBox = new JComboBox<>(competitions.stream().map(Competition::getName).toArray(String[]::new));
        competitionTypeComboBox.setSelectedIndex(0);
        competitionTypeComboBox.setFont(new Font("Arial", Font.ITALIC, 15));
        JButton selectTypeOfCompetitionButton = new JButton("Confirm");
        selectTypeOfCompetitionButton.setToolTipText("Click to confirm your selection");
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setToolTipText("Click to go back to the previous menu");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Include spacing for better layout
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around panel edges
        panel.add(competitionTypeComboBox, BorderLayout.NORTH);
        panel.add(selectTypeOfCompetitionButton, BorderLayout.CENTER);
        panel.add(goBackButton, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setLocationRelativeTo(parentFrame);
        frame.setVisible(true);
        goBackButton.addActionListener(e -> frame.dispose());
        selectTypeOfCompetitionButton.addActionListener(e -> {
            frame.dispose();
            String selectedCompetitionName = (String) competitionTypeComboBox.getSelectedItem();
            if (selectedCompetitionName != null) {
                for (Competition competition : competitions) {
                    if (competition.getName().equals(selectedCompetitionName))
                    {
                        List<Animal> animalList = competition.getAnimals();
                        if (animalList.isEmpty())
                        {
                            JOptionPane.showMessageDialog(parentFrame, "There are no animals in this competition.");
                            return;
                        }
                        JFrame removeFrame = new JFrame("Select Animal to Remove");
                        removeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        removeFrame.setSize(300, 150);
                        JComboBox<String> animalComboBox = new JComboBox<>(animalList.stream().map(Animal::getName).toArray(String[]::new));
                        animalComboBox.setSelectedIndex(0);
                        animalComboBox.setFont(new Font("Arial", Font.ITALIC, 15));
                        JButton removeAnimalButton = new JButton("Remove");
                        JButton GoBackButton = new JButton("Go Back");
                        removeAnimalButton.setToolTipText("Click to remove selected animal");
                        JPanel removePanel = new JPanel();
                        removePanel.setLayout(new BorderLayout(10, 10));
                        removePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        removePanel.add(animalComboBox, BorderLayout.NORTH);
                        removePanel.add(GoBackButton, BorderLayout.SOUTH);
                        removePanel.add(removeAnimalButton, BorderLayout.CENTER);
                        removeFrame.add(removePanel);
                        removeFrame.setLocationRelativeTo(parentFrame);
                        removeFrame.setVisible(true);
                        removeAnimalButton.addActionListener(e2 ->
                        {
                            String selectedAnimalName = (String) animalComboBox.getSelectedItem();
                            animalList.removeIf(animal -> animal.getName().equals(selectedAnimalName));
                            JOptionPane.showMessageDialog(parentFrame, selectedAnimalName + " removed from " + selectedCompetitionName);
                            removeFrame.dispose();
                        });
                        GoBackButton.addActionListener(e1 -> {
                            removeFrame.dispose();
                        });
                        break;
                    }
                }
            }
        });
    }
    private void info()
    {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (competitions.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "There are no competitions to clear.");
            return;
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Select Competition to see the info about the Animals");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        JComboBox<String> competitionTypeComboBox = new JComboBox<>(competitions.stream().map(Competition::getName).toArray(String[]::new));
        competitionTypeComboBox.setSelectedIndex(0);
        competitionTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        JButton selectTypeOfCompetitionButton = new JButton("Confirm");
        selectTypeOfCompetitionButton.setToolTipText("Click to confirm your selection");
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setToolTipText("Click to go back to the previous menu");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Include spacing for better layout
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around panel edges
        panel.add(competitionTypeComboBox, BorderLayout.NORTH);
        panel.add(selectTypeOfCompetitionButton, BorderLayout.CENTER);
        panel.add(goBackButton, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setLocationRelativeTo(parentFrame);
        frame.setVisible(true);
        goBackButton.addActionListener(e -> frame.dispose());
        selectTypeOfCompetitionButton.addActionListener(e ->
        {
            frame.dispose();
            String selectedCompetitionName = (String) competitionTypeComboBox.getSelectedItem();
            if (selectedCompetitionName != null)
            {
                for (Competition competition : competitions)
                {
                    if (competition.getName().equals(selectedCompetitionName))
                    {
                        List<Animal> animalList = competition.getAnimals();
                        if (animalList.isEmpty())
                        {
                            JOptionPane.showMessageDialog(parentFrame, "There are no animals in this competition.");
                            return;
                        }
                        
                    }
                }
            }
        });
    }
}
