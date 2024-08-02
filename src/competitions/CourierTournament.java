package competitions;

import animals.Animal;

public class CourierTournament extends Tournament {

    public CourierTournament(Animal[][] animalGroups, Object additionalInfo) {
        super(animalGroups, additionalInfo);
    }

    @Override
    protected void setup(Animal[][] animalGroups, Object additionalInfo) {
        Boolean startFlag = false;
        Scores scores = new Scores();

        for (int i = 0; i < animalGroups.length; i++)
        {
            Animal[] group = animalGroups[i];
            int n = group.length;
            Boolean[] flags = new Boolean[n];

            for (int j = 0; j < n; j++) {
                flags[j] = false;
            }

            for (int k = 0; k < n; k++) {
                final Boolean currentStartFlag = (k == 0) ? startFlag : flags[k - 1];
                final Boolean currentFinishFlag = flags[k];

                double neededDistance = calculateNeededDistance(k, n);

                AnimalThread animalThread = new AnimalThread(group[k], currentStartFlag, currentFinishFlag, neededDistance);
                new Thread(animalThread).start();
            }

            Referee referee = new Referee(flags[n - 1], scores);
            new Thread(referee).start();
        }
    }
}