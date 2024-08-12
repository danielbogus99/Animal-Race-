package competitions;

import Graphics.ImagePanel;
import animals.Animal;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegularTournament extends Tournament {
    private  AtomicBoolean startFlag; // Correctly initialize as final to ensure it's properly set
    private Scores scores;
    private int numberOfGroups;
    private Animal[][] animals;
    private String raceName;

    private int neededDistance;// Reference to ImagePanel

    public RegularTournament(Animal[][] animalGroups, int neededDistance,String raceName) {
        super(animalGroups, neededDistance);
        this.startFlag = new AtomicBoolean(false);
        this.neededDistance = neededDistance;
       this.raceName = raceName;
        setup(animalGroups, neededDistance);
    }

    @Override
    protected void setup(Animal[][] animalGroups, Object additionalInfo) {
        this.scores = new Scores();
        this.numberOfGroups = animalGroups.length;
        this.animals = animalGroups;

        for (Animal[] group : animalGroups) {
            for (Animal animal : group) {
                AtomicBoolean finishFlag = new AtomicBoolean(false);

                AnimalThread animalThread = new AnimalThread(animal, neededDistance, startFlag, finishFlag);
                new Thread(animalThread).start();

                // Create a Referee to monitor each animal
                Referee referee = new Referee(animal.getAnimalName(), scores, finishFlag);
                new Thread(referee).start();
            }
        }

        // Start the main tournament thread and ensure it has the correct startFlag
        TournamentThread tournamentThread = new TournamentThread(scores, startFlag, numberOfGroups,raceName);
        new Thread(tournamentThread).start();
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
