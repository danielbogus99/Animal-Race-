package Graphics;

import javax.swing.Timer;
import animals.*;
import competitions.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a panel for managing competitions and animals within a competition.
 */
public class CompetitionPanel extends JPanel {

    private Animal selectedAnimal;
    private CompetitionFrame parentFrame;
    private ImagePanel imagePanel;
    private List<RegularRace> regularRace;
    private List<CourierRace> courierRace;
    private Timer timer;
    private RegularTournament currentTournament;
    private CourierTournament currentCourier;
    private Animal[][] regularTournament;
    private Animal[][] courierTournament;

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
        this.regularRace = new ArrayList<>();
        this.courierRace = new ArrayList<>();

        JButton addCompetitionButton = new JButton("Add Competition");
        JButton startCompetitionButton = new JButton("Start Competition");
        JButton eatButton = new JButton("Eat");
        JButton exitButton = new JButton("Exit");

        // Initialize the timer
        timer = new Timer(100, e -> updateCompetition());

        add(addCompetitionButton);
        add(startCompetitionButton);
        add(eatButton);
        add(exitButton);

        exitButton.addActionListener(e -> System.exit(0));
        addCompetitionButton.addActionListener(e -> addCompetition());
        startCompetitionButton.addActionListener(e -> startCompetition());
        timer.start();
    }

    private void updateCompetition() {
        if (currentTournament != null && currentTournament.getStartFlag())
        {

            // Update each team of animals in the current tournament
            Animal[][] animalTeams = currentTournament.getAnimalTeams();

            imagePanel.setAnimalTeams(animalTeams);
            for (Animal[] team : animalTeams) {
                for (Animal animal : team) {
                    if (!animal.isOutOfEnergy()) {
                        animal.move(); // Move only if not out of energy
                        checkBoundsAndChangeDirection(animal); // Check and change direction if needed
                        animal.decreaseEnergy(); // Decrease energy after moving
                    }
                }
            }
            imagePanel.repaint(); // Repaint the panel to reflect updated positions
        }
    }

    private void checkBoundsAndChangeDirection(Animal animal) {
        int x = animal.getLocation().getX();
        int y = animal.getLocation().getY();
        int backgroundWidth = imagePanel.getWidth2();
        int backgroundHeight = imagePanel.getHeight2();

        if (animal instanceof AirAnimal) {
            if (x > backgroundWidth - 250) {
                animal.Stop(); // Ensure `stop()` method is used instead of `Stop()`
                System.out.println("AirAnimal stopped at boundary.");
            }
        } else if (animal instanceof ITerrestrailAnimal || animal instanceof TerrestrialAnimals) {
            if (x > backgroundWidth - 260 && y > backgroundHeight - 155) {
                animal.setOrientation(Animal.Orientation.WEST);
            } else if (x > backgroundWidth - 260) {
                animal.setOrientation(Animal.Orientation.SOUTH);
            } else if (x < 15 && y > backgroundHeight - 155)
            {
                animal.setOrientation(Animal.Orientation.NORTH);
            }
            else if (x == 0 && y == 0)
            {
                animal.setOrientation(Animal.Orientation.EAST);
            }
        } else if (animal instanceof WaterAnimal) {
            if (x > backgroundWidth - 345) {
                animal.Stop(); // Ensure `stop()` method is used instead of `Stop()`
                System.out.println("WaterAnimal stopped at boundary.");
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
                startRegularRace(selectedRaceName.replace(" (Regular)", ""));
            } else if (selectedRaceName.endsWith("(Courier)")) {
                startCourierRace(selectedRaceName.replace(" (Courier)", ""));
            }
        }
    }

    // Method to start a regular race
    private void startRegularRace(String raceName) {
        RegularRace selectedRace = regularRace.stream()
                .filter(race -> race.getRaceName().equals(raceName))
                .findFirst()
                .orElse(null);

        if (selectedRace != null) {
            Animal[][] animalTeams = selectedRace.toAnimalTeams();
            currentTournament = new RegularTournament(animalTeams, null);

            // Start the tournament race
            currentTournament.startRace();

            // Start the timer if not already running
            if (!timer.isRunning()) {
                timer.start();
            }
        }
    }

    // Placeholder method to start a courier race
    private void startCourierRace(String raceName) {
        CourierRace selectedRace = courierRace.stream()
                .filter(race -> race.getName().equals(raceName))
                .findFirst()
                .orElse(null);

        if (selectedRace != null) {
            Animal[][] animals = selectedRace.getAnimalGroups();
            currentCourier = new CourierTournament(animals, null);
            currentCourier.startRace();

            // Start the timer if not already running
            if (!timer.isRunning()) {
                timer.start();
            }
        }
    }

    private void addCompetition() {
        AddCompetitionDialog addCompetitionDialog = new AddCompetitionDialog(parentFrame);
        addCompetitionDialog.setVisible(true);
        regularRace = addCompetitionDialog.getAllRegularRaces();
        courierRace = addCompetitionDialog.getAllCourierRaces();
    }

//    private void feedAnimals() {
//        // Implement logic to feed all animals in the current tournament
//        if (currentTournament != null) {
//            Animal[][] animalTeams = currentTournament.getAnimalTeams();
//            for (Animal[] team : animalTeams) {
//                for (Animal animal : team) {
//                    animal.increaseEnergy(10); // Example method to increase energy
//                }
//            }
//            System.out.println("All animals have been fed.");
//        } else {
//            JOptionPane.showMessageDialog(parentFrame, "No active tournament to feed animals.");
//        }
//    }
}
