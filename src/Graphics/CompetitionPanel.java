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

    public CompetitionPanel(CompetitionFrame parentFrame, ImagePanel imagePanel) {
        this.parentFrame = parentFrame;
        setLayout(new GridLayout(1, 0));
        this.imagePanel = imagePanel;
        this.regularRace = new ArrayList<>();
        this.courierRace = new ArrayList<>();

        JButton addCompetitionButton = new JButton("Add Competition");
        JButton startCompetitionButton = new JButton("Start Competition");
        JButton exitButton = new JButton("Exit");

        // Initialize the timer with a faster delay for smoother animation
        timer = new Timer(200, e -> updateCompetition()); // ~60 FPS

        add(addCompetitionButton);
        add(startCompetitionButton);
        add(exitButton);

        exitButton.addActionListener(e -> System.exit(0));
        addCompetitionButton.addActionListener(e -> addCompetition());
        startCompetitionButton.addActionListener(e -> startCompetition());

        // Enable double buffering to prevent flickering
        setDoubleBuffered(true);
    }

    private void updateCompetition() {
        List<Animal> allAnimals = new ArrayList<>();

        if (currentTournament != null) {
            allAnimals.addAll(collectAnimals(currentTournament.getAnimalTeams()));
        }

        if (currentCourier != null) {
            allAnimals.addAll(collectAnimals(currentCourier.getAnimalTeams()));
        }

        if (allAnimals.isEmpty()) {
            // If there are no animals left, stop the timer and clear the panel
            timer.stop();
            imagePanel.setAnimals(null); // Clear the animals from the panel
        } else {
            imagePanel.setAnimals(allAnimals);
        }

        imagePanel.repaint();
    }


    private List<Animal> collectAnimals(Animal[][] animalTeams)
    {
        List<Animal> allAnimals = new ArrayList<>();
        for (Animal[] team : animalTeams) {
            for (Animal animal : team) {
                allAnimals.add(animal);
            }
        }
        return allAnimals;
    }

    private void startCompetition() {
        List<String> raceNames = collectRaceNames();
        if (raceNames.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "No races are available to start.");
            return;
        }

        String selectedRaceName = (String) JOptionPane.showInputDialog(
                parentFrame,
                "Select a race to start:",
                "Start Competition",
                JOptionPane.PLAIN_MESSAGE,
                null,
                raceNames.toArray(),
                raceNames.get(0));

        if (selectedRaceName != null) {
            if (selectedRaceName.endsWith("(Regular)")) {
                startRegularRace(selectedRaceName.replace(" (Regular)", ""));
            } else if (selectedRaceName.endsWith("(Courier)")) {
                startCourierRace(selectedRaceName.replace(" (Courier)", ""));
            }
        }
    }

    private List<String> collectRaceNames() {
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
        return raceNames;
    }

    private void startRegularRace(String raceName) {
        RegularRace selectedRace = regularRace.stream().filter(race -> race.getRaceName().equals(raceName)).findFirst().orElse(null);

        if (selectedRace != null) {
            Animal[][] animalTeams = selectedRace.toAnimalTeams();
            currentTournament = new RegularTournament(animalTeams, null,imagePanel);
            new Thread(() -> currentTournament.startRace()).start();

            if (!timer.isRunning()) {
                timer.start();
            }
        }
    }

    private void startCourierRace(String raceName)
    {
        CourierRace selectedRace = courierRace.stream()
                .filter(race -> race.getName().equals(raceName))
                .findFirst()
                .orElse(null);

        if (selectedRace != null) {
            Animal[][] animalTeams = selectedRace.getAnimalGroups();
            currentCourier = new CourierTournament(animalTeams, null, imagePanel); // Pass the ImagePanel to the tournament
            new Thread(() -> currentCourier.startRace()).start(); // Start the courier race in a new thread

            if (!timer.isRunning()) {
                timer.start(); // Start the update timer if not already running
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
