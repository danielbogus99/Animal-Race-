package competitions;

import animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class RegularTournament extends Tournament {

    private Boolean startFlag;
    private Scores scores;
    private int numberOfGroups;
    private List<Thread> animalThreads;
    private List<Thread> refereeThreads;

    public RegularTournament(Animal[][] animalGroups, Object additionalInfo) {
        super(animalGroups, additionalInfo);
        setup(animalGroups, additionalInfo);
    }

    @Override
    protected void setup(Animal[][] animalGroups, Object additionalInfo) {
        this.startFlag = false;
        this.scores = new Scores();
        this.numberOfGroups = animalGroups.length;
        this.animalThreads = new ArrayList<>();
        this.refereeThreads = new ArrayList<>();

        for (Animal[] group : animalGroups) {
            // Assume each group has exactly one animal for a regular tournament
            Animal animal = group[0];

            // Create a finish flag for this animal
            Boolean finishFlag = false;

            // Create an AnimalThread with the start flag and finish flag
            AnimalThread animalThread = new AnimalThread(animal, calculateNeededDistance(animal), startFlag, finishFlag);
            Thread animalThreadObject = new Thread(animalThread);
            animalThreads.add(animalThreadObject);

            // Create a Referee with the finish flag and scores
            Referee referee = new Referee(animal.getAnimaleName(), scores, finishFlag);
            Thread refereeThread = new Thread(referee);
            refereeThreads.add(refereeThread);

            // Start the threads
            animalThreadObject.start();
            refereeThread.start();
        }
    }

    private double calculateNeededDistance(Animal animal) {
        // Assuming a fixed total distance for simplicity
        return 1000.0;
    }

    public void startRace() {
        synchronized (startFlag) {
            startFlag = true;
            startFlag.notifyAll(); // Notify all waiting threads
        }
    }

    public Scores getScores() {
        return scores;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }
}
