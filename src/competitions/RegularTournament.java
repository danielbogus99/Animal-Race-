package competitions;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class RegularTournament extends Tournament {

    private boolean startFlag;
    private final Object startFlagLock; // Lock object for synchronization
    private Scores scores;
    private int numberOfGroups;
    private List<Thread> animalThreads;
    private List<Thread> refereeThreads;

    public RegularTournament(Animal[][] animalGroups, Object additionalInfo) {
        super(animalGroups, additionalInfo);
        this.startFlagLock = new Object(); // Initialize the lock here
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
            Animal animal = group[0];
            Boolean finishFlag = Boolean.FALSE;

            double neededDistance = calculateNeededDistance(animal);
            System.out.println(animal.getAnimaleName());
            // Ensure proper initialization before thread creation
            AnimalThread animalThread = new AnimalThread(animal, neededDistance, startFlagLock, finishFlag);
            Thread animalThreadObject = new Thread(animalThread);
            animalThreads.add(animalThreadObject);

            Referee referee = new Referee(animal.getAnimaleName(), scores, finishFlag);
            Thread refereeThread = new Thread(referee);
            refereeThreads.add(refereeThread);
        }
    }

    private double calculateNeededDistance(Animal animal) {
        // Assume a fixed total distance for now. Customize as needed.
        double totalDistance = 1000.0;
        return totalDistance;
    }

    public boolean getStartFlag() {
        synchronized (startFlagLock) {
            return startFlag;
        }
    }

    public Scores getScores() {
        return scores;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public void startRace() {
        synchronized (startFlagLock) {
            startFlag = true;
            startFlagLock.notifyAll(); // Notify all waiting threads
        }
        for (Thread animalThread : animalThreads) {
            animalThread.start();
        }
        for (Thread refereeThread : refereeThreads) {
            refereeThread.start();
        }
    }

    public void stopRace() {
        for (Thread thread : animalThreads) {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        }
        for (Thread thread : refereeThreads) {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        }
    }
}
