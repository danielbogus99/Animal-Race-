package Graphics;

import animals.*;
import competitions.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a panel for managing competitions and animals within a competition.
 * The panel allows the user to add and start competitions, as well as manage the energy of animals.
 */
public class CompetitionPanel extends JPanel {

    private Animal selectedAnimal;
    private CompetitionFrame parentFrame;
    private ImagePanel imagePanel;
    private List<RegularRace> regularRace;
    private List<CourierRace> courierRace;
    private List<RegularRace> startedRegularRaces; // Tracks started regular races
    private List<CourierRace> startedCourierRaces; // Tracks started courier races
    private List<Animal> activeRegularAnimals; // Tracks active animals in regular races
    private List<Animal> activeCourierAnimals; // Tracks active animals in courier races
    private Map<String, Integer> occupiedPaths; // Map to track occupied paths
    private Timer timer;
    private RegularTournament currentTournament;
    private CourierTournament currentCourier;

    /**
     * Constructs a new CompetitionPanel with the specified parent frame and image panel.
     *
     * @param parentFrame The parent frame of the competition panel.
     * @param imagePanel  The image panel used for displaying the animals.
     */
    public CompetitionPanel(CompetitionFrame parentFrame, ImagePanel imagePanel) {
        this.parentFrame = parentFrame;
        setLayout(new GridLayout(1, 0));
        this.imagePanel = imagePanel;
        this.regularRace = new ArrayList<>();
        this.courierRace = new ArrayList<>();
        this.startedRegularRaces = new ArrayList<>();
        this.startedCourierRaces = new ArrayList<>();
        this.activeRegularAnimals = new ArrayList<>();
        this.activeCourierAnimals = new ArrayList<>();
        this.occupiedPaths = new HashMap<>(); // Initialize the map

        JButton addCompetitionButton = new JButton("Add Competition");
        JButton startCompetitionButton = new JButton("Start Competition");
        JButton eatButton = new JButton("Eat");
        JButton exitButton = new JButton("Exit");

        // Initialize the timer with a faster delay for smoother animation
        timer = new Timer(200, e -> updateCompetition()); // ~60 FPS

        add(addCompetitionButton);
        add(startCompetitionButton);
        add(eatButton);
        add(exitButton);

        exitButton.addActionListener(e -> System.exit(0));
        addCompetitionButton.addActionListener(e -> addCompetition());
        startCompetitionButton.addActionListener(e -> startCompetition());
        eatButton.addActionListener(e -> eatAnimals());

        // Enable double buffering to prevent flickering
        setDoubleBuffered(true);
    }

    /**
     * Updates the competition panel by collecting all active animals and repainting the image panel.
     */
    private void updateCompetition() {
        List<Animal> allAnimals = new ArrayList<>();

        // Collect all active animals
        allAnimals.addAll(activeRegularAnimals);
        allAnimals.addAll(activeCourierAnimals);

        if (allAnimals.isEmpty()) {
            // If there are no animals left, stop the timer and clear the panel
            timer.stop();
            imagePanel.setAnimals(null); // Clear the animals from the panel
        } else {
            imagePanel.setAnimals(allAnimals); // Paint all animals together
        }

        imagePanel.repaint();
    }

    /**
     * Collects all animals from the provided animal teams.
     *
     * @param animalTeams The animal teams to collect from.
     * @return A list of all animals.
     */
    private List<Animal> collectAnimals(Animal[][] animalTeams) {
        List<Animal> allAnimals = new ArrayList<>();
        for (Animal[] team : animalTeams) {
            for (Animal animal : team) {
                allAnimals.add(animal);
            }
        }
        return allAnimals;
    }

    /**
     * Starts a competition by prompting the user to select a race and then starting it.
     */
    private void startCompetition() {
        List<String> raceNames = collectRaceNames(false); // Only show races that haven't started
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

    /**
     * Collects the names of all races, optionally including started races.
     *
     * @param includeStarted Whether to include started races.
     * @return A list of race names.
     */
    private List<String> collectRaceNames(boolean includeStarted) {
        List<String> raceNames = new ArrayList<>();
        if (regularRace != null) {
            for (RegularRace race : regularRace) {
                raceNames.add(race.getRaceName() + " (Regular)");
            }
        }
        if (includeStarted && startedRegularRaces != null) {
            for (RegularRace race : startedRegularRaces) {
                raceNames.add(race.getRaceName() + " (Regular)");
            }
        }
        if (courierRace != null) {
            for (CourierRace race : courierRace) {
                raceNames.add(race.getName() + " (Courier)");
            }
        }
        if (includeStarted && startedCourierRaces != null) {
            for (CourierRace race : startedCourierRaces) {
                raceNames.add(race.getName() + " (Courier)");
            }
        }
        return raceNames;
    }

    /**
     * Creates a composite key based on race type and path.
     *
     * @param raceType The type of the race (e.g., "Water", "Air").
     * @param path     The path of the race.
     * @return A composite key string.
     */
    private String createCompositeKey(String raceType, int path) {
        return raceType + "-" + path;
    }

    /**
     * Starts a regular race based on the selected race name.
     *
     * @param raceName The name of the race to start.
     */
    private void startRegularRace(String raceName) {
        RegularRace selectedRace = regularRace.stream()
                .filter(race -> race.getRaceName().equals(raceName))
                .findFirst()
                .orElse(null);

        if (selectedRace != null) {
            // Check if all animals are not moving
            List<Animal> animals = collectAnimals(selectedRace.toAnimalTeams());
            if (!allAnimalsNotMoving(animals)) {
                JOptionPane.showMessageDialog(parentFrame, "There is an animal in the race that is currently in an active race.");
                return;
            }

            // Determine the valid distance range based on race type
            String raceType = selectedRace.getRaceType();
            String distanceHint = "";
            if (raceType.equals("Water")) {
                distanceHint = " (100-800 meters)";
            } else if (raceType.equals("Air")) {
                distanceHint = " (100-1000 meters)";
            } else if (raceType.equals("Terrestrial")) {
                distanceHint = " (any positive number)";
            }

            // Prompt the user for the required distance with the hint
            String distanceInput = JOptionPane.showInputDialog(
                    parentFrame,
                    "Enter the required distance for the " + raceType + " race" + distanceHint + ":",
                    "Distance Input",
                    JOptionPane.PLAIN_MESSAGE);

            if (distanceInput != null) {
                try {
                    int distance = Integer.parseInt(distanceInput);

                    // Validate the distance based on the type of race
                    if ((raceType.equals("Water") && (distance < 100 || distance > 800)) ||
                            (raceType.equals("Air") && (distance < 100 || distance > 1000)) ||
                            (raceType.equals("Terrestrial") && distance <= 0)) {
                        JOptionPane.showMessageDialog(parentFrame, "Invalid distance for " + raceType + " race. Please enter a valid distance.");
                        return;
                    }

                    // Create the composite key
                    String compositeKey = createCompositeKey(raceType, selectedRace.getRacePath());

                    // Check if the selected path is already occupied
                    if (occupiedPaths.containsKey(compositeKey)) {
                        JOptionPane.showMessageDialog(parentFrame, "The selected path is already occupied by another race. Please choose a different path.");
                        return;
                    }

                    // Mark the path as occupied
                    occupiedPaths.put(compositeKey, selectedRace.getRacePath());

                    setAnimalsToMoving(animals);
                    activeRegularAnimals.addAll(animals); // Add these animals to the active list
                    RegularTournament tournament = new RegularTournament(selectedRace.toAnimalTeams(), distance, raceName, occupiedPaths, compositeKey); // Create a new tournament for each race
                    new Thread(() -> {
                        tournament.startRace();

                    }).start(); // Start each race in a separate thread

                    startedRegularRaces.add(selectedRace); // Add to the list of started races
                    regularRace.remove(selectedRace); // Remove the selected race from the start list

                    if (!timer.isRunning()) {
                        timer.start(); // Start the timer if it's not running (optional)
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid distance entered. Please enter a number.");
                }
            }
        }
    }

    /**
     * Checks if all animals are stationary (not moving).
     *
     * @param animals The list of animals to check.
     * @return True if all animals are stationary, false otherwise.
     */
    private boolean allAnimalsNotMoving(List<Animal> animals) {
        for (Animal animal : animals) {
            if (animal.isMoving()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets all animals to a moving state.
     *
     * @param animals The list of animals to set as moving.
     */
    private void setAnimalsToMoving(List<Animal> animals) {
        for (Animal animal : animals) {
            animal.setMoving();
        }
    }

    /**
     * Starts a courier race based on the selected race name.
     *
     * @param raceName The name of the race to start.
     */
    private void startCourierRace(String raceName) {
        CourierRace selectedRace = courierRace.stream()
                .filter(race -> race.getName().equals(raceName))
                .findFirst()
                .orElse(null);

        if (selectedRace != null) {
            // Check if all animals are not moving
            List<Animal> animals = collectAnimals(selectedRace.getAnimalGroups());
            if (!allAnimalsNotMoving(animals)) {
                JOptionPane.showMessageDialog(parentFrame, "All animals must be stationary before starting the race.");
                return;
            }

            // Determine the valid distance range based on race type
            String raceType = selectedRace.getType();
            String distanceHint = "";
            if (raceType.equals("Water")) {
                distanceHint = " (100-800 meters)";
            } else if (raceType.equals("Air")) {
                distanceHint = " (100-1000 meters)";
            } else if (raceType.equals("Terrestrial")) {
                distanceHint = " (any positive number)";
            }

            // Prompt the user for the required distance with the hint
            String distanceInput = JOptionPane.showInputDialog(
                    parentFrame,
                    "Enter the required distance for the " + raceType + " race" + distanceHint + ":",
                    "Distance Input",
                    JOptionPane.PLAIN_MESSAGE);

            if (distanceInput != null) {
                try {
                    int distance = Integer.parseInt(distanceInput);

                    // Validate the distance based on the type of race
                    if ((raceType.equals("Water") && (distance < 100 || distance > 800)) ||
                            (raceType.equals("Air") && (distance < 100 || distance > 1000)) ||
                            (raceType.equals("Terrestrial") && distance <= 0)) {
                        JOptionPane.showMessageDialog(parentFrame, "Invalid distance for " + raceType + " race. Please enter a valid distance.");
                        return;
                    }

                    // Create the composite key
                    String compositeKey = createCompositeKey(raceType, selectedRace.getRacePath());

                    // Check if the selected path is already occupied
                    if (occupiedPaths.containsKey(compositeKey)) {
                        JOptionPane.showMessageDialog(parentFrame, "The path of the race is already taken by another race. Please wait for the race to end.");
                        return;
                    }

                    // Mark the path as occupied
                    occupiedPaths.put(compositeKey, selectedRace.getRacePath());

                    setAnimalsToMoving(animals);
                    activeCourierAnimals.addAll(animals); // Add these animals to the active list
                    currentCourier = new CourierTournament(selectedRace.getAnimalGroups(), distance, raceName, occupiedPaths, compositeKey); // Pass the distance
                    new Thread(() -> {
                        currentCourier.startRace();

                    }).start();

                    startedCourierRaces.add(selectedRace); // Add to the list of started races
                    courierRace.remove(selectedRace); // Remove the selected race from the start list

                    if (!timer.isRunning()) {
                        timer.start();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid distance entered. Please enter a number.");
                }
            }
        }
    }

    /**
     * Allows the user to add energy to a selected animal in a race.
     */
    private void eatAnimals() {
        List<String> raceNames = collectRaceNames(true); // Show all races including started ones
        if (raceNames.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "No races are available.");
            return;
        }

        String selectedRaceName = (String) JOptionPane.showInputDialog(
                parentFrame,
                "Select a race to view animals:",
                "Eat",
                JOptionPane.PLAIN_MESSAGE,
                null,
                raceNames.toArray(),
                raceNames.get(0));

        if (selectedRaceName != null) {
            List<Animal> animals;
            if (selectedRaceName.endsWith("(Regular)")) {
                animals = getAnimalsFromRegularRace(selectedRaceName.replace(" (Regular)", ""));
            } else {
                animals = getAnimalsFromCourierRace(selectedRaceName.replace(" (Courier)", ""));
            }

            if (animals != null && !animals.isEmpty()) {
                String[] animalNames = animals.stream().map(Animal::getAnimalName).toArray(String[]::new);
                String selectedAnimalName = (String) JOptionPane.showInputDialog(
                        parentFrame,
                        "Select an animal:",
                        "Select Animal",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        animalNames,
                        animalNames[0]);

                if (selectedAnimalName != null) {
                    Animal selectedAnimal = animals.stream()
                            .filter(animal -> animal.getAnimalName().equals(selectedAnimalName))
                            .findFirst()
                            .orElse(null);

                    if (selectedAnimal != null) {
                        String energyInput = JOptionPane.showInputDialog(
                                parentFrame,
                                "Enter the amount of energy to add:",
                                "Energy Input",
                                JOptionPane.PLAIN_MESSAGE);

                        if (energyInput != null) {
                            try {
                                int energyAmount = Integer.parseInt(energyInput);
                                selectedAnimal.eat(energyAmount); // Add the specified energy to the selected animal
                                System.out.println(selectedAnimal.getAnimalName() + " energy increased by " + energyAmount + ".");
                                JOptionPane.showMessageDialog(parentFrame, selectedAnimal.getAnimalName() + " energy increased by " + energyAmount + ".");
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(parentFrame, "Invalid energy amount entered. Please enter a number.");
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(parentFrame, "No animals found in the selected race.");
            }
        }
    }

    /**
     * Retrieves the list of animals from a regular race.
     *
     * @param raceName The name of the race.
     * @return A list of animals in the race.
     */
    private List<Animal> getAnimalsFromRegularRace(String raceName) {
        RegularRace selectedRace = regularRace.stream()
                .filter(race -> race.getRaceName().equals(raceName))
                .findFirst()
                .orElse(null);

        if (selectedRace == null) {
            // If not found in regularRace, check startedRegularRaces
            selectedRace = startedRegularRaces.stream()
                    .filter(race -> race.getRaceName().equals(raceName))
                    .findFirst()
                    .orElse(null);
        }

        if (selectedRace != null) {
            return collectAnimals(selectedRace.toAnimalTeams());
        } else {
            return new ArrayList<>(); // Return an empty list if no race is found
        }
    }

    /**
     * Retrieves the list of animals from a courier race.
     *
     * @param raceName The name of the race.
     * @return A list of animals in the race.
     */
    private List<Animal> getAnimalsFromCourierRace(String raceName) {
        CourierRace selectedRace = courierRace.stream()
                .filter(race -> race.getName().equals(raceName))
                .findFirst()
                .orElse(null);

        if (selectedRace == null) {
            // If not found in courierRace, check startedCourierRaces
            selectedRace = startedCourierRaces.stream()
                    .filter(race -> race.getName().equals(raceName))
                    .findFirst()
                    .orElse(null);
        }

        return selectedRace != null ? collectAnimals(selectedRace.getAnimalGroups()) : new ArrayList<>();
    }

    /**
     * Opens the AddCompetitionDialog to allow the user to add a new competition.
     */
    private void addCompetition() {
        AddCompetitionDialog addCompetitionDialog = new AddCompetitionDialog(parentFrame);
        addCompetitionDialog.setVisible(true);
        regularRace = addCompetitionDialog.getAllRegularRaces();
        courierRace = addCompetitionDialog.getAllCourierRaces();
    }
}
