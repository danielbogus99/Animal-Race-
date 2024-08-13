package competitions;

import Graphics.ImagePanel;
import animals.Animal;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a regular tournament where animals participate in races.
 * The tournament manages multiple groups of animals and controls the race using threads.
 */
public class RegularTournament extends Tournament {
    private AtomicBoolean startFlag; // Flag to signal the start of the race
    private Scores scores; // Object to record the scores
    private int numberOfGroups; // Number of groups in the tournament
    private Animal[][] animals; // Array of animal groups participating in the tournament
    private String raceName; // Name of the race
    private String compositeKey; // Composite key for identifying the race
    private Map<String, Integer> occupiedPaths; // Map to track occupied paths
    private int neededDistance; // Distance that needs to be covered in the race

    /**
     * Constructs a RegularTournament with the specified parameters.
     *
     * @param animalGroups   The groups of animals participating in the tournament.
     * @param neededDistance The distance that needs to be covered by the animals.
     * @param raceName       The name of the race.
     * @param occupiedPaths  A map to track occupied paths.
     * @param compositeKey   A composite key for identifying the race.
     */
    public RegularTournament(Animal[][] animalGroups, int neededDistance, String raceName, Map<String, Integer> occupiedPaths, String compositeKey) {
        super(animalGroups, neededDistance);
        this.startFlag = new AtomicBoolean(false);
        this.neededDistance = neededDistance;
        this.raceName = raceName;
        this.occupiedPaths = occupiedPaths;
        this.compositeKey = compositeKey;
        setup(animalGroups, neededDistance);
    }

    /**
     * Sets up the tournament by initializing threads for each animal and referee.
     *
     * @param animalGroups   The groups of animals participating in the tournament.
     * @param additionalInfo Additional information needed for the setup.
     */
    @Override
    protected void setup(Animal[][] animalGroups, Object additionalInfo) {
        this.scores = new Scores();
        this.numberOfGroups = animalGroups.length;
        this.animals = animalGroups;

        for (Animal[] group : animalGroups) {
            for (Animal animal : group) {
                AtomicBoolean finishFlag = new AtomicBoolean(false);

                // Create and start a thread for each animal
                AnimalThread animalThread = new AnimalThread(animal, neededDistance, startFlag, finishFlag);
                new Thread(animalThread).start();

                // Create and start a referee thread to monitor each animal
                Referee referee = new Referee(animal.getAnimalName(), scores, finishFlag);
                new Thread(referee).start();
            }
        }

        // Start the main tournament thread with the correct startFlag
        TournamentThread tournamentThread = new TournamentThread(scores, startFlag, numberOfGroups, raceName, occupiedPaths, compositeKey);
        new Thread(tournamentThread).start();
    }

    /**
     * Starts the race by setting the start flag to true and notifying all threads.
     */
    public void startRace() {
        synchronized (startFlag) {
            startFlag.set(true);
            startFlag.notifyAll(); // Notify all waiting threads that the race has started
        }
    }
}
