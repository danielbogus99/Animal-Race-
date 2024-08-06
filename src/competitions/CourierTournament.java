package competitions;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class CourierTournament extends Tournament {
    private Boolean startFlag; // Special start flag to begin the race
    private Scores scores; // Scores to record the results
    private int numberOfGroups; // Number of groups in the tournament

    public CourierTournament(Animal[][] animalGroups, Object additionalInfo) {
        super(animalGroups, additionalInfo);
    }

    @Override
    protected void setup(Animal[][] animalGroups, Object additionalInfo) {
        this.startFlag = false; // Initialize the start flag to false
        this.scores = new Scores(); // Create a new Scores object
        this.numberOfGroups = animalGroups.length; // Set the number of groups

        for (int groupIndex = 0; groupIndex < animalGroups.length; groupIndex++) {
            Animal[] group = animalGroups[groupIndex];
            int n = group.length; // Number of animals in the group
            List<Boolean> flags = new ArrayList<>(n);

            // Initialize all flags to false
            for (int i = 0; i < n; i++) {
                flags.add(false);
            }

            // Create and start AnimalThread for each animal in the group
            for (int i = 0; i < n; i++) {
                Boolean currentStartFlag = (i == 0) ? startFlag : flags.get(i - 1);
                Boolean currentFinishFlag = flags.get(i);
                double neededDistance = 100.0 / n; // Calculate needed distance

                AnimalThread animalThread = new AnimalThread(group[i], neededDistance, currentStartFlag, currentFinishFlag);
                new Thread(animalThread).start();
            }

            // Create and start a Referee for the group
            Referee referee = new Referee("Group " + (groupIndex + 1), scores, flags.get(n - 1));
            new Thread(referee).start();
        }

        // Store the startFlag, scores, and number of groups in the tournament thread
        this.tournamentThread = new TournamentThread(scores, startFlag, numberOfGroups);
        new Thread(this.tournamentThread).start(); // Start the tournament thread
    }

    // Start the race by setting the start flag to true
    public void startRace() {
        synchronized (startFlag) {
            startFlag = true;
            startFlag.notifyAll();
        }
    }
}
