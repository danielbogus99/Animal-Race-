package competitions;

import Graphics.ImagePanel;
import animals.Animal;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegularTournament extends Tournament {
    private AtomicBoolean startFlag;
    private Scores scores;
    private int numberOfGroups;
    private Animal[][] animals;
    private ImagePanel imagePanel; // Reference to ImagePanel

    public RegularTournament(Animal[][] animalGroups, Object additionalInfo, ImagePanel imagePanel) {
        super(animalGroups, additionalInfo);
        this.startFlag = new AtomicBoolean(false);
        this.imagePanel = imagePanel; // Initialize ImagePanel reference
        setup(animalGroups, additionalInfo);
    }

    @Override
    protected void setup(Animal[][] animalGroups, Object additionalInfo) {
        this.scores = new Scores();
        this.numberOfGroups = animalGroups.length;
        this.animals = animalGroups;

        for (Animal[] group : animalGroups) {
            for (Animal animal : group) {
                AtomicBoolean finishFlag = new AtomicBoolean(false);
                // Create an AnimalThread for each animal and pass the ImagePanel reference

                AnimalThread animalThread = new AnimalThread(animal, 10000, startFlag, finishFlag, imagePanel);
                new Thread(animalThread).start();

                // Create a Referee to monitor each animal
                Referee referee = new Referee(animal.getAnimalName(), scores, finishFlag);
                new Thread(referee).start();
            }
        }

        // Start the main tournament thread
        this.tournamentThread = new TournamentThread(scores, startFlag, numberOfGroups);
        new Thread(this.tournamentThread).start();
    }

    public void startRace() {
        synchronized (startFlag) {
            startFlag.set(true);
            startFlag.notifyAll(); // Notify all waiting threads that the race has started
        }
    }

    public Animal[][] getAnimalTeams() {
        return animals;
    }
}
