package Graphics;

import javax.swing.Timer;
import animals.*;
import competitions.RegularTournament;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a panel for managing competitions and animals within a competition.
 */
public class CompetitionPanel extends JPanel {

    private List<Competition> competitions = new ArrayList<>();
    private Animal selectedAnimal;
    private CompetitionFrame parentFrame;
    private ImagePanel imagePanel;
    private List<RegularRace> regularRace;
    private List<CourierRace> courierRace;
    private Timer timer;
    private RegularTournament currentTournament;

    /**
     * Constructor to initialize the CompetitionPanel.
     *
     * @param parentFrame The parent frame.
     * @param imagePanel  The image panel.
     */
    public CompetitionPanel(CompetitionFrame parentFrame, ImagePanel imagePanel) {
        this.parentFrame = parentFrame;
        setLayout(new GridLayout(1, 0));
        this.imagePanel = imagePanel;

        JButton addCompetitionButton = new JButton("Add Competition");
        JButton StartCompetitionButton = new JButton("Start Competition");
        JButton clearButton = new JButton("Clear");
        JButton eatButton = new JButton("Eat");
        JButton infoButton = new JButton("Info");
        JButton exitButton = new JButton("Exit");

        // Initialize the timer
        timer = new Timer(200, e -> updateCompetition());

        add(addCompetitionButton);
        add(StartCompetitionButton);
        add(clearButton);
        add(eatButton);
        add(infoButton);
        add(exitButton);

        exitButton.addActionListener(e -> System.exit(0));
        addCompetitionButton.addActionListener(e -> addCompetition());
        StartCompetitionButton.addActionListener(e -> startCompetition());
        infoButton.addActionListener(e -> info());
        clearButton.addActionListener(e -> clear());
        eatButton.addActionListener(e -> eat());
    }

    private void updateCompetition() {
        if (currentTournament != null && currentTournament.getStartFlag()) {
            // Update animals only if the race has started
            for (Competition competition : competitions) {
                for (Animal animal : competition.getAnimals()) {
                    if (!animal.isOutOfEnergy()) {
                        animal.move();
                        animal.decreaseEnergy();
                        checkBoundsAndChangeDirection(animal);
                    }
                }
            }
            imagePanel.repaint();
        }
    }

    private void checkBoundsAndChangeDirection(Animal animal) {
        int x = animal.getLocation().getX();
        int y = animal.getLocation().getY();
        int backgroundWidth = imagePanel.getWidth2();
        int backgroundHeight = imagePanel.getHeight2();

        if (animal instanceof AirAnimal) {
            if (x > backgroundWidth - 250) {
                animal.Stop();
            }
        } else if (animal instanceof ITerrestrailAnimal) {
            if (x > backgroundWidth - 260 && y > backgroundHeight - 155) {
                animal.setOrientation(Animal.Orientation.WEST);
            } else if (x > backgroundWidth - 260) {
                animal.setOrientation(Animal.Orientation.SOUTH);
            } else if (x < 15 && y > backgroundHeight - 155) {
                animal.setOrientation(Animal.Orientation.NORTH);
            }
        } else if (animal instanceof TerrestrialAnimals) {
            if (x > backgroundWidth - 260 && y > backgroundHeight - 155) {
                animal.setOrientation(Animal.Orientation.WEST);
            } else if (x > backgroundWidth - 260) {
                animal.setOrientation(Animal.Orientation.SOUTH);
            } else if (x < 15 && y > backgroundHeight - 155) {
                animal.setOrientation(Animal.Orientation.NORTH);
            }
        } else if (animal instanceof WaterAnimal) {
            if (x > backgroundWidth - 345) {
                animal.Stop();
            }
        }
    }

    private void startCompetition() {
        // Gather all race names from both regular and courier races
        List<String> raceNames = new ArrayList<>();
        if (regularRace != null) {
            for (RegularRace race : regularRace) {
                raceNames.add(race.getRaceName() + " (Regular)");
            }
        }
        if (courierRace != null) {
            for (CourierRace race : courierRace) {
                raceNames.add(race.getName() + " (Courier)");
            }
        }

        if (raceNames.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "No races are available to start.");
            return;
        }

        // Create a selection dialog for race names
        String selectedRaceName = (String) JOptionPane.showInputDialog(
                parentFrame,
                "Select a race to start:",
                "Start Competition",
                JOptionPane.PLAIN_MESSAGE,
                null,
                raceNames.toArray(),
                raceNames.get(0));

        if (selectedRaceName != null) {
            // Determine the type of race and start it
            if (selectedRaceName.endsWith("(Regular)")) {
                String raceName = selectedRaceName.replace(" (Regular)", "");
                RegularRace selectedRace = regularRace.stream()
                        .filter(race -> race.getRaceName().equals(raceName))
                        .findFirst()
                        .orElse(null);
                if (selectedRace != null) {
                    startRegularRace(selectedRace);
                }
            } else if (selectedRaceName.endsWith("(Courier)")) {
                String raceName = selectedRaceName.replace(" (Courier)", "");
                CourierRace selectedRace = courierRace.stream()
                        .filter(race -> race.getName().equals(raceName))
                        .findFirst()
                        .orElse(null);
                if (selectedRace != null) {
                    startCourierRace(selectedRace);
                }
            }
        }
    }

    // Method to start a regular race
    private void startRegularRace(RegularRace race) {
        Animal[][] animalTeams = race.toAnimalTeams();
        currentTournament = new RegularTournament(animalTeams, null);

        // Start the tournament race
        currentTournament.startRace();

        // Add tournament competitions to the update cycle
        for (Animal[] team : animalTeams) {
            if (team.length > 0) {
                Competition competition = new Competition(race.getRaceName(), "Regular");
                for (Animal animal : team) {
                    competition.addAnimal(animal);
                }
                competitions.add(competition);
            }
        }

        // Start the timer if not already running
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    // Placeholder method to start a courier race
    private void startCourierRace(CourierRace race) {
        JOptionPane.showMessageDialog(parentFrame, "Starting Courier Race: " + race.getName());
        // Implement the logic to start the courier race
    }

    private void addCompetition() {
        AddCompetitionDialog addCompetitionDialog = new AddCompetitionDialog(parentFrame);
        addCompetitionDialog.setVisible(true);

        String newCompetitionType = addCompetitionDialog.getSelectedCompetitionType();
        String newCompetitionName = addCompetitionDialog.getCompetitionName();
        if (newCompetitionType != null && newCompetitionName != null) {
            competitions.add(new Competition(newCompetitionName, newCompetitionType));
        } else {
            System.out.println("Competition not added.");
        }
        regularRace = addCompetitionDialog.getAllRegularRaces();
        courierRace = addCompetitionDialog.getAllCourierRaces();
    }

    /**
     * Feeds an animal in a competition.
     */
    private void eat() {
        if (competitions.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "There are no competitions to select.");
            return;
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Select Competition to Feed Animal");
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
            if (selectedCompetitionName != null) {
                Competition selectedCompetition = competitions.stream().filter(c -> c.getName().equals(selectedCompetitionName)).findFirst().orElse(null);
                if (selectedCompetition != null) {
                    List<Animal> animalList = selectedCompetition.getAnimals();
                    if (animalList.isEmpty()) {
                        JOptionPane.showMessageDialog(parentFrame, "There are no animals in this competition.");
                        return;
                    }
                    JFrame giveToEatFrame = new JFrame("Feed Animal");
                    giveToEatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    giveToEatFrame.setSize(300, 200);

                    JComboBox<Animal> animalComboBox = new JComboBox<>(animalList.toArray(new Animal[0]));
                    animalComboBox.setFont(new Font("Arial", Font.PLAIN, 15));

                    animalComboBox.setRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                            if (value instanceof Animal) {
                                setText(((Animal) value).getAnimaleName());
                            }
                            return this;
                        }
                    });

                    JButton eatAnimalButton = new JButton("Add Energy");
                    JButton goBackButtonInner = new JButton("Go Back");
                    eatAnimalButton.setToolTipText("Click to give the selected animal more energy");

                    JPanel eatPanel = new JPanel();
                    eatPanel.setLayout(new BorderLayout(10, 10));
                    eatPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    eatPanel.add(animalComboBox, BorderLayout.NORTH);
                    eatPanel.add(goBackButtonInner, BorderLayout.SOUTH);
                    eatPanel.add(eatAnimalButton, BorderLayout.CENTER);

                    giveToEatFrame.add(eatPanel);
                    giveToEatFrame.setLocationRelativeTo(parentFrame);
                    giveToEatFrame.setVisible(true);

                    eatAnimalButton.addActionListener(e1 -> {
                        Animal selectedAnimal = (Animal) animalComboBox.getSelectedItem();
                        if (selectedAnimal == null) {
                            JOptionPane.showMessageDialog(giveToEatFrame, "No animal selected.");
                            return;
                        }

                        JDialog eatDialog = new JDialog(parentFrame, "Add Energy", true);
                        eatDialog.setSize(265, 150);
                        eatDialog.setLayout(new BorderLayout());

                        JTextField eatField = new JTextField(20); // Adjust the width here
                        eatField.setPreferredSize(new Dimension(100, 25));
                        JPanel eatDialogPanel = new JPanel(new BorderLayout(5, 5));
                        eatDialogPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                        eatDialogPanel.add(new JLabel("Enter an integer to add to the energy:"), BorderLayout.NORTH);
                        eatDialogPanel.add(eatField, BorderLayout.EAST);

                        JPanel buttonPanel = new JPanel(new FlowLayout());
                        JButton submitButton = new JButton("Submit");
                        JButton backButton = new JButton("Go back");
                        buttonPanel.add(submitButton);
                        buttonPanel.add(backButton);

                        eatDialogPanel.add(buttonPanel, BorderLayout.SOUTH);
                        eatDialog.add(eatDialogPanel);

                        submitButton.addActionListener(e3 -> {
                            try {
                                int energy = Integer.parseInt(eatField.getText());
                                if (energy < 0) {
                                    throw new Exception("Energy should be greater than 0.");
                                }
                                selectedAnimal.eat(energy);
                                JOptionPane.showMessageDialog(eatDialog, "Energy added successfully.");
                                eatDialog.dispose();
                                parentFrame.getImagePanel().setCompetitions(competitions);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(eatDialog, ex.getMessage());
                            }
                        });

                        backButton.addActionListener(e2 -> eatDialog.dispose());

                        eatDialog.setLocationRelativeTo(giveToEatFrame);
                        eatDialog.setVisible(true);
                    });

                    goBackButtonInner.addActionListener(e2 -> giveToEatFrame.dispose());
                }
            }
        });
    }

    /**
     * Clears the selected competition.
     */
    private void clear() {
        if (competitions.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "There are no competitions to clear.");
            return;
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Select Competition to Clear");
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
                Competition selectedCompetition = competitions.stream().filter(c -> c.getName().equals(selectedCompetitionName)).findFirst().orElse(null);
                if (selectedCompetition != null) {
                    List<Animal> animalList = selectedCompetition.getAnimals();
                    if (animalList.isEmpty()) {
                        JOptionPane.showMessageDialog(parentFrame, "There are no animals in this competition.");
                        return;
                    }
                    JFrame removeFrame = new JFrame("Select Animal to Remove");
                    removeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    removeFrame.setSize(300, 150);

                    JComboBox<Animal> animalComboBox = new JComboBox<>(animalList.toArray(new Animal[0]));
                    animalComboBox.setFont(new Font("Arial", Font.ITALIC, 15));

                    animalComboBox.setRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                            if (value instanceof Animal) {
                                setText(((Animal) value).getAnimaleName());
                            }
                            return this;
                        }
                    });

                    JButton removeAnimalButton = new JButton("Remove");
                    JButton goBackButtonInner = new JButton("Go Back");
                    removeAnimalButton.setToolTipText("Click to remove selected animal");

                    JPanel removePanel = new JPanel();
                    removePanel.setLayout(new BorderLayout(10, 10));
                    removePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    removePanel.add(animalComboBox, BorderLayout.NORTH);
                    removePanel.add(goBackButtonInner, BorderLayout.SOUTH);
                    removePanel.add(removeAnimalButton, BorderLayout.CENTER);
                    removeFrame.add(removePanel);
                    removeFrame.setLocationRelativeTo(parentFrame);
                    removeFrame.setVisible(true);

                    removeAnimalButton.addActionListener(e2 -> {
                        Animal selectedAnimal = (Animal) animalComboBox.getSelectedItem();
                        boolean removed = animalList.remove(selectedAnimal);
                        if (removed) {
                            JOptionPane.showMessageDialog(parentFrame, selectedAnimal.getAnimaleName() + " removed from " + selectedCompetitionName);
                        } else {
                            JOptionPane.showMessageDialog(parentFrame, "Failed to remove " + selectedAnimal.getAnimaleName());
                        }
                        removeFrame.dispose();
                        parentFrame.getImagePanel().setCompetitions(competitions);
                    });

                    goBackButtonInner.addActionListener(e1 -> removeFrame.dispose());
                }
            }
        });
    }

    /**
     * Displays information about the animals in a competition.
     */
    private void info() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (competitions.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "There are no competitions to select.");
            return;
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Select Competition for Animal Info");
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
            String selectedCompetitionName = (String) competitionTypeComboBox.getSelectedItem();
            if (selectedCompetitionName != null) {
                Competition selectedCompetition = competitions.stream().filter(c -> c.getName().equals(selectedCompetitionName)).findFirst().orElse(null);
                if (selectedCompetition != null) {
                    List<Animal> animalList = selectedCompetition.getAnimals();
                    if (animalList.isEmpty()) {
                        JOptionPane.showMessageDialog(parentFrame, "There are no animals in this competition.");
                        return;
                    }
                    frame.dispose();
                    JTable j = new JTable();
                    JFrame f = new JFrame();

                    // Frame Title
                    f.setTitle("Info");

                    Object[][] data = new Object[animalList.size()][7];
                    for (int i = 0; i < animalList.size(); i++) {
                        Animal animal = animalList.get(i);
                        data[i][0] = animal.getAnimaleName();
                        data[i][1] = animal.animalCategory();
                        data[i][2] = animal.animalType();
                        data[i][3] = animal.getSpeed();
                        data[i][4] = animal.getCurrentEnergy();
                        data[i][5] = animal.getTotalDistance();
                        data[i][6] = animal.getTotalConsumption();
                    }

                    String[] columnNames = { "Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption" };

                    j = new JTable(data, columnNames);
                    j.setBounds(30, 40, 200, 300);

                    JScrollPane sp = new JScrollPane(j);
                    f.add(sp);
                    f.setSize(500, 200);
                    f.setVisible(true);
                }
            }
        });
    }

    /**
     * Gets all the competitions.
     *
     * @return The list of all competitions.
     */
    public List<Competition> getAllCompetitions() {
        return competitions;
    }
}
