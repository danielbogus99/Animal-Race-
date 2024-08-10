package competitions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;

public class TournamentThread implements Runnable {
    private final Scores scores; // Holds the final results of each group
    private Boolean startSignal; // Special flag to start all animals
    private final int groups; // Number of competing groups
    private final Map<String, Date> raceResults; // Store the results for UI updates

    public TournamentThread(Scores scores, Boolean startSignal, int groups) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
        this.raceResults = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {

        synchronized (startSignal) {
            startSignal = true;
            startSignal.notifyAll();
        }


        while (true)
        {
            Map<String, Date> allScores = scores.getAll(); // Get all scores

            synchronized (raceResults)
            {
                raceResults.putAll(allScores); // Update local results with the scores
            }

            // Display results for completed groups and keep others blank
            for (int i = 0; i < groups; i++) {
                String groupName = "Group " + (i + 1);
                Date finishTime = raceResults.get(groupName);

                if (finishTime != null) {
                    System.out.println(groupName + " finished at: " + finishTime);
                } else {
                    System.out.println(groupName + " is still racing.");
                }
            }


            if (raceResults.size() == groups)
            {
                System.out.println("All groups have finished the race.");
                break; // Exit loop if all groups are done
            }

            try {
                Thread.sleep(1000); // Sleep for a while before the next update
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle interruption
                return;
            }
        }
    }
}
