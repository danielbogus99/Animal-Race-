package competitions;

import Graphics.ImagePanel;
import animals.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {
    private AtomicBoolean startFlag; // Special start flag to begin the race
    private Scores scores; // Scores to record the results
    private int numberOfGroups; // Number of groups in the tournament
    private ImagePanel imagePanel; // Reference to the ImagePanel for updates
    private Animal[][] animals;
    private String raceName;// Reference to the animal groups
    private int neededDistance;

    // Constructor that takes the animal groups, additional info, and the ImagePanel
    public CourierTournament(Animal[][] animalGroups, int neededDistance,String raceName) {
        super(animalGroups, neededDistance);
        this.raceName = raceName;
        setup(animalGroups, neededDistance); // Call setup with the provided parameters
    }

    @Override
    protected void setup(Animal[][] animalGroups, Object additionalInfo) {
        this.startFlag = new AtomicBoolean(false); // Initialize the start flag to false
        this.scores = new Scores(); // Create a new Scores object
        this.numberOfGroups = animalGroups.length; // Set the number of groups
        this.animals = animalGroups; // Store the animal groups

        for (int groupIndex = 0; groupIndex < animalGroups.length; groupIndex++) {
            Animal[] group = animalGroups[groupIndex];
            int n = group.length; // Number of animals in the group
            List<AtomicBoolean> flags = new ArrayList<>(n);

            // Initialize all flags to false
            for (int i = 0; i < n; i++) {
                flags.add(new AtomicBoolean(false));
            }

            // Create and start AnimalThread for each animal in the group
            for (int i = 0; i < n; i++) {
                AtomicBoolean currentStartFlag = (i == 0) ? startFlag : flags.get(i - 1);
                AtomicBoolean currentFinishFlag = flags.get(i);
                double neededDistance = 10000 / n; // Calculate needed distance

                // Pass the imagePanel reference to the AnimalThread
                AnimalThread animalThread = new AnimalThread(group[i], neededDistance, currentStartFlag, currentFinishFlag);
                new Thread(animalThread).start();
            }

            // Create and start a Referee for the group
            Referee referee = new Referee("Group " + (groupIndex + 1), scores, flags.get(n - 1));
            new Thread(referee).start();
        }

        // Store the startFlag, scores, and number of groups in the tournament thread
        this.tournamentThread = new TournamentThread(scores, startFlag, numberOfGroups,raceName);
        new Thread(this.tournamentThread).start(); // Start the tournament thread
    }

    // Start the race by setting the start flag to true
    public void startRace() {
        synchronized (startFlag) {
            startFlag.set(true);
            startFlag.notifyAll(); // Notify all threads that the race has started
        }
    }

    // Method to retrieve the animal teams
    public Animal[][] getAnimalTeams() {
        return animals;
    }
}
