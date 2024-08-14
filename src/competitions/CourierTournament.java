package competitions;

import Graphics.ImagePanel;
import animals.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a Courier Tournament, which is a type of tournament where animals participate in races.
 * The tournament manages multiple groups of animals and controls the race using threads.
 */
public class CourierTournament extends Tournament {
    private AtomicBoolean startFlag; // Special start flag to begin the race
    private Scores scores; // Scores to record the results
    private int numberOfGroups; // Number of groups in the tournament
    private ImagePanel imagePanel; // Reference to the ImagePanel for updates
    private Animal[][] animals; // Reference to the animal groups
    private String raceName; // Name of the race
    private String compositeKey; // Composite key for the race
    private Map<String, Integer> occupiedPaths; // Map to track occupied paths
    private int neededDistances; // Distance that needs to be covered in the race

    /**
     * Constructs a CourierTournament with the specified parameters.
     *
     * @param animalGroups    The groups of animals participating in the tournament.
     * @param neededDistance  The distance that needs to be covered by the animals.
     * @param raceName        The name of the race.
     * @param occupiedPaths   A map to track occupied paths.
     * @param compositeKey    A composite key for identifying the race.
     */
    public CourierTournament(Animal[][] animalGroups, int neededDistance, String raceName, Map<String, Integer> occupiedPaths, String compositeKey) {
        super(animalGroups, neededDistance);
        this.startFlag = new AtomicBoolean(false);
        this.neededDistances = neededDistance;
        this.occupiedPaths = occupiedPaths;
        this.compositeKey = compositeKey;
        this.raceName = raceName;
        setup(animalGroups, neededDistance); // Call setup with the provided parameters
    }

    /**
     * Sets up the tournament by initializing threads for each animal and referee.
     *
     * @param animalGroups   The groups of animals participating in the tournament.
     * @param additionalInfo Additional information needed for the setup.
     */
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
                double neededDistance = this.neededDistances / n; // Calculate needed distance

                // Pass the imagePanel reference to the AnimalThread
                AnimalThread animalThread = new AnimalThread(group[i], neededDistance, currentStartFlag, currentFinishFlag);
                new Thread(animalThread).start();
            }

            // Create and start a Referee for the group
            Referee referee = new Referee("Group " + (groupIndex + 1), scores, flags.get(n - 1));
            new Thread(referee).start();
        }

        // Store the startFlag, scores, and number of groups in the tournament thread
        this.tournamentThread = new TournamentThread(scores, startFlag, numberOfGroups, raceName, occupiedPaths, compositeKey);
        new Thread(this.tournamentThread).start(); // Start the tournament thread
    }

    /**
     * Starts the race by setting the start flag to true and notifying all threads.
     */
    public void startRace() {
        synchronized (startFlag) {
            startFlag.set(true);
            startFlag.notifyAll(); // Notify all threads that the race has started
        }
    }
}
