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
        JButton exitButton = new JButton("Exit");

        // Initialize the timer
        timer = new Timer(50, e -> updateCompetition());

        add(addCompetitionButton);
        add(startCompetitionButton);
        add(exitButton);

        exitButton.addActionListener(e -> System.exit(0));
        addCompetitionButton.addActionListener(e -> addCompetition());
        startCompetitionButton.addActionListener(e -> startCompetition());
    }

    private void updateCompetition() {
        if (currentTournament != null) {
            // Update each team of animals in the current tournament
            Animal[][] animalTeams = currentTournament.getAnimalTeams();
            imagePanel.repaint(); // Repaint the panel to reflect updated positions
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
        RegularRace selectedRace = regularRace.stream().filter(race -> race.getRaceName().equals(raceName)).findFirst().orElse(null);

        if (selectedRace != null) {
            Animal[][] animalTeams = selectedRace.toAnimalTeams();
            currentTournament = new RegularTournament(animalTeams, null);
            new Thread(() -> currentTournament.startRace()).start(); // Start the regular tournament

            // Start the timer if not already running
            if (!timer.isRunning()) {
                timer.start();
            }
        }
    }

    // Placeholder method to start a courier race
    private void startCourierRace(String raceName) {
        CourierRace selectedRace = courierRace.stream().filter(race -> race.getName().equals(raceName)).findFirst().orElse(null);

        if (selectedRace != null) {
            Animal[][] animals = selectedRace.getAnimalGroups();
            currentCourier = new CourierTournament(animals, null);
            new Thread(() -> currentCourier.startRace()).start(); // Start the courier tournament

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
}
