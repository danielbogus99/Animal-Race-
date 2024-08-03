package competitions;

import animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class CourierTournament extends Tournament {

    private Boolean startFlag;
    private Scores scores;
    private int numberOfGroups;
    private List<Thread> animalThreads;
    private Thread refereeThread;

    public CourierTournament(Animal[][] animalGroups, Object additionalInfo) {
        super(animalGroups, additionalInfo);
    }

    @Override
    protected void setup(Animal[][] animalGroups, Object additionalInfo) {
        this.startFlag = false;
        this.scores = new Scores();
        this.numberOfGroups = animalGroups.length;
        this.animalThreads = new ArrayList<>();

        for (Animal[] group : animalGroups)
        {
            int n = group.length;
            Boolean[] flags = new Boolean[n];
            for (int i = 0; i < n; i++) {
                flags[i] = false;
            }

            double neededDistance = calculateNeededDistance(group);

            for (int i = 0; i < n; i++)
            {
                Boolean currentStartFlag = (i == 0) ? this.startFlag : flags[i - 1];
                Boolean currentFinishFlag = flags[i];
                AnimalThread animalThread = new AnimalThread(group[i], neededDistance, currentStartFlag, currentFinishFlag);
                Thread thread = new Thread(animalThread);
                animalThreads.add(thread);
                thread.start();
            }

            Referee referee = new Referee("Team " + group[0].getAnimaleName(), scores, flags[n - 1]);
            refereeThread = new Thread(referee);
            refereeThread.start();
        }
    }

    private double calculateNeededDistance(Animal[] group) {
        double totalDistance = 1000.0;
        return totalDistance / group.length;
    }

    public Boolean getStartFlag() {
        return startFlag;
    }

    public Scores getScores() {
        return scores;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public void startRace() {
        synchronized (startFlag) {
            startFlag = true;
            startFlag.notifyAll();
        }
    }

    public void stopRace() {
        for (Thread thread : animalThreads) {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        }
        if (refereeThread.isAlive()) {
            refereeThread.interrupt();
        }
    }
}
