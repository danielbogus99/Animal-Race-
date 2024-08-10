package competitions;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

public class RegularTournament extends Tournament {
    private Boolean startFlag; // Special start flag to begin the race
    private Scores scores; // Scores to record the results
    private int numberOfGroups; // Number of groups in the tournament
    private Animal[][] animalTeams; // Store animal teams

    public RegularTournament(Animal[][] animalGroups, Object additionalInfo) {
        super(animalGroups, additionalInfo);
    }

    @Override
    protected void setup(Animal[][] animalGroups, Object additionalInfo) {
        this.startFlag = false; // Initialize the start flag to false
        this.scores = new Scores(); // Create a new Scores object
        this.numberOfGroups = animalGroups.length; // Set the number of groups
        this.animalTeams = animalGroups; // Store the animal teams

        for (Animal[] group : animalGroups)
        {
            // Each group contains only one animal in a regular tournament
            Animal animal = group[0];

            // Create a finish flag for the single animal
            Boolean finishFlag = false;

            // Create and start AnimalThread for the single animal
            AnimalThread animalThread = new AnimalThread(animal, 1000, startFlag, finishFlag);
            new Thread(animalThread).start();

            // Create and start a Referee for the single animal
            Referee referee = new Referee(animal.getAnimalName(), scores, finishFlag);
            new Thread(referee).start();
        }

        // Store the startFlag, scores, and number of groups in the tournament thread
        this.tournamentThread = new TournamentThread(scores, startFlag, numberOfGroups);
        new Thread(this.tournamentThread).start(); // Start the tournament thread
    }

    public Animal[][] getAnimalTeams() {
        return animalTeams;
    }


    public void startRace() {
        synchronized (startFlag)
        {
            startFlag = true;
            startFlag.notifyAll();
        }
    }
}
