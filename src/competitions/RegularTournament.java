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
            Boolean finishFlag = false;

            double neededDistance = calculateNeededDistance(animal);


            AnimalThread animalThread = new AnimalThread(animal, neededDistance, this.startFlag, finishFlag);
            Thread animalThreadObject = new Thread(animalThread);
            animalThreads.add(animalThreadObject);
            animalThreadObject.start();


            Referee referee = new Referee(animal.getAnimaleName(), scores, finishFlag);
            Thread refereeThread = new Thread(referee);
            refereeThreads.add(refereeThread);
            refereeThread.start();
        }
    }

    private double calculateNeededDistance(Animal animal) {
        double totalDistance = 1000.0;
        return totalDistance;
    }

}
